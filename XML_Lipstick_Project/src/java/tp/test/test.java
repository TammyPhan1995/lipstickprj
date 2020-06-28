/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp.crawler.PolishPleaseDownloader;
import tp.parsers.PolishPleaseParser;
import tp.utils.Common;
import tp.utils.FileUtil;

/**
 *
 * @author ADMIN
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        PolishPleaseDownloader downloader = new PolishPleaseDownloader();
//        try {
//                downloader.downloadProductDetails("http://polishplease.ph/essie/3635-captivate-me-essie-gel-couture-nail-polish.html"
//                    ,String.format("%s\\%s",Common.PACKAGE_FILE_CONTAINER, Common.XML_PRODUCT_DETAILS_POLISH_PLEASE));
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//


        //clean HTML from productDetails
        PolishPleaseParser parser = new PolishPleaseParser();
        boolean test = parser.cleanHTMLFromProductDetails(String.format("%s\\%s",Common.PACKAGE_FILE_CONTAINER, Common.XML_PRODUCT_DETAILS_POLISH_PLEASE), 
                String.format("%s\\%s",Common.PACKAGE_FILE_CONTAINER, "temp.xml"));
        System.out.println("isWellForm = " + test);
      
    }
    
}
