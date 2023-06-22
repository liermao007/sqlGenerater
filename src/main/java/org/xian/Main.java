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
        testDelete();



    }

    public static void testSelect() {
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

    private static void testDelete() {
        String dataJson = """
                {
                    params: {
                        su1: 'a',
                    }
                }
                """;
        SqlGenerator sqlGenerator = SqlGenerator.delete(dataJson);
        SqlBuilder sql = sqlGenerator.sqlBuilder();
        System.out.println(sql.build());

        int result = SQLExecutor.execute(sql);
        System.out.println(result);
    }
}