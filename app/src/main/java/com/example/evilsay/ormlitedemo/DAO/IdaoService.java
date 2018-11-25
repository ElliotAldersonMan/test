package com.example.evilsay.ormlitedemo.DAO;


import java.sql.SQLException;
import java.util.List;

/**
 * 数据访问接口
 * @param <T>
 * @param <Z>
 */
public interface IdaoService<T,Z> {
    /**
     * 添加
     * @param t
     * @return
     * @throws SQLException
     */
     int save(T t) throws SQLException;

    /**
     * 删除
     * @param t
     * @return
     * @throws SQLException
     */
     int delete(T t)throws SQLException;

    /**
     * 修改
     * @param t
     * @return
     * @throws SQLException
     */
     int update(T t) throws SQLException;

    /**
     * 查询
     * @param z
     * @return
     * @throws SQLException
     */
    T queryByid(Z z) throws SQLException;

    /**
     * 查询所有
     * @return
     * @throws SQLException
     */
    List<T> queryAll () throws SQLException;
}
