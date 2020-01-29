package com.codeup.deimosspringblog.models;

import javax.persistence.*;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String date;

    @Column
    private String time;

    @Column
    private String notes;

    public Task(){}

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getNotes(){
        return notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

}
