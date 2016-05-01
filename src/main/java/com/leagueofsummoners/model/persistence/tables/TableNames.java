package com.leagueofsummoners.model.persistence.tables;

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
    public final static String COLUMN_CHAMPIONS_CHAMPION_ICON_NAME = "CHAMPION_ICON_NAME";


    /* Tabla Guides*/
    public final static String TABLE_GUIDES = "GUIDES";
    public final static String COLUMN_GUIDES_ID_GUIDE = "ID_GUIDE";
    public final static String COLUMN_GUIDES_ID_USER = "ID_USER";
    public final static String COLUMN_GUIDES_ID_CHAMPION = "ID_CHAMPION";
    public final static String COLUMN_GUIDES_TITLE = "GUIDE_TITLE";
    public final static String COLUMN_GUIDES_CONTENT = "GUIDE_CONTENT";
    public final static String COLUMN_GUIDES_LANG =  "GUIDE_LANG";


}
