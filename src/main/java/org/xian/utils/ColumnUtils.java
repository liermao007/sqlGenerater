package org.xian.utils;

import org.xian.exception.ParamException;
import org.xian.sql.TableColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lgx
 */
public class ColumnUtils {

    private static Map<String, TableColumn> columnMap = new HashMap<>();

    static {
        columnMap.put("title", new TableColumn("test1", "title", "title"));
        columnMap.put("name", new TableColumn("test1", "name", "name"));
        columnMap.put("sex", new TableColumn("test1", "sex", "sex"));
    }

    public static List<TableColumn> getTableColumns(List<String> columnKeys) {
        List<TableColumn> columns = new ArrayList<>(columnKeys.size());
        for (String columnKey : columnKeys) {
            if(!columnMap.containsKey(columnKey)) {
                throw new ParamException("数据库不存在对应参数数据");
            }
            columns.add(columnMap.get(columnKey));
        }
        return columns;
    }
}
