package com.leagueofsummoners.model.persistence.tables;

import org.springframework.beans.factory.annotation.Value;

/**
 * Esta clase tienet todos los nombres y campos de la BD
 *
 * @author Juanjors
 */
public class TableNames {

    /* Tabla campeones*/
    public final static String TABLE_CHAMPIONS = "CHAMPIONS";
    public final static String COLUMN_CHAMPIONS_ID_CHAMPION = "ID_CHAMPION";
    public final static String COLUMN_CHAMPIONS_CHAMPION_NAME = "CHAMPION_NAME";
    public final static String COLUMN_CHAMPIONS_CHAMPION_TITLE_ES = "CHAMPION_TITLE_ES";
    public final static String COLUMN_CHAMPIONS_CHAMPION_TITLE_EN = "CHAMPION_TITLE_EN";
    public final static String COLUMN_CHAMPIONS_ID_PASSIVE = "ID_PASSIVE";
    public final static String COLUMN_CHAMPIONS_CHAMPION_LORE_ES = "CHAMPION_LORE_ES";
    public final static String COLUMN_CHAMPIONS_CHAMPION_LORE_EN = "CHAMPION_LORE_EN";
    public final static String COLUMN_CHAMPIONS_CHAMPION_INFO = "CHAMPION_INFO";
    public static final String COLUMN_CHAMPIONS_CHAMPION_ICON_NAME = "CHAMPION_ICON_NAME";


    /* Tabla Guides*/
    public static final String TABLE_GUIDES = "GUIDES";
    public static final String COLUMN_GUIDES_ID_GUIDE = "ID_GUIDE";
    public static final String COLUMN_GUIDES_ID_USER = "ID_USER";
    public static final String COLUMN_GUIDES_ID_CHAMPION = "ID_CHAMPION";
    public static final String COLUMN_GUIDES_TITLE = "GUIDE_TITLE";
    public static final String COLUMN_GUIDES_CONTENT = "GUIDE_CONTENT";
    public static final String COLUMN_GUIDES_LANG = "GUIDE_LANG";

 /*   @Value("${table.champions}")
    public static void setTableChampions(String tableChampions) {
        TABLE_CHAMPIONS = tableChampions;
    }

    @Value("${table.champions.column.idchampion}")
    public static void setColumnChampionsIdChampion(String columnChampionsIdChampion) {
        COLUMN_CHAMPIONS_ID_CHAMPION = columnChampionsIdChampion;
    }

    @Value("${table.champions.column.championname}")
    public static void setColumnChampionsChampionName(String columnChampionsChampionName) {
        COLUMN_CHAMPIONS_CHAMPION_NAME = columnChampionsChampionName;
    }

    @Value("${table.champions.column.championtitlees}")
    public static void setColumnChampionsChampionTitleEs(String columnChampionsChampionTitleEs) {
        COLUMN_CHAMPIONS_CHAMPION_TITLE_ES = columnChampionsChampionTitleEs;
    }

    @Value("${table.champions.column.championtitleen}")
    public static void setColumnChampionsChampionTitleEn(String columnChampionsChampionTitleEn) {
        COLUMN_CHAMPIONS_CHAMPION_TITLE_EN = columnChampionsChampionTitleEn;
    }

    @Value("${table.champions.column.idpassive}")
    public static void setColumnChampionsIdPassive(String columnChampionsIdPassive) {
        COLUMN_CHAMPIONS_ID_PASSIVE = columnChampionsIdPassive;
    }

    @Value("${table.champions.column.championlorees}")
    public static void setColumnChampionsChampionLoreEs(String columnChampionsChampionLoreEs) {
        COLUMN_CHAMPIONS_CHAMPION_LORE_ES = columnChampionsChampionLoreEs;
    }

    @Value("${table.champions.column.championloreen}")
    public static void setColumnChampionsChampionLoreEn(String columnChampionsChampionLoreEn) {
        COLUMN_CHAMPIONS_CHAMPION_LORE_EN = columnChampionsChampionLoreEn;
    }

    @Value("${table.champions.column.championinfo}")
    public static void setColumnChampionsChampionInfo(String columnChampionsChampionInfo) {
        COLUMN_CHAMPIONS_CHAMPION_INFO = columnChampionsChampionInfo;
    }

    @Value("${table.champions.column.championicon}")
    public static void setColumnChampionsChampionIconName(String columnChampionsChampionIconName) {
        COLUMN_CHAMPIONS_CHAMPION_ICON_NAME = columnChampionsChampionIconName;
    }

    @Value("$table.guides}")
    public static void setTableGuides(String tableGuides) {
        TABLE_GUIDES = tableGuides;
    }

    @Value("${table.guides.column.idguide}")
    public static void setColumnGuidesIdGuide(String columnGuidesIdGuide) {
        COLUMN_GUIDES_ID_GUIDE = columnGuidesIdGuide;
    }

    @Value("${table.guides.column.iduser}")
    public static void setColumnGuidesIdUser(String columnGuidesIdUser) {
        COLUMN_GUIDES_ID_USER = columnGuidesIdUser;
    }

    @Value("${table.guides.column.idchampion}")
    public static void setColumnGuidesIdChampion(String columnGuidesIdChampion) {
        COLUMN_GUIDES_ID_CHAMPION = columnGuidesIdChampion;
    }

    @Value("${table.guides.column.guidetitle}")
    public static void setColumnGuidesTitle(String columnGuidesTitle) {
        COLUMN_GUIDES_TITLE = columnGuidesTitle;
    }

    @Value("${table.guides.column.guidecontent}")
    public static void setColumnGuidesContent(String columnGuidesContent) {
        COLUMN_GUIDES_CONTENT = columnGuidesContent;
    }

    @Value("${table.guides.column.guidelang}")
    public static void setColumnGuidesLang(String columnGuidesLang) {
        COLUMN_GUIDES_LANG = columnGuidesLang;
    }*/


}
