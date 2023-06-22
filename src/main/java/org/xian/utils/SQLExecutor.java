package org.xian.utils;

import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.handler.BeanHandler;
import cn.hutool.db.handler.BeanListHandler;
import cn.hutool.db.sql.SqlBuilder;
import cn.hutool.db.sql.SqlExecutor;
import org.xian.exception.QueryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * sql执行
 *
 * @author lgx
 */
public class SQLExecutor {

    public static List<Entity> queryList(String sql, Object... params) {
        return queryList(Entity.class, sql, params);
    }

    public static <T> List<T> queryList(Class<T> clazz, SqlBuilder sqlBuilder) {
        try (Connection connection = getConnection()) {
            return SqlExecutor.query(connection, sqlBuilder, new BeanListHandler<>(clazz));
        } catch (SQLException e) {
            throw new QueryException("查询异常", e);
        }
    }

    public static Entity queryOne(String sql, Object... params) {
        return queryOne(Entity.class, sql, params);
    }

    public static <T> List<T> queryList(Class<T> clazz, String sql, Object... params) {
        try (Connection connection = getConnection()) {
            return SqlExecutor.query(connection, sql, new BeanListHandler<>(clazz), params);
        } catch (SQLException e) {
            throw new QueryException("查询异常", e);
        }
    }

    public static <T> T queryOne(Class<T> clazz, String sql, Object... params) {
        try (Connection connection = getConnection()) {
            return SqlExecutor.query(connection, sql, new BeanHandler<>(clazz), params);
        } catch (SQLException e) {
            throw new QueryException("查询异常", e);
        }
    }

    public static int execute(SqlBuilder sql) {
        try (Connection connection = getConnection()) {
            return SqlExecutor.execute(connection, sql.build(), sql.getParamValueArray());
        } catch (SQLException e) {
            throw new QueryException("执行SQL异常", e);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DSFactory.get().getConnection();
    }
}
