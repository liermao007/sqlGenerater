package com.xian.generator.sql;

/**
 * @author lgx
 */
public class TableColumn {

    private String tableKey;

    private String tableName;
    private String columnKey;
    private String columnName;
    private String comment;

    public TableColumn() {
    }

    public TableColumn(String tableKey, String columnKey, String columnName) {
        this.tableKey = tableKey;
        this.columnKey = columnKey;
        this.columnName = columnName;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
