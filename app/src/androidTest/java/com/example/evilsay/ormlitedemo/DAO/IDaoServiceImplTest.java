package com.example.evilsay.ormlitedemo.DAO;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.example.evilsay.ormlitedemo.Bean.Author;
import com.example.evilsay.ormlitedemo.Bean.NoteDataBase;

import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IDaoServiceImplTest {
    private static final String TAG = "IDaoTest";
    private Context context = InstrumentationRegistry.getTargetContext().getApplicationContext();
    private IDaoServiceImpl<NoteDataBase,Integer> daoService = new IDaoServiceImpl<>(context,NoteDataBase.class);
    private IDaoServiceImpl<Author,Integer> authorService    = new IDaoServiceImpl<>(context,Author.class);
    @Test
    public void save() throws SQLException {
        Author author     = new Author("Amy","程序员");
        NoteDataBase base = new NoteDataBase("Android单元测试3","测试成功3",author);
        NoteDataBase make = new NoteDataBase("python开发","测试成功3",author);
        authorService.save(author);
        daoService.save(base);
        daoService.save(make);
        assertNotNull(base);
    }

    @Test
    public void delete() throws SQLException{
        NoteDataBase base = daoService.queryByid(6);
        int delete = daoService.delete(base);
        Log.e(TAG, "delete: "+ delete);
    }

    @Test
    public void update() throws SQLException {
        NoteDataBase base = daoService.queryByid(5);
        base.setTitle("Android单元测试更改");
        base.setContent("更改成功");
        daoService.update(base);
        Log.e(TAG, "update: " + base.toString());
    }

    @Test
    public void queryByid() throws SQLException {
        List<NoteDataBase> baseList = new ArrayList<>();
        NoteDataBase base = daoService.queryByid(1);
        baseList.add(base);
        for (NoteDataBase base1 : baseList){
            assertNotNull(base1);
        }
    }

    @Test
    public void queryAll() throws SQLException{
        List<Author> authorList = authorService.queryAll();
        List<NoteDataBase> baseList = daoService.queryAll();
        for (NoteDataBase noteDataBase : baseList){
            Log.e(TAG, "queryAll: "+ "文章" + noteDataBase.toString() + "作者" + noteDataBase.getAuthor().toString());
        }
        for (Author author : authorList){
            Log.e(TAG, "queryAllauthor: "+ "作者" + author.toString() + "发表的文章" + author.getNoteDataBases().toString() );
            for (NoteDataBase base : baseList){
                Log.e(TAG, "queryAll: "+ base );
            }
        }
    }
}