package com.emretech.compressor;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.zip.*;
 
public class Main {
	
	private static Scanner scan = new Scanner(System.in);
	private static String file = "";
	private static String fileName = "";
	private static String fileType = "";
	
    public static void zipFile(String filePath, String fileName, String fileType) {
        try {
        	TimeUnit.SECONDS.sleep((long) 2.5);       	
        	System.out.println("Setting up...");
        	
            File file = new File(filePath);
            String zipFileName = fileName.concat(".zip");
 
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            
            TimeUnit.SECONDS.sleep((long) 2.5);
            System.out.println("Compressing file...");
            
            zos.putNextEntry(new ZipEntry(file.getName()));
 
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            zos.write(bytes, 0, bytes.length);
            
            TimeUnit.SECONDS.sleep((long) 2.5);          
            System.out.println("Finishing up...");
            
            zos.closeEntry();
            zos.close();
            
           /* TimeUnit.SECONDS.sleep((long) 2.5);
            System.out.println("Moving file to res folder...");
            
            File fileNEW = new File(file + fileName + ".zip");
            fileNEW.renameTo(new File(file + "/res/" + fileName + ".zip"));*/
            
        } catch (FileNotFoundException ex) {
            System.err.format("The file %s does not exist", filePath);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        } catch (InterruptedException e) {
			System.err.println("InteruptedException error: " + e);
		}
    }
 
    public static void main(String[] args) throws InterruptedException {
    	file = System.getProperty("user.dir");
    	
    	System.out.print("Type in your file name here: ");
    	fileName = scan.nextLine();
    	System.out.print("Type in your file type here: ");
    	fileType = scan.nextLine();
    	file = file + "/res/" + fileName + fileType;
    	System.out.println(file);
    	
        zipFile(file, fileName, fileType);
        scan.close();
        
        TimeUnit.SECONDS.sleep(5);
        System.out.println("File finished compressing!");
    }
}