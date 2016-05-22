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

	public static String normalizeChampion(String championName) {
		championName = championName.replace(" ", "");
		championName = championName.replace("'", "");
		championName = championName.replace(".", "");
		return championName;
	}

	public static String determineSpecialChampionNames(String championName) {
		switch (championName) {
		case "Wukong":
			championName = "MonkeyKing";
			break;
		case "Fiddlesticks":
			championName = "FiddleSticks";
			break;
		case "KhaZix":
			championName = "Khazix";
			break;
		case "VelKoz":
			championName = "Velkoz";
			break;
		case "ChoGath":
			championName = "Chogath";
			break;
		case "LeBlanc":
			championName = "Leblanc";
			break;
		}
		return championName;
	}

}
