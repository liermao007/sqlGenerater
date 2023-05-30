package org.xian.utils;

import org.xian.exception.ParamException;
import org.xian.sql.Table;
import org.xian.sql.TableColumn;

import java.util.*;

/**
 * @author lgx
 */
public class TableUtils {

    private static Map<String, Table> tableMap = new HashMap<>();

    static {
        tableMap.put("test1", new Table("test1", "test1"));
        tableMap.put("test2", new Table("test2", "test2"));
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
