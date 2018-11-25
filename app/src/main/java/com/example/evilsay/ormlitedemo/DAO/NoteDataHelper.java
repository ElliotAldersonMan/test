package com.example.evilsay.ormlitedemo.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.evilsay.ormlitedemo.Bean.Author;
import com.example.evilsay.ormlitedemo.Bean.NoteDataBase;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class NoteDataHelper extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "test";
    private static final Integer VERSION = 5;
    private static NoteDataHelper sInstance;

    private NoteDataHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }
    public static synchronized NoteDataHelper getsInstance(Context context){
        if (sInstance == null) {
            sInstance = new NoteDataHelper(context.getApplicationContext());
        }
        return sInstance;

    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,NoteDataBase.class);
            TableUtils.createTable(connectionSource, Author.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,NoteDataBase.class,true);
            TableUtils.dropTable(connectionSource,Author.class,true);
            onCreate(database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
        DaoManager.clearDaoCache();
        connectionSource.close();
        OpenHelperManager.releaseHelper();
    }
}
