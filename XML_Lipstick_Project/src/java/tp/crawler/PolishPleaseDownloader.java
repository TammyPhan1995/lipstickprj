/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp.utils.FileUtil;
import tp.utils.URLUtil;

/**
 *
 * @author ADMIN
 */
public class PolishPleaseDownloader {

    private InputStream is = null;
    private BufferedReader br = null;
    private Writer writer = null;

    private void closeStream() {
        try {
            if (writer != null) {
                writer.close();
            }
            if (br != null) {
                br.close();
            }
            if (is != null) {
                is.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(PolishPleaseDownloader.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    //for product list of polishplease
    public void downloadProductLinks(String pageUrl, String outfilePath) throws IOException {
        //delete file contains product links to write a new one
        FileUtil.deleteFile(outfilePath);
        String strLine;
        try {
            is = URLUtil.getInputStream(pageUrl);
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfilePath, true), "UTF-8"));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

            while ((strLine = br.readLine()) != null) {
                if (strLine.contains("<a class=\"product_img_link\"")) {
                    writer.write(strLine + "\n");
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(PolishPleaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PolishPleaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStream();
        }
    }

    //get product details
    public void downloadProductDetails(String pageUrl, String outfilePath) throws IOException {
        String strLine;
        boolean isInTable = false;
        try {
            is = URLUtil.getInputStream(pageUrl);
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfilePath), "UTF-8"));
            writer.write("<product>\n");
            while ((strLine = br.readLine()) != null) {

                if (strLine.contains("<img itemprop=\"image\"")) {
                    writer.write(strLine + "\n");
                }

                if (strLine.contains("<h1 itemprop=\"name\">")) {
                    writer.write(strLine + "\n");
                }
                if (strLine.contains("<span id=\"loyalty_price\">")) {
                    writer.write("<!-- product name-->");
                    writer.write(strLine + "\n");
                }
                if (strLine.contains("<!-- Data sheet -->")) {
                    isInTable = true;
                }
                if (strLine.contains("<!--end Data sheet -->")) {
                    isInTable = false;
                }
                if (isInTable) {
                    writer.write(strLine + "\n");
                }

            }
            writer.write("</product>");

        } catch (MalformedURLException ex) {
            Logger.getLogger(PolishPleaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PolishPleaseDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStream();
        }
    }

}
