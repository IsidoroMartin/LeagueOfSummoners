package com.leagueofsummoners.model.utils;

import java.util.List;


public class RiotUtils {
	
	public static String[] parseRiotListToString(List<?> list) {
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
	
	/**
	 * Este método normaliza el nombre de los campeones apra que sean usables en los links, 
	 * no esta parametrizado con el otro método porque se llama desde thymleaf y es más sencillo así.
	 * @param championName
	 * @return nombre normalizzado
	 */
	public static String normalizeChampionForLink(String championName) {
		championName = championName.replace(" ", "");
		championName = championName.replace("'", "");
		championName = championName.replace(".", "-");
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
