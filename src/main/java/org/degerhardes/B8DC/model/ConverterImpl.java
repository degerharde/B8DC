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
        try {
            command = stringToArray(inc);
            converterTemplate = new ConverterTemplateFabric().generateTemplate(command[4]);
            converterTemplate.convert(command);
            return Arrays.toString(command);
        } catch (StringIndexOutOfBoundsException e) {return "Некорректный пакет";}
    }


    private String[] stringToArray(String incString){
        return incString.trim().replaceAll("0x", "").substring(incString.indexOf("55 55 55 55 55")+16).split(" ");
    }
}
