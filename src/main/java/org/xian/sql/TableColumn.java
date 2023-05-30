package org.xian.sql;

/**
 * @author lgx
 */
public class TableColumn {

    private String tableKey;
    private String columnKey;
    private String name;
    private String comment;

    public TableColumn() {
    }

    public TableColumn(String tableKey, String columnKey, String name) {
        this.tableKey = tableKey;
        this.columnKey = columnKey;
        this.name = name;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
