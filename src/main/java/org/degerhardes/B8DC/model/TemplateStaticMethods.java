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

    static int getTIDigitCap(String s){
        int res;
        int[] tmp = getBits(s);
        if (tmp[6] == 0 && tmp[7] == 0) res = 8;
        else res = tmp[6] != 0 ? 10 : 12;
        return res;
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

    static String calculateDate(String[] arr, int curPos){
        StringBuilder sb = new StringBuilder();
        int pos = curPos;
        for (int i = 0; i < 4; i ++){
            sb.append(arr[pos++]).append(" ");
        }
        int[] incBytes = {
                Integer.parseInt(arr[curPos++],16),
                Integer.parseInt(arr[curPos++],16),
                Integer.parseInt(arr[curPos++],16),
                Integer.parseInt(arr[curPos++],16)
        };
        long res = 0;
        for (int i = incBytes.length-1; i >= 0; i--){
            res |= incBytes[i];
            if (i != 0) res <<= 8;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(res * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-y / HH:mm:ss / z Z");
        sb.append("- временная метка архива: ").append(sdf.format(calendar.getTime()));
        return sb.toString();
    }
}