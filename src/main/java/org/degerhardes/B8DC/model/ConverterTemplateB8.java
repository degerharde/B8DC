package org.degerhardes.B8DC.model;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.degerhardes.B8DC.model.TemplateStaticMethods.*;


public class ConverterTemplateB8 implements ConverterTemplate {
    private boolean answerFormat;
    private boolean tiDigitCap;
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
        curPos = 9;

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
            .append(", разрядность в 1 байт?: ").append(tiDigitCap).append("\n")
            .append(arr[8]).append(" - количество параметров ТС: ").append(tsCount).append("\n")
            .append(arr[9]).append(" - количество параметров ТИИ: ").append(tiiCount).append("\n")
            .append(calcArchive(arr,true)).append(" параметры ТИ\n")
            .append(calcArchive(arr, false)).append(" параметры ТИИ\n");
        int archSize = 4 + tiCount * 2 + tiiArchive.size()*5;
        archSize += tiDigitCap ? (tiCount*3) : (tiCount*2);
        for (int i = 1; curPos < arr.length - archSize; i++) {
            sb.append("\nАрхив № ").append(i).append("\n");
            if (answerFormat || i == 1) sb.append(calculateDate(arr[++curPos], arr[++curPos], arr[++curPos], arr[++curPos]));
            sb.append("Значение параметров ТИ:\n");
            for (Integer el : tiArchive){
                if (!tiDigitCap) sb.append(calculateTiValue(el, arr[++curPos], arr[++curPos]));
                else sb.append(calculateTiValue(el, arr[++curPos]));
            }
            sb.append("Значения всех ТС:\n");
            for (int its = 0; its < tsCount;){
                sb.append(arr[++curPos]).append(" - ");
                for (int el : getBits(arr[curPos])){
                    sb.append(++its).append(": ").append(el != 0).append(", ");
                }
                sb.deleteCharAt(sb.length()-2).append("\n");
            }
            sb.append("Значение параметров ТИИ:\n");
            for (Integer el : tiiArchive){
                sb.append(calculateTiValue(el, arr[++curPos], arr[++curPos], arr[++curPos], arr[++curPos]));
            }
            sb.append("Маски достоверности архивируемых ТИ:\n");
            for (int iti = 0; iti < tiArchive.size();){
                sb.append(arr[++curPos]).append(" - ");
                for (int el : getBits(arr[curPos])){
                    sb.append(++iti).append(": ").append(el != 0).append(", ");
                }
                sb.deleteCharAt(sb.length()-2).append("\n");
            }
            sb.append("Маски достоверности архивируемых ТИИ:\n");
            for (int iti = 0; iti < tiiArchive.size();){
                sb.append(arr[++curPos]).append(" - ");
                for (int el : getBits(arr[curPos])){
                    sb.append(++iti).append(": ").append(el != 0).append(", ");
                }
                sb.deleteCharAt(sb.length()-2).append("\n");
            }
            sb.append("Маски достоверности архивируемых ТС:\n");
            for (int its = 0; its < tsCount;){
                sb.append(arr[++curPos]).append(" - ");
                for (int el : getBits(arr[curPos])){
                    sb.append(++its).append(": ").append(el != 0).append(", ");
                }
                sb.deleteCharAt(sb.length()-2).append("\n");
            }
        }
        sb.append(arr[++curPos]).append(" ").append(arr[++curPos]).append(" - CRC 1, 2");
        return sb.toString();
    }

    private String calcArchive(String[] arr, boolean isTi){
        int count = isTi ? tiCount : tiiCount;
        List<Integer> list = new ArrayList<>(count);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i += 8){
            sb.append(arr[++curPos]).append(" ");
            list.addAll(archiveMask(arr[curPos], i+1));
        }
        if (isTi) tiArchive = list;
        else tiiArchive = list;
        sb.append("- архивируются: ").append(list.toString());
        return sb.toString();
    }
}
