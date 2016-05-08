package com.leagueofsummoners;

import java.io.File;
import java.io.IOException;

/**
 * Created by juanj on 07/05/2016.
 */
public class ApplicationPaths {

    /**
     * Generación de rutas dependiendo de donde se este ejecutando la app.
     */
    static {
        try {
            GALLERY_DEVELOP_SAVE_PATH = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main"
                    + File.separator + "resources" + File.separator + "static" + File.separator + "img" + File.separator + "avatars" + File.separator;

               /*  GALLERY_DEVELOP_SAVE_PATH  = new File(".").getCanonicalPath() + File.separator + "webapps" + File.separator + "leagueofsummoners" + File.separator + "WEB-INF" + File.separator
                    + "classes" + File.separator + "static" + File.separator + "img" + File.separator + "avatars";*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String GALLERY_DEVELOP_SAVE_PATH;
    public static final String SUMMONER_PROFILE_ICON_PATH = "http://lkimg.zamimg.com/images/v2/summoner/icons/size64x64/";
    public static final String TEEMO_ICON = "http://ddragon.leagueoflegends.com/cdn/6.9.1/img/champion/Teemo.png";
    public static final String ROOT_PATH = "/";
    public static final String INDEX_HTML_PATH = ROOT_PATH+"index.html";
    public static final String INDEX_PATH = "/index";
    public static final String HOME_PATH = "/home";

}
