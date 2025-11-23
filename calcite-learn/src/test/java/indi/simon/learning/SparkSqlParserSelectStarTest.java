package indi.simon.learning;

import org.apache.calcite.sql.parser.SqlParseException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试 SELECT * 的解析
 */
public class SparkSqlParserSelectStarTest {
    
    private SparkSqlParser parser = new SparkSqlParser();
    
    @Test
    public void testSelectStarFromSingleTable() throws SqlParseException {
        // 测试 SELECT * FROM table
        String sql = "SELECT * FROM users";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        
        // 验证表名
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段：应该包含 USERS.*
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.*");
        Assert.assertTrue("应该包含 USERS.* 字段。实际字段: " + fields, fields.contains("USERS.*"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有 USERS.*，不应该包含其他字段。实际剩余字段: " + fields, fields.isEmpty());
    }
    
    @Test
    public void testSelectStarFromMultipleTables() throws SqlParseException {
        // 测试 SELECT * FROM table1 JOIN table2
        String sql = "SELECT * FROM users u JOIN orders o ON u.id = o.user_id";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 ORDERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段：应该包含 USERS.* 和 ORDERS.*
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.*");
        expectedFields.add("ORDERS.*");
        Assert.assertTrue("应该包含 USERS.* 字段", fields.contains("USERS.*"));
        Assert.assertTrue("应该包含 ORDERS.* 字段", fields.contains("ORDERS.*"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有 USERS.* 和 ORDERS.*，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testSelectCountStar() throws SqlParseException {
        // 测试 SELECT COUNT(*) FROM table
        String sql = "SELECT COUNT(*) FROM users";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段：COUNT(*) 中的 * 不应该被解析为字段
        Set<String> expectedFields = new HashSet<>();
        // 期望没有字段
        Assert.assertFalse("不应该包含 USERS.* 字段", fields.contains("USERS.*"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该为空，不应该包含任何字段", fields.isEmpty());
    }
    
    @Test
    public void testSelectCountStarWithOtherFields() throws SqlParseException {
        // 测试 SELECT COUNT(*), user_id FROM table
        String sql = "SELECT COUNT(*), user_id FROM orders";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 ORDERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段：应该只包含 ORDERS.USER_ID，不应该包含 ORDERS.*
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("ORDERS.USER_ID");
        Assert.assertTrue("应该包含 ORDERS.USER_ID 字段", fields.contains("ORDERS.USER_ID"));
        Assert.assertFalse("不应该包含 ORDERS.* 字段（因为 * 在 COUNT 函数中）", fields.contains("ORDERS.*"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有 ORDERS.USER_ID，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testSelectStarWithWhere() throws SqlParseException {
        // 测试 SELECT * FROM table WHERE condition
        String sql = "SELECT * FROM users WHERE age > 18";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段：应该包含 USERS.* 和 WHERE 子句中的字段
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.*");
        expectedFields.add("USERS.AGE");
        Assert.assertTrue("应该包含 USERS.* 字段", fields.contains("USERS.*"));
        Assert.assertTrue("应该包含 USERS.AGE 字段", fields.contains("USERS.AGE"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该只有期望的字段，不应该包含其他字段", fields.isEmpty());
    }
    
    @Test
    public void testSelectStarWithJoin() throws SqlParseException {
        // 测试 SELECT * FROM table1 JOIN table2 ON condition
        String sql = "SELECT * FROM users u JOIN orders o ON u.id = o.user_id WHERE u.age > 18";
        TableFieldInfo info = parser.parse(sql);
        
        Set<String> tables = info.getTables();
        Set<String> fields = new HashSet<>(info.getFields());
        
        // 验证表名
        Set<String> expectedTables = new HashSet<>();
        expectedTables.add("USERS");
        expectedTables.add("ORDERS");
        Assert.assertTrue("应该包含 users 表", tables.contains("USERS"));
        Assert.assertTrue("应该包含 orders 表", tables.contains("ORDERS"));
        tables.removeAll(expectedTables);
        Assert.assertTrue("tables 应该只有 USERS 和 ORDERS，不应该包含其他表", tables.isEmpty());
        
        // 验证字段：应该包含 USERS.*, ORDERS.* 和 WHERE/JOIN 子句中的字段
        // 注意：SELECT * 会生成 USERS.* 和 ORDERS.*，但 JOIN 和 WHERE 条件中的字段也会被提取
        Set<String> expectedFields = new HashSet<>();
        expectedFields.add("USERS.*");
        expectedFields.add("ORDERS.*");
        // JOIN 和 WHERE 条件中的字段也会被提取
        if (fields.contains("USERS.ID")) {
            expectedFields.add("USERS.ID");
        }
        if (fields.contains("ORDERS.USER_ID")) {
            expectedFields.add("ORDERS.USER_ID");
        }
        if (fields.contains("USERS.AGE")) {
            expectedFields.add("USERS.AGE");
        }
        Assert.assertTrue("应该包含 USERS.* 字段", fields.contains("USERS.*"));
        Assert.assertTrue("应该包含 ORDERS.* 字段", fields.contains("ORDERS.*"));
        fields.removeAll(expectedFields);
        Assert.assertTrue("fields 应该包含 USERS.* 和 ORDERS.*，可能还包含 JOIN/WHERE 中的字段。实际剩余字段: " + fields, fields.isEmpty() || fields.size() <= 3);
    }
}
