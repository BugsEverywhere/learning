package indi.simon.learning;

import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Spark SQL 解析器
 * 使用 Calcite 解析 SQL 并提取表和字段信息
 * 支持多段 SQL、DDL、DML 语句，能够提取所有 SELECT 中的表和字段
 */
public class SparkSqlParser {
    
    /**
     * 解析 SQL 并提取表和字段信息
     * 支持多段 SQL（用分号分隔），以及 DDL/DML 语句
     * 
     * @param sql SQL 语句（可以是多段，用分号分隔）
     * @return 包含表和字段信息的对象
     * @throws SqlParseException 如果 SQL 解析失败
     */
    public TableFieldInfo parse(String sql) throws SqlParseException {
        TableFieldInfo tableFieldInfo = new TableFieldInfo();
        
        // 分割多个 SQL 语句（按分号分割）
        List<String> sqlStatements = splitSqlStatements(sql);
        
        for (String sqlStatement : sqlStatements) {
            if (sqlStatement.trim().isEmpty()) {
                continue;
            }
            
            try {
                // 先尝试解析为语句（DDL/DML），因为 DDL 语句不能通过 parseQuery 解析
                parseAsStatement(sqlStatement, tableFieldInfo);
            } catch (SqlParseException e1) {
                try {
                    // 如果解析为语句失败，尝试解析为查询语句
                    parseAsQuery(sqlStatement, tableFieldInfo);
                } catch (SqlParseException e2) {
                    // 如果都失败，抛出第一个异常
                    throw e1;
                }
            }
        }
        
        return tableFieldInfo;
    }
    
    /**
     * 分割 SQL 语句（按分号分割，但要注意字符串中的分号）
     */
    private List<String> splitSqlStatements(String sql) {
        List<String> statements = new ArrayList<>();
        if (sql == null || sql.trim().isEmpty()) {
            return statements;
        }
        
        // 简单的分割逻辑：按分号分割，但忽略引号内的分号
        StringBuilder current = new StringBuilder();
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;
        boolean inBacktick = false;
        
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            
            if (c == '\'' && !inDoubleQuote && !inBacktick) {
                inSingleQuote = !inSingleQuote;
                current.append(c);
            } else if (c == '"' && !inSingleQuote && !inBacktick) {
                inDoubleQuote = !inDoubleQuote;
                current.append(c);
            } else if (c == '`' && !inSingleQuote && !inDoubleQuote) {
                inBacktick = !inBacktick;
                current.append(c);
            } else if (c == ';' && !inSingleQuote && !inDoubleQuote && !inBacktick) {
                String statement = current.toString().trim();
                if (!statement.isEmpty()) {
                    statements.add(statement);
                }
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        
        // 添加最后一段
        String lastStatement = current.toString().trim();
        if (!lastStatement.isEmpty()) {
            statements.add(lastStatement);
        }
        
        return statements;
    }
    
    /**
     * 解析为查询语句
     */
    private void parseAsQuery(String sql, TableFieldInfo tableFieldInfo) throws SqlParseException {
        SqlParser parser = SqlParser.create(sql);
        SqlNode sqlNode = parser.parseQuery();
        
        SqlTableFieldExtractor extractor = new SqlTableFieldExtractor(tableFieldInfo);
        sqlNode.accept(extractor);
    }
    
    /**
     * 解析为语句（DDL/DML）
     */
    private void parseAsStatement(String sql, TableFieldInfo tableFieldInfo) throws SqlParseException {
        SqlParser parser = SqlParser.create(sql);
        SqlNode sqlNode;
        
        try {
            // 尝试解析为语句
            sqlNode = parser.parseStmt();
        } catch (SqlParseException e) {
            // 如果 parseStmt 失败，尝试使用正则表达式提取 SELECT 部分
            // 这对于 CREATE TABLE AS SELECT 等语句很有用
            String selectPart = extractSelectFromDDL(sql);
            if (selectPart != null && !selectPart.isEmpty()) {
                // 递归解析提取出的 SELECT 部分
                parseAsQuery(selectPart, tableFieldInfo);
                return;
            }
            throw e;
        }
        
        SqlTableFieldExtractor extractor = new SqlTableFieldExtractor(tableFieldInfo);
        sqlNode.accept(extractor);
    }
    
    /**
     * 从 DDL 语句中提取 SELECT 部分
     * 例如：CREATE TABLE ... AS SELECT ... -> SELECT ...
     * 支持处理 WITH 子句等复杂情况
     */
    private String extractSelectFromDDL(String sql) {
        if (sql == null || sql.trim().isEmpty()) {
            return null;
        }
        
        String trimmedSql = sql.trim();
        String upperSql = trimmedSql.toUpperCase();
        
        // 处理 CREATE TABLE ... AS SELECT
        if (upperSql.startsWith("CREATE TABLE")) {
            // 查找 "AS SELECT" 或 "AS (SELECT" 或 "AS WITH"
            int asIndex = findAsKeyword(upperSql, "CREATE TABLE".length());
            if (asIndex > 0) {
                // 查找 AS 后面的 SELECT 或 WITH
                int selectIndex = upperSql.indexOf("SELECT", asIndex);
                int withIndex = upperSql.indexOf("WITH", asIndex);
                
                int startIndex = -1;
                if (selectIndex > 0 && (withIndex < 0 || selectIndex < withIndex)) {
                    startIndex = selectIndex;
                } else if (withIndex > 0) {
                    startIndex = withIndex;
                }
                
                if (startIndex > 0) {
                    return trimmedSql.substring(startIndex);
                }
            }
        }
        
        // 处理 INSERT INTO ... SELECT
        if (upperSql.startsWith("INSERT INTO")) {
            int selectIndex = upperSql.indexOf("SELECT");
            int withIndex = upperSql.indexOf("WITH");
            
            int startIndex = -1;
            if (selectIndex > 0 && (withIndex < 0 || selectIndex < withIndex)) {
                startIndex = selectIndex;
            } else if (withIndex > 0) {
                startIndex = withIndex;
            }
            
            if (startIndex > 0) {
                return trimmedSql.substring(startIndex);
            }
        }
        
        return null;
    }
    
    /**
     * 查找 AS 关键字的位置（忽略字符串中的 AS）
     */
    private int findAsKeyword(String upperSql, int startPos) {
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;
        
        for (int i = startPos; i < upperSql.length() - 1; i++) {
            char c = upperSql.charAt(i);
            
            if (c == '\'' && !inDoubleQuote) {
                inSingleQuote = !inSingleQuote;
            } else if (c == '"' && !inSingleQuote) {
                inDoubleQuote = !inDoubleQuote;
            } else if (!inSingleQuote && !inDoubleQuote) {
                // 检查是否是 " AS " 或 " AS(" 或 " AS\n"
                if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                    if (i + 2 < upperSql.length() && 
                        upperSql.charAt(i + 1) == 'A' && 
                        upperSql.charAt(i + 2) == 'S') {
                        // 检查 AS 后面是否是空格、换行或括号
                        if (i + 3 < upperSql.length()) {
                            char next = upperSql.charAt(i + 3);
                            if (next == ' ' || next == '\n' || next == '\r' || 
                                next == '\t' || next == '(' || next == '\n') {
                                return i + 1; // 返回 A 的位置
                            }
                        }
                    }
                }
            }
        }
        
        return -1;
    }
    
    /**
     * 解析 SQL 并返回解析结果（包含表和字段信息）
     * 
     * @param sql SQL 语句
     * @return 解析结果字符串
     */
    public String parseAndFormat(String sql) {
        try {
            TableFieldInfo info = parse(sql);
            return info.toString();
        } catch (SqlParseException e) {
            return "SQL 解析失败: " + e.getMessage();
        }
    }
}
