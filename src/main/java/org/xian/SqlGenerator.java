package org.xian;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.sql.Condition;
import cn.hutool.db.sql.SqlBuilder;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.xian.enums.OperTypeEnum;
import org.xian.sql.Table;
import org.xian.sql.TableColumn;
import org.xian.sql.TableParam;
import org.xian.sql.TableRelevance;
import org.xian.utils.ColumnUtils;
import org.xian.utils.TableRelevanceUtils;
import org.xian.utils.TableUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * sql 生成器
 * @author lgx
 */
public class SqlGenerator {

    private OperTypeEnum type = null;
    private Map<String, String> params = new HashMap<>();

    private List<String> resultColumns = new ArrayList<>();

    private List<TableParam> tableParams = new ArrayList<>();

    private Set<String> tableKeys = new HashSet<>();

    private List<TableColumn> selectColumns = new ArrayList<>();
    private List<TableColumn> whereColumns = new ArrayList<>();

    public static SqlGenerator select(String jsonStr) {
        JSON json = JSONUtil.parse(jsonStr);
        SqlGenerator generator = new SqlGenerator();
        generator.type = OperTypeEnum.SELECT;
        generator.params = json.getByPath("params", Map.class);
        generator.resultColumns = json.getByPath("results", List.class);
        List<JSONObject> params = json.getByPath("tables", List.class);
        for (JSONObject param : params) {
            generator.tableParams.add(param.toBean(TableParam.class));
        }

        generator.selectColumns = ColumnUtils.getTableColumns(generator.resultColumns);
        generator.whereColumns = ColumnUtils.getTableColumns(generator.params.keySet());
        generator.tableKeys = Stream.concat(generator.selectColumns.stream(), generator.whereColumns.stream())
                .map(TableColumn::getTableKey)
                .collect(Collectors.toSet());

        return generator;
    }

    public SqlBuilder sqlBuilder() {
        return switch (type) {
            case SELECT -> selectSqlBuilder();
            case INSERT, DELETE, DROP, UPDATE, CREATE -> null;
        };
    }

    private SqlBuilder selectSqlBuilder() {
        SqlBuilder sqlBuilder = SqlBuilder.create();
        handleSelect(sqlBuilder).handleFrom(sqlBuilder).handleWhere(sqlBuilder);
        return sqlBuilder;
    }

    private SqlGenerator handleSelect(SqlBuilder sqlBuilder) {
        String[] queryColumns = new String[selectColumns.size()];
        for (int i = 0; i < selectColumns.size(); i++) {
            TableColumn column = selectColumns.get(i);
            queryColumns[i] = column.getTableKey() + StrPool.DOT + column.getName() + " as '" + column.getColumnKey() + "'";
        }
        sqlBuilder.select(queryColumns);
        return this;
    }

    private SqlGenerator handleFrom(SqlBuilder sqlBuilder) {
        Set<String> keys = tableParams.stream().map(TableParam::getKey).collect(Collectors.toSet());
        List<Table> tables = TableUtils.getTable(keys);
        Map<String, Table> tableMap = tables.stream().collect(Collectors.toMap(Table::getKey, obj -> obj));
        for (TableParam tableParam : tableParams) {
            if(StrUtil.isNotBlank(tableParam.getS())) {
                sqlBuilder.join(tableMap.get(tableParam.getKey()).getName() + " " + tableParam.getKey(), SqlBuilder.Join.INNER);
                TableRelevance relevance = TableRelevanceUtils.getRelevance(tableMap.get(tableParam.getKey()).getName(), tableMap.get(tableParam.getS()).getName());
                sqlBuilder.on(tableParam.getKey() + StrPool.DOT + relevance.getSourceColumn() + "=" + tableParam.getS() + StrPool.DOT + relevance.getLinkColumn());
            } else {
                sqlBuilder.from(tableMap.get(tableParam.getKey()).getName() + " " + tableParam.getKey());
            }
        }
        return this;
    }

    private SqlGenerator handleWhere(SqlBuilder sqlBuilder) {
        List<Condition> conditions = new ArrayList<>(whereColumns.size());
        for (TableColumn column : whereColumns) {
            conditions.add(new Condition(column.getTableKey() + StrPool.DOT + column.getName(), params.get(column.getColumnKey())));
        }
        sqlBuilder.where(conditions.toArray(Condition[]::new));
        return this;
    }
}
