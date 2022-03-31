package com.suszkolabs.entity;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "unit")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //TODO set up fetch types
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "relatedUnit")
    private List<Ticket> relatedTickets;

    public Unit(){

    }


    public Unit(String name, String description){
        this.name = name;
        this.description = description;
    }

    public void addTicket(Ticket ticket){
        Optional.ofNullable(ticket).ifPresent( t -> relatedTickets.add(t));
    }

    public List<Ticket> getRelatedTickets() {
        return relatedTickets;
    }

    public void setRelatedTickets(List<Ticket> relatedTickets) {
        this.relatedTickets = relatedTickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
