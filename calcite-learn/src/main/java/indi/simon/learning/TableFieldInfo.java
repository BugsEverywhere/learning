package indi.simon.learning;

import java.util.HashSet;
import java.util.Set;

/**
 * 存储表和字段信息的数据结构
 */
public class TableFieldInfo {
    /**
     * 表名集合（包含 schema.table 格式）
     */
    private Set<String> tables = new HashSet<>();
    
    /**
     * 字段信息集合，格式为 "table.field" 或 "schema.table.field"
     */
    private Set<String> fields = new HashSet<>();
    
    public void addTable(String tableName) {
        if (tableName != null && !tableName.isEmpty()) {
            tables.add(tableName);
        }
    }
    
    public void addField(String fieldName) {
        if (fieldName != null && !fieldName.isEmpty()) {
            // 避免添加不完整的字段引用（如 "table."），但允许 "table.*" 格式
            if (fieldName.endsWith(".") && !fieldName.endsWith(".*")) {
                return;
            }
            fields.add(fieldName);
        }
    }
    
    public Set<String> getTables() {
        return tables;
    }
    
    public Set<String> getFields() {
        return fields;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tables: ").append(tables).append("\n");
        sb.append("Fields: ").append(fields);
        return sb.toString();
    }
}
