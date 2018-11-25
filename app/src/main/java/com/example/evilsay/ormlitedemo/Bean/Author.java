package com.example.evilsay.ormlitedemo.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DatabaseTable(tableName = "author_tb")
public class Author {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String annotation;
    //外键集合字段
    @ForeignCollectionField
    private Collection<NoteDataBase> noteDataBases;

    private List<NoteDataBase> baseList = new ArrayList<>();

    public Author(String name, String annotation) {
        this.name = name;
        this.annotation = annotation;
    }
    public Author() {
    }
    public List<NoteDataBase> getBaseList() {
        return baseList;
    }

    public void setBaseList(List<NoteDataBase> baseList) {
        this.baseList = baseList;
    }

    public Collection<NoteDataBase> getNoteDataBases() {
        return noteDataBases;
    }

    public void setNoteDataBases(Collection<NoteDataBase> noteDataBases) {
        this.noteDataBases = noteDataBases;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }


    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", annotation='" + annotation + '\'' +
                ", noteDataBases=" + noteDataBases +
                '}';
    }
}
