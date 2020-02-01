package org.degerhardes.B8DC.model;

public class ConverterTemplateFabric {
    public ConverterTemplate generateTemplate(String inc){
        switch (inc) {
            case "BB" :
            case "3A" :
                return new ConverterTemplateBB();
            case "B8" :
                return new ConverterTemplateB8();
            default: throw new IllegalArgumentException(inc);
        }
    }
}
