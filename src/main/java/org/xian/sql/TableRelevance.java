package org.xian.sql;

/**
 * @author lgx
 */
public class TableRelevance {

    private String sourceTable;
    private String sourceColumn;
    private String linkTable;
    private String linkColumn;
    private String linkType;

    public TableRelevance() {
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    public String getSourceColumn() {
        return sourceColumn;
    }

    public void setSourceColumn(String sourceColumn) {
        this.sourceColumn = sourceColumn;
    }

    public String getLinkTable() {
        return linkTable;
    }

    public void setLinkTable(String linkTable) {
        this.linkTable = linkTable;
    }

    public String getLinkColumn() {
        return linkColumn;
    }

    public void setLinkColumn(String linkColumn) {
        this.linkColumn = linkColumn;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }
}
