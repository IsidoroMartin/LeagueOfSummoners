package com.leagueofsummoners.model.utils;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by juanj on 14/05/2016.
 */
public class CacheUtils {

    public static void setValuesToModelMap(HashMap<String,Object> mapToSet, ModelMap map, HttpSession session){
        for (Map.Entry<String, Object> entry : mapToSet.entrySet())
        {
        	if(entry.getKey() != null && entry.getValue() != null)
            map.put(entry.getKey(),entry.getValue());
        }
    }

}
