package com.suszkolabs.utils;

import com.suszkolabs.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IdCheckboxWrapper {

    private List<Ticket> collectedIds;

    public IdCheckboxWrapper(){
        collectedIds = new ArrayList<>();
    }

    public void newId(Ticket ticket){
        collectedIds.add(ticket);
    }

    public List<Ticket> getCollectedIds() {
        return collectedIds;
    }

    public void setCollectedIds(List<Ticket> collectedIds) {
        this.collectedIds = collectedIds;
    }
}
