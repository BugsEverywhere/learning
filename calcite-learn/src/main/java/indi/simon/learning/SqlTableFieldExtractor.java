package indi.simon.learning;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.util.SqlBasicVisitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * SQL 访问者，用于提取表和字段信息
 * 支持处理子查询、CTE、JOIN 等复杂结构
 */
public class SqlTableFieldExtractor extends SqlBasicVisitor<Void> {
    
    private TableFieldInfo tableFieldInfo;
    private Stack<Boolean> inFromContext = new Stack<>(); // 标记是否在 FROM 子句中
    private Map<String, String> cteAliases = new HashMap<>(); // CTE 别名映射
    private Map<String, String> tableAliases = new HashMap<>(); // 表别名映射（别名 -> 实际表名）
    private boolean inAsOperand = false; // 标记是否在 AS 操作的操作数中（实际表名）
    private Stack<Set<String>> currentQueryTables = new Stack<>(); // 当前查询的 FROM 表集合（用于解析无表前缀的字段）
    private Stack<Boolean> inFunctionCall = new Stack<>(); // 标记是否在函数调用中（用于区分 SELECT * 和 COUNT(*)）
    
    public SqlTableFieldExtractor(TableFieldInfo tableFieldInfo) {
        this.tableFieldInfo = tableFieldInfo;
    }
    
    @Override
    public Void visit(SqlIdentifier id) {
        // 检查是否是 SELECT * 中的 *
        // 注意：SELECT * 的处理已经在 visitSelect 方法中完成
        // 这里主要是为了确保 COUNT(*) 中的 * 不被处理（当 visit(SqlCall) 的 default 分支访问函数参数时）
        if (id.names.size() == 1 && "*".equals(id.names.get(0))) {
            // 如果在函数调用中（如 COUNT(*)），不处理，直接返回
            // 如果不在函数调用中，也不处理（因为 SELECT * 已经在 visitSelect 中处理了）
            return null;
        }
        
        // 如果在 FROM 上下文中
        if (!inFromContext.isEmpty() && inFromContext.peek()) {
            // 在 FROM 子句中，只有在 AS 操作的操作数中才是实际的表名
            // 如果不在 AS 操作的操作数中，可能是 CTE 别名或表别名，不应该添加
            if (inAsOperand) {
                // 这是实际的表名，添加它
                String tableName = buildTableName(id);
                tableFieldInfo.addTable(tableName);
            }
            // 否则，这是别名或 CTE，不添加到 tables
            return null;
        }
        
        // 不在 FROM 上下文中，这是字段引用
        if (id.names.size() == 1) {
            // 单个标识符，可能是字段名（没有表前缀）
            String name = id.names.get(0);
            
            // 检查是否是数字字面量（如 "1"）或特殊值
            // 注意：Calcite 可能将数字字面量解析为 SqlIdentifier，所以需要检查
            try {
                // 尝试解析为数字，如果是数字，不是字段
                Double.parseDouble(name);
                return null; // 是数字字面量，不处理
            } catch (NumberFormatException e) {
                // 不是数字，继续检查
                // 但也要检查是否是纯数字字符串（可能被解析为标识符）
                if (name.matches("^\\d+(\\.\\d+)?$")) {
                    return null; // 是数字格式的字符串，不处理
                }
            }
            
            // 检查是否是特殊关键字（如 NULL, TRUE, FALSE 等）
            String nameUpper = name.toUpperCase();
            if ("NULL".equals(nameUpper) || "TRUE".equals(nameUpper) || "FALSE".equals(nameUpper)) {
                return null; // 是关键字，不是字段
            }
            
            // 尝试从当前查询的 FROM 表集合中确定表名
            // 只有在有 FROM 表信息且只有一个表时，才将单个标识符当作字段
            // 注意：如果没有 FROM 表信息，单个标识符可能是字面量或其他，不应该当作字段
            if (!currentQueryTables.isEmpty()) {
                Set<String> tables = currentQueryTables.peek();
                // 只有在有表且只有一个表时，才将单个标识符当作字段
                if (tables != null && !tables.isEmpty() && tables.size() == 1) {
                    // 只有一个表，可以确定字段属于该表
                    String tableNameUpper = tables.iterator().next();
                    // 从已添加的表名中查找匹配的表名（保持大小写一致）
                    String actualTableName = findActualTableName(tableNameUpper);
                    if (actualTableName != null) {
                        String fieldName = id.names.get(0);
                        // 统一使用大写格式
                        tableFieldInfo.addField(tableNameUpper + "." + fieldName.toUpperCase());
                    } else {
                        // 如果表中不存在，可能是新表，使用 tableNameUpper
                        String fieldName = id.names.get(0);
                        tableFieldInfo.addField(tableNameUpper + "." + fieldName.toUpperCase());
                    }
                }
                // 如果有多个表或没有表，无法确定字段属于哪个表，跳过
            }
            // 如果没有 FROM 表信息，跳过（可能是字面量或其他，不应该当作字段）
        } else if (id.names.size() == 2) {
            // table.field 格式
            String table = id.names.get(0);
            String field = id.names.get(1);
            
            // 检查字段名是否为空（避免添加不完整的字段引用，如 "table."）
            if (field == null || field.isEmpty() || "*".equals(field)) {
                return null;
            }
            
            String tableUpper = table.toUpperCase();
            
            // 先检查是否是 CTE 别名（包括 CTE 的表别名）
            if (cteAliases.containsKey(tableUpper)) {
                // 是 CTE 别名，不添加到 fields（因为 CTE 不是原始表）
                return null;
            }
            
            // 不是 CTE 别名，解析实际表名
            String actualTable = resolveTableName(table);
            if (actualTable != null && !cteAliases.containsKey(actualTable.toUpperCase())) {
                // 有实际表名，且不是 CTE
                // 统一使用大写格式
                String actualTableUpper = actualTable.toUpperCase();
                // 添加表名（如果需要）
                String existingTable = findActualTableName(actualTableUpper);
                if (existingTable == null) {
                    tableFieldInfo.addTable(actualTableUpper);
                }
                // 添加字段（统一使用大写格式）
                tableFieldInfo.addField(actualTableUpper + "." + field.toUpperCase());
            }
            // 如果是 CTE 或无法解析，不添加字段
        } else if (id.names.size() == 3) {
            // schema.table.field 格式
            String schema = id.names.get(0);
            String table = id.names.get(1);
            String field = id.names.get(2);
            
            // 检查字段名是否为空（避免添加不完整的字段引用）
            if (field == null || field.isEmpty() || "*".equals(field)) {
                return null;
            }
            
            // schema.table 格式通常是实际表名
            String fullTableName = schema + "." + table;
            if (!cteAliases.containsKey(fullTableName.toUpperCase())) {
                tableFieldInfo.addTable(fullTableName);
                // 统一使用大写格式
                tableFieldInfo.addField(fullTableName.toUpperCase() + "." + field.toUpperCase());
            }
        }
        return null;
    }
    
    /**
     * 从已添加的表名中查找匹配的表名（保持原始大小写）
     */
    private String findActualTableName(String tableNameUpper) {
        // 从已添加的表名中查找（不区分大小写）
        for (String table : tableFieldInfo.getTables()) {
            if (table.toUpperCase().equals(tableNameUpper)) {
                return table;
            }
        }
        return null;
    }
    
    /**
     * 解析表名：如果是别名，返回实际表名；否则返回原值
     * 注意：CTE 别名返回 null，表别名返回实际表名
     */
    private String resolveTableName(String name) {
        String nameUpper = name.toUpperCase();
        
        // 先检查是否是 CTE 别名（CTE 别名优先级最高）
        if (cteAliases.containsKey(nameUpper)) {
            // CTE 别名不应该被添加到 tables
            return null;
        }
        
        // 检查是否是表别名
        String actualTable = tableAliases.get(nameUpper);
        if (actualTable != null) {
            // 检查实际表名是否是 CTE 别名
            if (cteAliases.containsKey(actualTable)) {
                // 实际表名是 CTE 别名，不应该添加
                return null;
            }
            return actualTable;
        }
        
        // 不是别名，返回原值（但需要检查原值是否是 CTE）
        if (cteAliases.containsKey(nameUpper)) {
            return null;
        }
        return name;
    }
    
    private String buildTableName(SqlIdentifier id) {
        if (id.names.size() == 1) {
            return id.names.get(0);
        } else if (id.names.size() == 2) {
            return id.names.get(0) + "." + id.names.get(1);
        } else if (id.names.size() == 3) {
            return id.names.get(0) + "." + id.names.get(1) + "." + id.names.get(2);
        }
        return String.join(".", id.names);
    }
    
    @Override
    public Void visit(SqlCall call) {
        SqlKind kind = call.getKind();
        
        switch (kind) {
            case SELECT:
                return visitSelect((SqlSelect) call);
            case JOIN:
                return visitJoin((SqlJoin) call);
            case AS:
                return visitAs(call);
            case UNION:
            case INTERSECT:
            case EXCEPT:
                return visitSetOp(call);
            case CASE:
                return visitCase(call);
            case WITH:
                return visitWith((SqlWith) call);
            case INSERT:
                // INSERT 语句需要特殊处理
                if (call instanceof SqlInsert) {
                    return visitInsertStatement((SqlInsert) call);
                }
                return visitInsert(call);
            case CREATE_TABLE:
            case OTHER_DDL:
                // DDL 语句（CREATE TABLE AS SELECT 等）
                return visitDdlStatement(call);
            default:
                // 处理其他类型的调用，如函数调用等
                // 函数调用（如 MAX(amount), COUNT(*)）需要递归访问所有参数以提取字段
                // 标记当前在函数调用中
                inFunctionCall.push(true);
                List<SqlNode> operands = call.getOperandList();
                if (operands != null) {
                    for (SqlNode operand : operands) {
                        if (operand != null) {
                            operand.accept(this);
                        }
                    }
                }
                inFunctionCall.pop();
                return null;
        }
    }
    
    /**
     * 处理 INSERT INTO ... SELECT 语句
     */
    private Void visitInsert(SqlCall call) {
        // INSERT 语句的结构：INSERT INTO table (columns) SELECT ...
        // 需要访问 SELECT 部分
        for (SqlNode operand : call.getOperandList()) {
            if (operand != null) {
                operand.accept(this);
            }
        }
        return null;
    }
    
    /**
     * 处理 CREATE TABLE AS SELECT 语句
     */
    private Void visitCreateTable(SqlCall call) {
        // CREATE TABLE 语句可能包含 AS SELECT 子句
        // 遍历所有操作数，查找 SELECT 语句
        for (SqlNode operand : call.getOperandList()) {
            if (operand != null) {
                operand.accept(this);
            }
        }
        return null;
    }
    
    private Void visitWith(SqlWith with) {
        // 处理 WITH 子句（CTE）
        SqlNodeList withList = with.withList;
        if (withList != null) {
            for (SqlNode node : withList) {
                if (node instanceof SqlWithItem) {
                    SqlWithItem withItem = (SqlWithItem) node;
                    SqlIdentifier alias = withItem.name;
                    if (alias != null) {
                        String aliasName = alias.getSimple().toUpperCase();
                        // 记录 CTE 别名（使用大写，便于后续检查）
                        cteAliases.put(aliasName, aliasName);
                    }
                    // 访问 CTE 的查询（先访问查询，再访问主查询）
                    SqlNode query = withItem.query;
                    if (query != null) {
                        query.accept(this);
                    }
                }
            }
        }
        // 访问主查询（CTE 别名已经记录）
        SqlNode body = with.body;
        if (body != null) {
            body.accept(this);
        }
        return null;
    }
    
    private Void visitSelect(SqlSelect select) {
        // 收集当前查询的 FROM 表集合（用于解析无表前缀的字段）
        Set<String> queryTables = new HashSet<>();
        currentQueryTables.push(queryTables);
        
        // 处理 FROM 子句（先处理 FROM，确保在处理 SELECT 列表时 queryTables 已经被填充）
        SqlNode from = select.getFrom();
        if (from != null) {
            inFromContext.push(true);
            // FROM 子句中的表名需要特殊处理
            if (from instanceof SqlIdentifier) {
                // 检查是否是 CTE 别名
                String tableName = buildTableName((SqlIdentifier) from);
                if (!cteAliases.containsKey(tableName.toUpperCase())) {
                    // 不是 CTE 别名，是实际表名，添加（统一使用大写）
                    String tableNameUpper = tableName.toUpperCase();
                    tableFieldInfo.addTable(tableNameUpper);
                    queryTables.add(tableNameUpper);
                }
                // 如果是 CTE 别名，不添加
            } else {
                // 可能是 JOIN、AS 操作、子查询等，递归访问
                // 在访问过程中会收集表名到 tableFieldInfo
                // 访问完成后，从 tableFieldInfo 中收集表名到 queryTables
                // 记录访问前的表集合
                Set<String> tablesBefore = new HashSet<>(tableFieldInfo.getTables());
                from.accept(this);
                // 找出新添加的表
                Set<String> tablesAfter = tableFieldInfo.getTables();
                for (String table : tablesAfter) {
                    String tableUpper = table.toUpperCase();
                    if (!tablesBefore.contains(table) && !queryTables.contains(tableUpper)) {
                        queryTables.add(tableUpper);
                    }
                }
            }
            inFromContext.pop();
        }
        
        // 处理 SELECT 列表（不在 FROM 上下文中）
        // 注意：此时 queryTables 应该已经被填充了
        SqlNodeList selectList = select.getSelectList();
        if (selectList != null) {
            for (SqlNode node : selectList) {
                // 检查是否是 SELECT *（使用 toString() 来检查，因为 Calcite 中 * 的表示可能特殊）
                String nodeStr = node.toString();
                if ("*".equals(nodeStr.trim())) {
                    // 这是 SELECT * 的情况，需要添加 table.* 格式的字段
                    if (!queryTables.isEmpty()) {
                        if (queryTables.size() == 1) {
                            String tableName = queryTables.iterator().next();
                            tableFieldInfo.addField(tableName + ".*");
                        } else {
                            // 多个表，为每个表添加 table.*
                            for (String tableName : queryTables) {
                                tableFieldInfo.addField(tableName + ".*");
                            }
                        }
                    }
                    // 跳过访问，因为我们已经处理了
                    continue;
                }
                node.accept(this);
            }
        }
        
        // 处理 WHERE 子句（需要 currentQueryTables 来解析无表前缀的字段）
        // 注意：WHERE 子句中的字面量（如 1）不应该被当作字段
        SqlNode where = select.getWhere();
        if (where != null) {
            where.accept(this);
        }
        
        // 处理 GROUP BY
        SqlNodeList groupBy = select.getGroup();
        if (groupBy != null) {
            for (SqlNode node : groupBy) {
                node.accept(this);
            }
        }
        
        // 处理 HAVING
        SqlNode having = select.getHaving();
        if (having != null) {
            having.accept(this);
        }
        
        // 处理 ORDER BY
        SqlNodeList orderBy = select.getOrderList();
        if (orderBy != null) {
            for (SqlNode node : orderBy) {
                node.accept(this);
            }
        }
        
        // 移除当前查询的表集合（在所有子句处理完成后）
        currentQueryTables.pop();
        
        return null;
    }
    
    private Void visitJoin(SqlJoin join) {
        // 访问左表（在 FROM 上下文中）
        SqlNode left = join.getLeft();
        if (left != null) {
            inFromContext.push(true);
            // 左表可能是：
            // 1. 表名（SqlIdentifier，且不是 CTE 别名）
            // 2. 表名 AS 别名（AS 操作，会在 visitAs 中处理）
            // 3. CTE 别名 AS 别名（AS 操作，操作数是 CTE 别名）
            // 4. 子查询
            if (left instanceof SqlIdentifier) {
                // 直接是表名（没有别名），检查是否是 CTE 别名
                String tableName = buildTableName((SqlIdentifier) left).toUpperCase();
                if (!cteAliases.containsKey(tableName)) {
                    // 不是 CTE 别名，是实际表名，添加
                    tableFieldInfo.addTable(tableName);
                    // 更新当前查询的表集合
                    if (!currentQueryTables.isEmpty()) {
                        currentQueryTables.peek().add(tableName);
                    }
                }
                // 如果是 CTE 别名，不添加
            } else {
                // 可能是 AS 操作、子查询等，递归访问（visitAs 会处理 CTE 检查）
                left.accept(this);
            }
            inFromContext.pop();
        }
        
        // 访问右表（在 FROM 上下文中）
        SqlNode right = join.getRight();
        if (right != null) {
            inFromContext.push(true);
            if (right instanceof SqlIdentifier) {
                // 直接是表名（没有别名），检查是否是 CTE 别名
                String tableName = buildTableName((SqlIdentifier) right).toUpperCase();
                if (!cteAliases.containsKey(tableName)) {
                    // 不是 CTE 别名，是实际表名，添加
                    tableFieldInfo.addTable(tableName);
                    // 更新当前查询的表集合
                    if (!currentQueryTables.isEmpty()) {
                        currentQueryTables.peek().add(tableName);
                    }
                }
                // 如果是 CTE 别名，不添加
            } else {
                // 可能是 AS 操作、子查询等，递归访问（visitAs 会处理 CTE 检查）
                right.accept(this);
            }
            inFromContext.pop();
        }
        
        // 访问 JOIN 条件（不在 FROM 上下文中）
        SqlNode condition = join.getCondition();
        if (condition != null) {
            condition.accept(this);
        }
        
        return null;
    }
    
    private Void visitAs(SqlCall call) {
        // 处理 AS 别名
        if (call.operandCount() >= 2) {
            SqlNode operand = call.operand(0);
            SqlNode alias = call.operand(1);
            
            // 如果是在 FROM 上下文中，操作数可能是实际的表名、CTE 别名或子查询
            if (!inFromContext.isEmpty() && inFromContext.peek()) {
                // FROM 上下文中的 AS 处理（表别名）
                // 标记正在访问 AS 操作的操作数
                boolean oldInAsOperand = inAsOperand;
                inAsOperand = true;
                
                // 检查操作数是否是 CTE 别名
                if (operand instanceof SqlIdentifier) {
                    SqlIdentifier operandId = (SqlIdentifier) operand;
                    String operandName = buildTableName(operandId).toUpperCase();
                    
                    // 如果是 CTE 别名，不添加到 tables，也不记录表别名
                    if (!cteAliases.containsKey(operandName)) {
                        // 不是 CTE 别名，是实际表名
                        // 直接添加表名（统一使用大写），而不是调用 accept（避免重复处理）
                        tableFieldInfo.addTable(operandName);
                        // 更新当前查询的表集合
                        if (!currentQueryTables.isEmpty()) {
                            currentQueryTables.peek().add(operandName);
                        }
                        
                        // 记录表别名映射（只有实际表名才记录别名）
                        if (alias instanceof SqlIdentifier) {
                            SqlIdentifier aliasId = (SqlIdentifier) alias;
                            String aliasName = aliasId.getSimple().toUpperCase();
                            tableAliases.put(aliasName, operandName);
                        }
                    } else {
                        // 操作数是 CTE 别名，别名本身也应该标记为 CTE 别名
                        if (alias instanceof SqlIdentifier) {
                            SqlIdentifier aliasId = (SqlIdentifier) alias;
                            String aliasName = aliasId.getSimple().toUpperCase();
                            // 将别名也标记为 CTE 别名（因为它是 CTE 的表别名）
                            cteAliases.put(aliasName, aliasName);
                        }
                    }
                    // 如果是 CTE 别名，不处理（不添加到 tables，不记录表别名）
                } else {
                    // 不是 SqlIdentifier，可能是子查询等，递归访问
                    operand.accept(this);
                    
                    // 如果是子查询，别名可能是 CTE
                    if (alias instanceof SqlIdentifier) {
                        SqlIdentifier aliasId = (SqlIdentifier) alias;
                        String aliasName = aliasId.getSimple().toUpperCase();
                        if (operand instanceof SqlSelect || operand instanceof SqlCall) {
                            // 子查询的别名是 CTE
                            cteAliases.put(aliasName, aliasName);
                        }
                    }
                }
                
                inAsOperand = oldInAsOperand;
            } else {
                // 不在 FROM 上下文中，这是字段别名（SELECT 列表中的 AS）
                // 只访问操作数（字段），不访问别名（别名不应该被添加到 fields）
                operand.accept(this);
                // 别名本身不处理，因为字段别名不应该出现在 fields 中
            }
        }
        return null;
    }
    
    private Void visitSetOp(SqlCall call) {
        // 处理 UNION, INTERSECT, EXCEPT
        List<SqlNode> operands = call.getOperandList();
        if (operands != null) {
            for (SqlNode operand : operands) {
                if (operand != null) {
                    operand.accept(this);
                }
            }
        }
        return null;
    }
    
    private Void visitCase(SqlCall call) {
        // 处理 CASE WHEN 表达式
        List<SqlNode> operands = call.getOperandList();
        if (operands != null) {
            for (SqlNode operand : operands) {
                if (operand != null) {
                    operand.accept(this);
                }
            }
        }
        return null;
    }
    
    @Override
    public Void visit(SqlNodeList nodeList) {
        for (SqlNode node : nodeList) {
            node.accept(this);
        }
        return null;
    }
    
    @Override
    public Void visit(SqlLiteral literal) {
        // 字面量不需要处理
        return null;
    }
    
    @Override
    public Void visit(SqlDynamicParam param) {
        // 参数不需要处理
        return null;
    }
    
    /**
     * 处理 INSERT 语句
     */
    private Void visitInsertStatement(SqlInsert insert) {
        // INSERT INTO table SELECT ... 或 INSERT INTO table (cols) SELECT ...
        // 需要访问 source（SELECT 部分）
        SqlNode source = insert.getSource();
        if (source != null) {
            source.accept(this);
        }
        
        // 访问其他部分（可能也包含 SELECT）
        SqlNodeList targetColumnList = insert.getTargetColumnList();
        if (targetColumnList != null) {
            targetColumnList.accept(this);
        }
        
        return null;
    }
    
    /**
     * 处理 DDL 语句（CREATE TABLE 等）
     * 递归访问所有子节点以找到 SELECT 语句
     */
    private Void visitDdlStatement(SqlCall call) {
        // DDL 语句可能包含 SELECT（如 CREATE TABLE AS SELECT）
        // 递归访问所有操作数
        List<SqlNode> operands = call.getOperandList();
        if (operands != null) {
            for (SqlNode operand : operands) {
                if (operand != null) {
                    // 递归访问所有子节点
                    operand.accept(this);
                }
            }
        }
        return null;
    }
}
