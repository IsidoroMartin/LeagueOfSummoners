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

/*
Autores= Juan José Ramírez & Isidoro Martín
Fecha= Junio de 2016
Licencia=  gp130
Version= 1.0
Descripcion= Proyecto final desarrollo de aplicaciones web. League of Summoners es una aplicación
enfocada a los jugadores del popular juego League of Legends, usando esta aplicación podrán acceder
a guías, detalles sobre campeones e incluso sus últimas partidas.

Copyright (C) 2016 Juan José Ramírez & Isidoro Martín
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


/**
 * Esta clase contiene métodos útiles para subir archivosal servidor
 */

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