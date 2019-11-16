package com.ds.rough;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
            // let's create a ZIP file to write data
            FileOutputStream fos = new FileOutputStream("./target/surefire-reports/sample.zip");
            ZipOutputStream zipOS = new ZipOutputStream(fos);

            String file1 = "./target/surefire-reports/html/extent.html";
            String file2 = "./target/surefire-reports/html/index.html";
          
            writeToZipFile(file1, zipOS);
            writeToZipFile(file2, zipOS);
           
            zipOS.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


	}
	
	
	
	public static void writeToZipFile(String path, ZipOutputStream zipStream)
            throws FileNotFoundException, IOException {

        System.out.println("Writing file : '" + path + "' to zip file");

        File aFile = new File(path);
        FileInputStream fis = new FileInputStream(aFile);
        ZipEntry zipEntry = new ZipEntry(path);
        zipStream.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipStream.write(bytes, 0, length);
        }

        zipStream.closeEntry();
        fis.close();
    }
}
	


