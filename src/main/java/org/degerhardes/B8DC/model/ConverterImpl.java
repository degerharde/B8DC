package org.degerhardes.B8DC.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ConverterImpl implements Converter{
    ConverterTemplate converterTemplate;
    String[] command;
    public String convertAndReturn(String inc) {
        command = stringToArray(inc);
        converterTemplate = new ConverterTemplateFabric().generateTemplate(command[4]);
        converterTemplate.convert(command);
        return null;
    }


    private String[] stringToArray(String incString){
        return incString.trim().substring(incString.indexOf("0x55 0x55 0x55 0x55 0x55")+25).replaceAll("0x", "").split(" ");
    }
}
