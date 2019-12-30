package com.emretech.compressor;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.awt.event.ActionEvent;

public class GUI {
	
	private static String filePath = System.getProperty("user.dir");
	private static String fileName = "";
	private static String fileType = "";
	private static int textFieldMode = 0;
	private JTextField textInput;
	JLabel lblNewLabel;
	
	public GUI(){
		JFrame frame = new JFrame("Zip Compressor");
		frame.setSize(450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFileCompressor = new JLabel("File Compressor");
		lblFileCompressor.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblFileCompressor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFileCompressor.setBounds(85, 5, 280, 55);
		frame.getContentPane().add(lblFileCompressor);
		
		JLabel lblEnter = new JLabel("Type in your file name here:");
		lblEnter.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEnter.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnter.setBounds(16, 92, 419, 55);
		frame.getContentPane().add(lblEnter);
		
		textInput = new JTextField();
		textInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (textFieldMode) {
				case 0:
					fileName = textInput.getText();
					textInput.setText("");
					lblEnter.setText("Type in your file type here:");
					textFieldMode += 1;
					break;
				case 1:
					fileType = textInput.getText();
					textInput.setText("");
					lblEnter.setText("Done collecting info, hit enter to convert");
					textFieldMode += 1;
					filePath = filePath + "/res/" + fileName + fileType;
					break;
				case 2:
					lblNewLabel.setText("Compressing " + filePath + " to " + fileName + ".zip.");
					zipFile(filePath, fileName, fileType);

					try {
						TimeUnit.SECONDS.sleep( (long)2.5);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					lblNewLabel.setText("File finished compressing!");
				}
				
				
			}
		});
		textInput.setBounds(85, 157, 280, 26);
		frame.getContentPane().add(textInput);
		textInput.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 195, 438, 77);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
		
		
	}
	
	public void zipFile(String filePath, String fileName, String fileType) {
        try {
        	
        	
            File file = new File(filePath);
            String zipFileName = fileName.concat(".zip");
 
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            
            
            
            zos.putNextEntry(new ZipEntry(file.getName()));
 
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            zos.write(bytes, 0, bytes.length);
            
            
            
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
        }
    }
	
	public static void main(String[] args) {
		new GUI();
	}
}
