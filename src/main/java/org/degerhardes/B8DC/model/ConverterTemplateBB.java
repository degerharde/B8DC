package org.degerhardes.B8DC.model;


import static org.degerhardes.B8DC.model.TemplateStaticMethods.*;
import static org.degerhardes.B8DC.model.TemplateStaticMethods.calculateTiValue;


public class ConverterTemplateBB implements ConverterTemplate {
    @Override
    public String convert(String[] arr) {
        int tiCount = getTILength(arr[10]);
        boolean tiDigitCap = getTIDigitCap(arr[10]);
        int tsCount = convertToDec(arr[11]) * 8;
        int tiiCount = convertToDec(arr[12]);
        int curPos = 12;
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]).append(" ").append(arr[1]).append(" - длинна пакета: ").append(convertToDec(arr[1])).append(" байт\n")
                .append(arr[2]).append(" - адрес получателя (ПУ): ").append(convertToDec(arr[2])).append("\n")
                .append(arr[3]).append(" - адрес отправителя (КП): ").append(convertToDec(arr[3])).append("\n")
                .append(arr[4]).append(" - полученная команда\n")
                .append(arr[5]).append(arr[5].equals("00") ? " - ошибок нет\n" : " - ошибка в пакете;\n")
                .append(calculateDate(arr[6], arr[7], arr[8], arr[9]))
                .append(arr[10]).append(" - количество параметров ТИ: ").append(tiCount)
                .append(tiDigitCap ? ", разрядность в 1 байт\n" : ", разрядность в 2 байта\n")
                .append(arr[11]).append(" - количество параметров ТС: ").append(tsCount).append("\n")
                .append(arr[12]).append(" - количество параметров ТИИ: ").append(tiiCount).append("\n")
                .append("Значение параметров ТИ:\n");
        for (int i = 1; i <= tiCount; i++) {
            if (!tiDigitCap) sb.append(calculateTiValue(i, arr[++curPos], arr[++curPos]));
            else sb.append(calculateTiValue(i, arr[++curPos]));
        }
        sb.append("Значения параметров ТС:\n");
        for (int its = 0; its < tsCount;){
            sb.append(arr[++curPos]).append(" - ");
            for (int el : getBits(arr[curPos])){
                sb.append(++its).append(": ").append(el != 0).append(", ");
            }
            sb.deleteCharAt(sb.length()-2).append("\n");
        }
        sb.append("Значение параметров ТИИ:\n");
        for (int i = 1; i <= tiiCount; ++i) {
            sb.append(calculateTiValue(i, arr[++curPos], arr[++curPos], arr[++curPos], arr[++curPos]));
        }
        sb.append("Маски достоверности ТИ:\n");
        for (int i = 0; i < tiCount;){
            sb.append(arr[++curPos]).append(" - ");
            for (int el : getBits(arr[curPos])){
                if (i >= tiCount) break;
                sb.append(++i).append(": ").append(el != 0).append(", ");
            }
            sb.deleteCharAt(sb.length()-2).append("\n");
        }
        sb.append("Маски достоверности ТС:\n");
        for (int i = 0; i < tsCount;){
            sb.append(arr[++curPos]).append(" - ");
            for (int el : getBits(arr[curPos])){
                if (i >= tsCount) break;
                sb.append(++i).append(": ").append(el != 0).append(", ");
            }
            sb.deleteCharAt(sb.length()-2).append("\n");
        }
        sb.append("Маски достоверности ТИИ:\n");
        for (int i = 0; i < tiiCount;){
            sb.append(arr[++curPos]).append(" - ");
            for (int el : getBits(arr[curPos])){
                if (i >= tiiCount) break;
                sb.append(++i).append(": ").append(el != 0).append(", ");
            }
            sb.deleteCharAt(sb.length()-2).append("\n");
        }
        sb.append(arr[++curPos]).append(" ").append(arr[++curPos]).append(" - CRC 1, 2");
        return sb.toString();
    }
}
