package com.leagueofsummoners.model.utils;

import java.util.List;

/**
 * Created by isi on 18/05/2016.
 */
public class RiotUtils {
    public String[] parseRiotListToString(List<?> list) {
        String[] stringList = new String[list.size()];
        for (int i = 0; i < stringList.length; i++) {
            stringList[i] = list.get(i).toString();
        }
        return stringList;
    }
}
