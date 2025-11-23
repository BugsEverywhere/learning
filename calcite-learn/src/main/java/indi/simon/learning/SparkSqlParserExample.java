package indi.simon.learning;

import org.apache.calcite.sql.parser.SqlParseException;

/**
 * Spark SQL 解析器使用示例
 * 演示如何解析各种复杂 SQL 语法
 */
public class SparkSqlParserExample {
    
    public static void main(String[] args) {
        SparkSqlParser parser = new SparkSqlParser();
        
        System.out.println("========== Spark SQL 解析器示例 ==========\n");
        
        // 示例 1: 简单查询
        testSimpleQuery(parser);
        
        // 示例 2: JOIN 查询
        testJoinQuery(parser);
        
        // 示例 3: 子查询
        testSubquery(parser);
        
        // 示例 4: CTE (WITH 子句)
        testCTE(parser);
        
        // 示例 5: CASE WHEN
        testCaseWhen(parser);
        
        // 示例 6: 复杂嵌套查询
        testComplexNestedQuery(parser);
        
        // 示例 7: UNION 查询
        testUnionQuery(parser);
    }
    
    private static void testSimpleQuery(SparkSqlParser parser) {
        System.out.println("【示例 1】简单查询:");
        String sql = "SELECT id, name, age FROM users WHERE age > 18";
        System.out.println("SQL: " + sql);
        System.out.println(parser.parseAndFormat(sql));
        System.out.println();
    }
    
    private static void testJoinQuery(SparkSqlParser parser) {
        System.out.println("【示例 2】JOIN 查询:");
        String sql = "SELECT u.id, u.name, o.order_id, o.amount " +
                     "FROM users u JOIN orders o ON u.id = o.user_id " +
                     "WHERE u.age > 25";
        System.out.println("SQL: " + sql);
        System.out.println(parser.parseAndFormat(sql));
        System.out.println();
    }
    
    private static void testSubquery(SparkSqlParser parser) {
        System.out.println("【示例 3】子查询:");
        String sql = "SELECT name, age FROM users " +
                     "WHERE id IN (SELECT user_id FROM orders WHERE amount > 1000)";
        System.out.println("SQL: " + sql);
        System.out.println(parser.parseAndFormat(sql));
        System.out.println();
    }
    
    private static void testCTE(SparkSqlParser parser) {
        System.out.println("【示例 4】CTE (WITH 子句):");
        String sql = "WITH high_value_orders AS (" +
                     "  SELECT user_id, SUM(amount) as total " +
                     "  FROM orders " +
                     "  GROUP BY user_id " +
                     "  HAVING SUM(amount) > 5000" +
                     ") " +
                     "SELECT u.name, hvo.total " +
                     "FROM users u JOIN high_value_orders hvo ON u.id = hvo.user_id";
        System.out.println("SQL: " + sql);
        System.out.println(parser.parseAndFormat(sql));
        System.out.println();
    }
    
    private static void testCaseWhen(SparkSqlParser parser) {
        System.out.println("【示例 5】CASE WHEN:");
        String sql = "SELECT id, name, " +
                     "CASE WHEN age < 18 THEN '未成年' " +
                     "     WHEN age < 60 THEN '成年' " +
                     "     ELSE '老年' END as age_group " +
                     "FROM users";
        System.out.println("SQL: " + sql);
        System.out.println(parser.parseAndFormat(sql));
        System.out.println();
    }
    
    private static void testComplexNestedQuery(SparkSqlParser parser) {
        System.out.println("【示例 6】复杂嵌套查询:");
        String sql = "SELECT u.name, " +
                     "       (SELECT COUNT(*) FROM orders o WHERE o.user_id = u.id) as order_count, " +
                     "       (SELECT MAX(amount) FROM orders o2 WHERE o2.user_id = u.id) as max_amount " +
                     "FROM users u " +
                     "WHERE u.id IN (" +
                     "  SELECT DISTINCT user_id FROM orders WHERE order_date > '2023-01-01'" +
                     ")";
        System.out.println("SQL: " + sql);
        System.out.println(parser.parseAndFormat(sql));
        System.out.println();
    }
    
    private static void testUnionQuery(SparkSqlParser parser) {
        System.out.println("【示例 7】UNION 查询:");
        String sql = "SELECT id, name FROM users WHERE age < 18 " +
                     "UNION " +
                     "SELECT id, name FROM users WHERE age > 60";
        System.out.println("SQL: " + sql);
        System.out.println(parser.parseAndFormat(sql));
        System.out.println();
    }
}
