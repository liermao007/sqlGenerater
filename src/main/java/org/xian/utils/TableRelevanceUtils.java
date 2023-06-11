package org.xian.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.xian.sql.TableRelevance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表关联关系
 * @author lgx
 */
public class TableRelevanceUtils {

    private static Table<String, String, TableRelevance> relevanceTable;
    private static Map<String, List<TableRelevance>> tableRelevanceMap;

    static {
        List<TableRelevance> tableRelevances = SQLExecutor.queryList(TableRelevance.class, "SELECT source_table AS 'sourceTable', source_column AS 'sourceColumn', link_table AS 'linkTable', link_column AS 'linkColumn', link_type AS 'linkType' FROM config_table_relevance where del_flag = 0");
        relevanceTable = HashBasedTable.create();
        tableRelevanceMap = new HashMap<>();
        for (TableRelevance tableRelevance : tableRelevances) {
            relevanceTable.put(tableRelevance.getSourceTable(), tableRelevance.getLinkTable(), tableRelevance);
            if(!tableRelevanceMap.containsKey(tableRelevance.getSourceTable())) {
                tableRelevanceMap.put(tableRelevance.getSourceTable(), new ArrayList<>());
            }
            tableRelevanceMap.get(tableRelevance.getSourceTable()).add(tableRelevance);
        }
    }

    public static TableRelevance getRelevance(String sourceTable, String linkTable) {
        return relevanceTable.get(sourceTable, linkTable);
    }
}
