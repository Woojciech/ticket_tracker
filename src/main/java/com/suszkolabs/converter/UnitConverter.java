package com.suszkolabs.converter;

import com.suszkolabs.dao.TicketDAO;
import com.suszkolabs.dao.UnitDAO;
import com.suszkolabs.entity.Unit;
import com.suszkolabs.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.lang.annotation.Annotation;

public class UnitConverter implements Converter<String, Unit> {

    @Autowired
    private UnitService unitService;

    @Override
    public Unit convert(String source) {
        int unitId = Integer.parseInt(source);
        return unitService.findUnitById(unitId);
    }
}
