package org.xian;

import cn.hutool.core.text.StrPool;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.xian.enums.OperTypeEnum;
import org.xian.sql.Table;
import org.xian.sql.TableColumn;
import org.xian.utils.ColumnUtils;
import org.xian.utils.TableUtils;

import java.util.*;

/**
 * sql 生成器
 * @author lgx
 */
public class SqlGenerater {

    private Enum type = null;
    private Map<String, String> params = new HashMap<>();
    private List<String> resultColumns = new ArrayList<>();

    public static SqlGenerater createSelect(String jsonStr) {
        JSON json = JSONUtil.parse(jsonStr);
        SqlGenerater generater = new SqlGenerater();
        generater.type = OperTypeEnum.SELECT;
        generater.params = json.getByPath("params", Map.class);
        generater.resultColumns = json.getByPath("results", List.class);
        return generater;
    }

    public String sql() {
        List<TableColumn> tableColumns = ColumnUtils.getTableColumns(resultColumns);
        StringJoiner columnJoiner = new StringJoiner(StrPool.COMMA);
        Set<String> tableKeys = new HashSet<String>();
        for (TableColumn tableColumn : tableColumns) {
            columnJoiner.add(tableColumn.getTableKey() + StrPool.DOT + tableColumn.getName());
            tableKeys.add(tableColumn.getTableKey());
        }
        List<Table> tables = TableUtils.getTable(tableKeys);
        StringJoiner tableJoiner = new StringJoiner(StrPool.COMMA);
        for (Table table : tables) {
            tableJoiner.add(table.getKey() + StrPool.DOT + table.getName());
        }
        String sql = "select " + columnJoiner + " from " + tableJoiner;
        return sql;
    }
}
