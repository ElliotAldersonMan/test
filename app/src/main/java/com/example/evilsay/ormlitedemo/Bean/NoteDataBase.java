package com.example.evilsay.ormlitedemo.Bean;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "note_tb")
public class NoteDataBase{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "content",canBeNull = false)
    private String content;
    //查询文章信息的时候顺带填充作者信息
    @DatabaseField(columnName = "author_id",foreign = true,foreignAutoRefresh = true)
    private Author author;

    public NoteDataBase(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public NoteDataBase() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NoteDataBase{" +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
