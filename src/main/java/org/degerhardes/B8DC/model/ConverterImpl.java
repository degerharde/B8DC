package org.degerhardes.B8DC.model;

import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class ConverterImpl implements Converter{
    ConverterTemplate converterTemplate;
    String[] command;
    public String convertAndReturn(String inc) {
        try {
            command = stringToArray(inc);
            converterTemplate = new ConverterTemplateFabric().generateTemplate(command[4]);
            return converterTemplate.convert(command);
        }
        catch (StringIndexOutOfBoundsException e) {return "Некорректный пакет.";}
        catch (IllegalArgumentException e){return e.getMessage()+" команда не поддерживается.";}
        catch (Exception e) {return "Ошибка разбора пакета:\n"+e.toString();}
    }


    private String[] stringToArray(String incString){
        return incString.trim().replaceAll("0x", "").substring(incString.indexOf("55 55 55 55 55")+16).split(" ");
    }
}
