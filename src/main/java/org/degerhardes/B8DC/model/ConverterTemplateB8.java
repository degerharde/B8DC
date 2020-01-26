package org.degerhardes.B8DC.model;
import java.util.ArrayList;
import java.util.List;

import static org.degerhardes.B8DC.model.TemplateStaticMethods.*;


public class ConverterTemplateB8 implements ConverterTemplate {
    private boolean answerFormat;
    private int tiDigitCap;
    private int tiCount;
    private int tsCount;
    private int tiiCount;
    private int curPos;
    private List<Integer> tiArchive;
    private List<Integer> tiiArchive;

    @Override
    public String convert(String[] arr) {
        answerFormat = getBits(arr[6])[0] == 0;
        tiDigitCap = getTIDigitCap(arr[7]);
        tiCount = getTILength(arr[7]);
        tsCount = convertToDec(arr[8]) * 8;
        tiiCount = convertToDec(arr[9]);
        curPos = 10;
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]).append(" ").append(arr[1]).append(" - LI пакета\n")
            .append(arr[2]).append(" - адрес получателя (ПУ): ").append(convertToDec(arr[2])).append("\n")
            .append(arr[3]).append(" - адрес отправителя (КП): ").append(convertToDec(arr[3])).append("\n")
            .append(arr[4]).append(" - полученная команда\n")
            .append(arr[5]).append(arr[5].equals("00") ? " - ошибок нет\n" : " - ошибка в пакете;\n");
        if (answerFormat) {
            sb.append(arr[6]).append(" - формат вывода записей архива по умолчанию ")
                .append(getBits(arr[6])[7] != 0 ? ". Переполнение буфера обмена\n" : "\n");
        }
        else {
            sb.append(arr[6]).append(" - формат вывода записей архива без временных меток со 2й записи ")
                .append(getBits(arr[6])[7] != 0 ? ".  буфера обмена\n" : "\n");
        }
        sb.append(arr[7]).append(" - количество параметров ТИ: ").append(tiCount)
            .append(", разрядность: ").append(tiDigitCap).append("\n")
            .append(arr[8]).append(" - количество параметров ТС: ").append(tsCount).append("\n")
            .append(arr[9]).append(" - количество параметров ТИИ: ").append(tiiCount).append("\n")
            .append(calcArchive(arr,true)).append(" параметры ТИ\n")
            .append(calcArchive(arr, false)).append(" параметры ТИИ\n");
        for (int i = 1; curPos < arr.length - 6; i++) {
            sb.append("Архив № ").append(i).append("\n")
                .append(calculateDate(arr, curPos));
            curPos += 4;

        }
        return sb.toString();
    }

    private String calcArchive(String[] arr, boolean isTi){
        int count = isTi ? tiCount : tiiCount;
        List<Integer> list = new ArrayList<>(count);
        StringBuilder sb = new StringBuilder();
        int pos = curPos;
        for (int i = 0; i < count; i += 8){
            sb.append(arr[pos++]).append(" ");
        }
        for (int i = 0; i < count; i += 8){
            list.addAll(archiveMask(arr[curPos++], i+1));
        }
        if (isTi) tiArchive = list;
        else tiiArchive = list;
        sb.append("- архивируются: ").append(list.toString());
        return sb.toString();
    }


}
