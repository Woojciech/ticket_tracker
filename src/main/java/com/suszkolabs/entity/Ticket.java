package com.suszkolabs.entity;

import javax.persistence.*;
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
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date_added")
    private Date dateAdded;

    // surely dont want to remove the entire unit when removing the ticket
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Unit relatedUnit;

    public Ticket(){

    }

    public Ticket(String title, String description){
        this.title = title;
        this.description = description;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        this.dateAdded = date;
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Unit getRelatedUnit() {
        return relatedUnit;
    }

    public void setRelatedUnit(Unit relatedUnit) {
        this.relatedUnit = relatedUnit;
    }
}
