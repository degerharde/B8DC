package org.degerhardes.B8DC.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

class TemplateStaticMethods {
    static int convertToDec(String s){
        return Integer.parseInt(s, 16);
    }

    static int[] getBits(String s){
        int[] tmp = new int[8];
        int num = convertToDec(s), bit = 1;
        for (int i = 0; i < 8; ++i){
            tmp[i] = num & bit;
            bit <<= 1;
        }
        return tmp;
    }

    static int getTILength(String s){
        return Arrays.stream(getBits(s)).limit(5).sum();
    }

    static boolean getTIDigitCap(String s){
        int[] tmp = getBits(s);
        return tmp[6] == 0 && tmp[7] == 0;
    }

    static List<Integer> archiveMask(String s, int startCount){
        List<Integer> tmp = new ArrayList<>(8);
        int num = convertToDec(s), bit = 1;
        for (int i = 0; i < 8; ++i){
            if ((num & bit) != 0) tmp.add(i + startCount);
            bit <<= 1;
        }
        return tmp;
    }

    static String calculateDate(String... arr){
        StringBuilder sb = new StringBuilder();
        int[] incBytes = new int[4];
        for (int i = 0; i < 4; i ++){
            sb.append(arr[i]).append(" ");
            incBytes[i] = Integer.parseInt(arr[i],16);
        }
        long res = 0;
        for (int i = incBytes.length-1; i >= 0; i--){
            res |= incBytes[i];
            if (i != 0) res <<= 8;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(res * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-y / HH:mm:ss (z Z)");
        sb.append("- временная метка архива: ").append(sdf.format(calendar.getTime())).append("\n");
        return sb.toString();
    }

    static String calculateTiValue(Integer index, String... arr){
        StringBuilder sb = new StringBuilder();
        int[] incBytes = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            sb.append(arr[i]).append(" ");
            incBytes[i] = Integer.parseInt(arr[i],16);
        }
        int res = 0;
        for (int i = incBytes.length-1; i >= 0; i--){
            res |= incBytes[i];
            if (i != 0) res <<= 8;
        }
        sb.append("- АЦП параметра № ").append(index).append(": ").append(res).append("\n");
        return sb.toString();
    }
}