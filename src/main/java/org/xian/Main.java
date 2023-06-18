package org.xian;

import cn.hutool.db.Entity;
import cn.hutool.db.sql.SqlBuilder;
import org.xian.utils.SQLExecutor;

import java.sql.SQLException;
import java.util.List;

/**
 * @author lgx
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        String dataJson = """
                {
                    params: {
                        su1: 'admin',
                    },
                    results: ['su1', 'su3'],
                    tables: [{key: 'su'}, {key: 'sur', s: 'su'}]
                }
                """;
        long l = System.currentTimeMillis();
        SqlBuilder sql = SqlGenerator.select(dataJson).sqlBuilder();

        System.out.println(sql);
        List<Entity> entities = SQLExecutor.queryList(Entity.class, sql);
        System.out.println(System.currentTimeMillis() - l);
        System.out.println(sql);
    }
}