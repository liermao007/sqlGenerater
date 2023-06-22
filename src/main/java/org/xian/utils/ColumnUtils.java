package org.xian.utils;

import org.xian.exception.ParamException;
import org.xian.sql.Table;
import org.xian.sql.TableColumn;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lgx
 */
public class ColumnUtils {

    private static Map<String, TableColumn> columnMap;

    static {
        List<TableColumn> tables = SQLExecutor.queryList(TableColumn.class, "select table_key as 'tableKey', table_name as 'tableName', column_key as 'columnKey', column_name as 'columnName', column_comment as 'comment' from config_column where del_flag = 0");
        columnMap = tables.stream()
                .collect(Collectors.toMap(TableColumn::getColumnKey, t -> t));
    }

    public static List<TableColumn> getTableColumns(Collection<String> columnKeys) {
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
