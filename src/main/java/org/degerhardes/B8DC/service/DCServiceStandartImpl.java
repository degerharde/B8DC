package org.degerhardes.B8DC.service;

import org.degerhardes.B8DC.model.Converter;
import org.springframework.beans.factory.annotation.Autowired;

public class DCServiceStandartImpl implements DCService{
    private Converter converter;

    @Autowired
    public DCServiceStandartImpl(Converter converter) {
        this.converter = converter;
    }

    @Override
    public String convertAndReturn(String inc) {
        return converter.convertAndReturn(inc);
    }
}
