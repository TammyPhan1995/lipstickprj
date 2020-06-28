/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.utils;

import java.io.File;

/**
 *
 * @author ADMIN
 */
public class FileUtil {
    
    
    public static void deleteFile(String filePath){
        File file = new File(filePath);
        file.delete();
    }
}
