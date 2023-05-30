package org.xian;

import cn.hutool.core.text.StrPool;
import cn.hutool.db.sql.Condition;
import cn.hutool.db.sql.SqlBuilder;
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
public class SqlGenerator {

    private OperTypeEnum type = null;
    private Map<String, String> params = new HashMap<>();

    private List<String> resultColumns = new ArrayList<>();

    public static SqlGenerator createSelect(String jsonStr) {
        JSON json = JSONUtil.parse(jsonStr);
        SqlGenerator generator = new SqlGenerator();
        generator.type = OperTypeEnum.SELECT;
        generator.params = json.getByPath("params", Map.class);
        generator.resultColumns = json.getByPath("results", List.class);
        return generator;
    }

    public SqlBuilder sqlBuilder() {
        return switch (type) {
            case SELECT -> selectSqlBuilder();
            case INSERT, DELETE, DROP, UPDATE, CREATE -> null;
        };
    }

    private SqlBuilder selectSqlBuilder() {
        List<TableColumn> tableColumns = ColumnUtils.getTableColumns(resultColumns);
        Set<String> tableKeys = new HashSet<>();
        String[] queryColumns = new String[tableColumns.size()];
        for (int i = 0; i < tableColumns.size(); i++) {
            TableColumn column = tableColumns.get(i);
            queryColumns[i] = column.getTableKey() + StrPool.DOT + column.getName() + " as '" + column.getColumnKey() + "'";
            tableKeys.add(column.getTableKey());
        }
        List<Table> tables = TableUtils.getTable(tableKeys);
        String[] queryTables = tables.stream()
                .map(table -> table.getName() + " " + table.getKey()).toArray(String[]::new);
        List<TableColumn> whereColumns = ColumnUtils.getTableColumns(params.keySet());
        Condition[] queryWheres = whereColumns.stream().map(column -> new Condition(column.getTableKey() + StrPool.DOT + column.getName(), params.get(column.getColumnKey()))).toArray(Condition[]::new);
        SqlBuilder sqlBuilder = SqlBuilder.create()
                .select(queryColumns)
                .from(queryTables)
                .where(queryWheres);
        return sqlBuilder;
    }
}
