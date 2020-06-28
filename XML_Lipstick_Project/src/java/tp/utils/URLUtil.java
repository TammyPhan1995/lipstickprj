/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author ADMIN
 */
public class URLUtil {
    
    //need to close is after using this method
    public static InputStream getInputStream(String pageURL) throws IOException{
        InputStream is = null;
        URL url = new URL(pageURL);
        URLConnection conn = url.openConnection();
        conn.setUseCaches(false);
        conn.addRequestProperty("User-agent", Common.USER_AGENT);
        is = conn.getInputStream();
        return is;
    }
}
