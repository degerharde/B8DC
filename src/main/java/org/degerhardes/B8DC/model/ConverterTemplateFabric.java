package org.degerhardes.B8DC.model;

public class ConverterTemplateFabric {
    public ConverterTemplate generateTemplate(String inc){
        switch (inc) {
            case "BB" :
            case "3A" :
                return new ConverterTemplateBB();
            case "B8" :
                return new ConverterTemplateB8();
            case "00" :
                return new ConverterTemplate80();
            case "A2" :
                return new ConverterTemplateA2();
            default: throw new IllegalArgumentException(inc);
        }
    }
}
