package com.suszkolabs.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "id")
    private int id;

    @Column(name = "title")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String title;

    @Column(name = "description")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String description;

    @Column(name = "date_added")
    private String dateAdded;

    //TODO think about fetch type (eager, lazy etc)
    // surely dont want to remove the entire unit when removing the ticket
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "unit_id")
    private Unit relatedUnit;

    @Column(name = "is_completed")
    private boolean isCompleted;

    public Ticket(){

    }

    //TODO work on date formatting (time has to be displayed)
    public Ticket(String title, String description){
        this.title = title;
        this.description = description;
        setDateNow();
    }

    public void setDateNow(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date(System.currentTimeMillis()));
        this.dateAdded = date;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Unit getRelatedUnit() {
        return relatedUnit;
    }

    public void setRelatedUnit(Unit relatedUnit) {
        this.relatedUnit = relatedUnit;
    }
}
