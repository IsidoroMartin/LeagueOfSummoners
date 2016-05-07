package com.leagueofsummoners.model.utils;

import com.leagueofsummoners.ApplicationPaths;
import com.leagueofsummoners.LeagueofsummonersApplication;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadUtils {


    public static String saveImg(MultipartFile file, String username) {
        if (!file.isEmpty()) {
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!fileExtension.equals("jpg") && !fileExtension.equals("jpeg") && !fileExtension.equals("png"))
                return null;
            String imgName = "avatar_" + username + "." + fileExtension;
            String avatarPath = File.separator + username;
            return (saveFile(file, ApplicationPaths.GALLERY_DEVELOP_SAVE_PATH + avatarPath + File.separator, imgName) != null) ? avatarPath + File.separator + imgName : null;
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