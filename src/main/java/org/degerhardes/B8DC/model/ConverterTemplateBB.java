package org.degerhardes.B8DC.model;


import java.util.Arrays;


public class ConverterTemplateBB implements ConverterTemplate {
    @Override
    public String convert(String[] arr) {
        System.out.println("BB command acquired");
        return Arrays.toString(arr);
    }
}
