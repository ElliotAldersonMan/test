package com.example.evilsay.ormlitedemo.DAO;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.DatabaseConnection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDaoServiceImpl<T,Z> implements IdaoService<T,Z> {
    private Context context;
    private Class<T> zclass;
//    缓存泛型DAO
    private Map<Class<T>,Dao<T,Z>> mDaoMap = new HashMap<Class<T>,Dao<T,Z>>();

    public IDaoServiceImpl(Context context,Class<T> aClass) {
        this.zclass = aClass;
        this.context = context;
    }
//    获取NoteDataHelper对象
    private Dao<T,Z> getDao() throws SQLException{
        Dao<T,Z> dao = mDaoMap.get(zclass);
        if (dao == null){
            dao = NoteDataHelper.getsInstance(context).getDao(zclass);
            mDaoMap.put(zclass,dao);
        }
        return dao;
    }

    /**
     * 增，带事务操作
     * @param t
     * @return 影响的行数
     * @throws SQLException
     */
    @Override
    public int save(T t) throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            int save = dao.create(t);
            dao.commit(connection);
            return save;
        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return 0;
    }

    /**
     * 删除
     * @param t
     * @return 影响的行数
     * @throws SQLException
     */
    @Override
    public int delete(T t) throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            int delete = dao.delete(t);
            dao.commit(connection);
            return delete;
        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return 0;
    }

    /**
     * 更新
     * @param t
     * @return 影响的行数
     * @throws SQLException
     */
    @Override
    public int update(T t) throws SQLException {
        Dao<T,Z> dao  = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            int update = dao.update(t);
            dao.commit(connection);
            return update;
        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return 0;
    }

    /**
     * 查询
     * @param z
     * @return 影响的行数
     * @throws SQLException
     */
    @Override
    public T queryByid(Z z) throws SQLException {
        Dao<T,Z> dao  = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            T t = dao.queryForId(z);
            dao.commit(connection);
            return t;
        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return null;
    }

    /**
     * 查询所有记录
     * @return
     * @throws SQLException
     */
    @Override
    public List<T> queryAll() throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            dao.commit(connection);
            return dao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
            dao.rollBack(connection);
        }finally {
            dao.endThreadConnection(connection);
        }
        return null;
    }
}
