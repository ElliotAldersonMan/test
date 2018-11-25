package com.example.evilsay.ormlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.evilsay.ormlitedemo.Bean.Author;
import com.example.evilsay.ormlitedemo.Bean.NoteDataBase;
import com.example.evilsay.ormlitedemo.DAO.IDaoServiceImpl;
import com.example.evilsay.ormlitedemo.DAO.IdaoService;
import com.example.evilsay.ormlitedemo.DAO.NoteDataHelper;
import com.example.evilsay.ormlitedemo.Fragment.FragmentALLNote;
import com.example.evilsay.ormlitedemo.Fragment.FragmentAdd;
import com.example.evilsay.ormlitedemo.Fragment.FragmentME;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    protected FragmentAdd fragmentAdd = new FragmentAdd();
    protected FragmentME  fragmentME  = new FragmentME();
    protected FragmentALLNote fragmentALLNote = new FragmentALLNote();
    protected LinearLayout all_note,add_note,me_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        FragmentView();
        InitView();
//        SpecifiedQuery();
    }
    private void FragmentView(){
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_menu,fragmentAdd)
                .add(R.id.main_menu,fragmentALLNote)
                .add(R.id.main_menu,fragmentME)
                .hide(fragmentME)
                .hide(fragmentAdd)
                .commit();
    }
    private void InitView(){
        all_note = findViewById(R.id.all_note);
        add_note = findViewById(R.id.add_note);
        me_note  = findViewById(R.id.me_note);
        all_note.setOnClickListener(this);
        add_note.setOnClickListener(this);
        me_note .setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.all_note:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(fragmentALLNote)
                        .hide(fragmentME)
                        .hide(fragmentAdd)
                        .commit();
                break;
            case R.id.add_note:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(fragmentAdd)
                        .hide(fragmentME)
                        .hide(fragmentALLNote)
                        .commit();
                break;
            case R.id.me_note:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(fragmentME)
                        .hide(fragmentAdd)
                        .hide(fragmentALLNote)
                        .commit();
                break;
        }
    }
    public void SpecifiedQuery(){
        IdaoService<Author,Integer> idaoService = new IDaoServiceImpl<>(this,Author.class);
        IdaoService<NoteDataBase,Integer> idaoServiceIdaoService = new IDaoServiceImpl<>(this,NoteDataBase.class);
        try {
            List<Author> authors = idaoService.queryAll();
            List<NoteDataBase> baseList = idaoServiceIdaoService.queryAll();
            for (NoteDataBase noteDataBase : baseList){
                Log.i(TAG, "文章" + noteDataBase.toString()+"作者"+noteDataBase.getAuthor());
            }
            for (Author author : authors) {
                Log.i(TAG, "作者 " + author.getName() );
                for (NoteDataBase base : author.getNoteDataBases()) {
                    Log.i(TAG, "文章" +  base.getTitle() );
                }
            }
        } catch (SQLException e) {
           
        }
    }
}
