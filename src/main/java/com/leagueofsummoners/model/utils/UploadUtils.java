package com.leagueofsummoners.model.utils;

import com.leagueofsummoners.LeagueofsummonersApplication;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadUtils {

    public static String root;

    static {
        try {
           // root = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main"
             //       + File.separator + "resources" + File.separator + "static" + File.separator;
             root = new File(".").getCanonicalPath() + File.separator + "webapps" + File.separator + "leagueofsummoners" + File.separator + "WEB-INF" + File.separator
             + "classes" + File.separator + "static" + File.separator + "img" + File.separator + "avatars";
        } catch (IOException e) {
            LeagueofsummonersApplication.LOGGER.error("Error creando ruta - UploadUtils");
            e.printStackTrace();
        }
    }

    public static String saveImg(MultipartFile file, String username) {
        if (!file.isEmpty()) {
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!fileExtension.equals("jpg") && !fileExtension.equals("jpeg") && !fileExtension.equals("png"))
                return null;
            String imgPath = null;
            String imgName = "avatar_" + username + "." + fileExtension;
            //String avatarPath = "img" + File.separator + "avatars" + File.separator + username;
            String avatarPath = File.separator + username;
            return (saveFile(file, root + avatarPath + File.separator, imgName) != null) ? avatarPath + File.separator + imgName : null;
            //return (saveFile(file, "C:\\\\avatars" + avatarPath + File.separator, imgName) != null) ? avatarPath + File.separator + imgName : null;
        }
        return null;
    }

    private static String saveFile(MultipartFile file, String savePath, String fileName) {
        if (!file.isEmpty()) { //Si el fichero esta vacio no creo nada
            try {
                new File(savePath).mkdirs();
                File saveFile = new File(savePath + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(saveFile));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
                savePath = null; //Si hay algún error devuelvo null
                LeagueofsummonersApplication.LOGGER.error("Error guardando archivo en: " + savePath);
            }
        } else {
            return null;
        }

        return savePath;
    }
}
