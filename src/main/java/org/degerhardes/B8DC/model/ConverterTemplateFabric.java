package org.degerhardes.B8DC.model;

public class ConverterTemplateFabric {
    public ConverterTemplate generateTemplate(String inc){
        switch (inc) {
            case "BB" : return new ConverterTemplateBB();
            case "B8" : return new ConverterTemplateB8();
            default: throw new IllegalArgumentException(inc);
        }
    }
}
