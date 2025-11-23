# Spark SQL 解析工具

基于 Apache Calcite 实现的 Spark SQL 解析工具，用于提取 SQL 语句中使用的表和字段信息。

## 功能特性

- ✅ 支持解析 Spark SQL 常用语法
- ✅ 支持嵌套子查询
- ✅ 支持 CTE (WITH 子句)
- ✅ 支持 CASE WHEN 表达式
- ✅ 支持 JOIN 查询（INNER JOIN, LEFT JOIN, RIGHT JOIN 等）
- ✅ 支持 UNION, INTERSECT, EXCEPT 等集合操作
- ✅ **支持多段 SQL（用分号分隔）**
- ✅ **支持 DDL 语句（CREATE TABLE AS SELECT）**
- ✅ **支持 DML 语句（INSERT INTO ... SELECT）**
- ✅ **能够提取所有 SELECT 语句中的表和字段，无论 SELECT 出现在什么位置**
- ✅ 提取 SQL 中所有使用的表名
- ✅ 提取 SQL 中所有使用的字段名

## 项目结构

```
calcite-learn/
├── src/main/java/indi/simon/learning/
│   ├── SparkSqlParser.java          # 主解析器类
│   ├── SqlTableFieldExtractor.java  # SQL AST 访问者，用于提取表和字段
│   ├── TableFieldInfo.java          # 存储表和字段信息的数据结构
│   └── SparkSqlParserExample.java   # 使用示例
└── pom.xml
```

## 使用方法

### 基本用法

```java
import indi.simon.learning.SparkSqlParser;
import indi.simon.learning.TableFieldInfo;
import org.apache.calcite.sql.parser.SqlParseException;

SparkSqlParser parser = new SparkSqlParser();

try {
    String sql = "SELECT id, name, age FROM users WHERE age > 18";
    TableFieldInfo info = parser.parse(sql);
    
    // 获取所有表名
    Set<String> tables = info.getTables();
    System.out.println("Tables: " + tables);
    
    // 获取所有字段名
    Set<String> fields = info.getFields();
    System.out.println("Fields: " + fields);
} catch (SqlParseException e) {
    System.err.println("SQL 解析失败: " + e.getMessage());
}
```

### 快速格式化输出

```java
String sql = "SELECT u.id, u.name FROM users u JOIN orders o ON u.id = o.user_id";
String result = parser.parseAndFormat(sql);
System.out.println(result);
```

## 支持的 SQL 语法示例

### 1. 简单查询
```sql
SELECT id, name, age FROM users WHERE age > 18
```

### 2. JOIN 查询
```sql
SELECT u.id, u.name, o.order_id, o.amount 
FROM users u 
JOIN orders o ON u.id = o.user_id 
WHERE u.age > 25
```

### 3. 子查询
```sql
SELECT name, age 
FROM users 
WHERE id IN (SELECT user_id FROM orders WHERE amount > 1000)
```

### 4. CTE (WITH 子句)
```sql
WITH high_value_orders AS (
  SELECT user_id, SUM(amount) as total 
  FROM orders 
  GROUP BY user_id 
  HAVING SUM(amount) > 5000
) 
SELECT u.name, hvo.total 
FROM users u 
JOIN high_value_orders hvo ON u.id = hvo.user_id
```

### 5. CASE WHEN
```sql
SELECT id, name, 
  CASE 
    WHEN age < 18 THEN '未成年'
    WHEN age < 60 THEN '成年'
    ELSE '老年' 
  END as age_group 
FROM users
```

### 6. 复杂嵌套查询
```sql
SELECT u.name, 
       (SELECT COUNT(*) FROM orders o WHERE o.user_id = u.id) as order_count,
       (SELECT MAX(amount) FROM orders o2 WHERE o2.user_id = u.id) as max_amount
FROM users u
WHERE u.id IN (
  SELECT DISTINCT user_id FROM orders WHERE order_date > '2023-01-01'
)
```

### 7. UNION 查询
```sql
SELECT id, name FROM users WHERE age < 18
UNION
SELECT id, name FROM users WHERE age > 60
```

### 8. 多段 SQL
```sql
SELECT id, name FROM users WHERE age > 18;
SELECT order_id, amount FROM orders WHERE amount > 1000;
```

### 9. CREATE TABLE AS SELECT
```sql
CREATE TABLE new_users AS 
SELECT id, name, age FROM users WHERE age > 18
```

### 10. INSERT INTO ... SELECT
```sql
INSERT INTO target_table (id, name) 
SELECT id, name FROM users WHERE age > 18
```

### 11. 复杂的 CREATE TABLE AS SELECT（包含 CTE 和子查询）
```sql
CREATE TABLE user_statistics AS 
WITH high_value_orders AS (
  SELECT user_id, SUM(amount) as total 
  FROM orders 
  GROUP BY user_id 
  HAVING SUM(amount) > 5000
) 
SELECT u.id, u.name, hvo.total 
FROM users u 
JOIN high_value_orders hvo ON u.id = hvo.user_id
```

## 运行示例

运行示例程序查看各种 SQL 语法的解析结果：

```bash
mvn compile exec:java -Dexec.mainClass="indi.simon.learning.SparkSqlParserExample"
```

## 运行测试

运行所有测试用例：

```bash
# 运行所有测试
mvn test

# 运行基础功能测试
mvn test -Dtest=SparkSqlParserTest

# 运行高级功能测试（多段 SQL、DDL/DML）
mvn test -Dtest=SparkSqlParserAdvancedTest
```

## 技术实现

- **解析器**: Apache Calcite 1.6.0
- **解析方式**: 使用 Calcite 的 `SqlParser` 将 SQL 解析为 AST
- **提取方式**: 实现 `SqlBasicVisitor` 访问者模式，遍历 AST 提取表和字段信息

## 注意事项

1. 本工具基于 Calcite 标准 SQL 解析器，与 Spark SQL 语法高度兼容
2. 表名和字段名的提取基于 SQL AST 结构，可能包含一些别名信息
3. 对于复杂的 SQL，建议先测试确认提取结果是否符合预期
4. **多段 SQL 支持**：使用分号（`;`）分隔多个 SQL 语句，工具会自动解析每一段
5. **DDL/DML 支持**：工具能够从 CREATE TABLE AS SELECT 和 INSERT INTO ... SELECT 语句中提取 SELECT 部分的表和字段信息
6. **智能提取**：无论 SELECT 语句出现在什么位置（普通查询、子查询、CTE、DDL 中的 AS SELECT、DML 中的 SELECT），都能准确提取

## 依赖

- Apache Calcite Core 1.6.0
- Apache Calcite Avatica 1.6.0

## 许可证

本项目为学习项目，仅供学习和参考使用。
