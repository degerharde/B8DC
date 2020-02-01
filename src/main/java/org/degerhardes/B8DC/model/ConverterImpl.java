package org.degerhardes.B8DC.model;

import org.springframework.stereotype.Component;

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
        catch (Exception e) {
            if (e.getLocalizedMessage().equals("4")) return "Короткий пакет";
            return "Ошибка разбора пакета:\n"+e.toString();}
    }


    private String[] stringToArray(String incString){
        String res = incString.strip().replaceAll("0x", "");
        return res.substring(res.indexOf("55 55 55 55 55")+15).split(" ");
    }
}
