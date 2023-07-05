package com.xian.generator.utils;

import com.xian.generator.exception.ParamException;
import com.xian.generator.sql.Table;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lgx
 */
public class TableUtils {

    private static Map<String, Table> tableMap;

    static {
        List<Table> tables = SQLExecutor.queryList(Table.class, "select table_key as 'key', table_name as 'name', table_comment as 'comment' from config_table where del_flag = 0");
        tableMap = tables.stream()
                .collect(Collectors.toMap(Table::getKey, t -> t));
    }

    public static Table getTable(String tableKey) {
        if(!tableMap.containsKey(tableKey)) {
            throw new ParamException("数据库不存在对应参数数据");
        }
        return tableMap.get(tableKey);
    }

    public static List<Table> getTable(Collection<String> tableKeys) {
        List<Table> tables = new ArrayList<>();
        for (String tableKey : tableKeys) {
            if(!tableMap.containsKey(tableKey)) {
                throw new ParamException("数据库不存在对应参数数据");
            }
            tables.add(tableMap.get(tableKey));
        }
        return tables;
    }
}
