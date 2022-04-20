package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note{
	
	String content;
	
	public TextNote(String title){
		super(title);
	}
	
	public TextNote(String title, String content)
	{
		super(title);
		this.content = content;
	}
	
	
	/**
	 * load a TextNote from File f
	 * 
	 * the tile of the TextNote is the name of the file
	 * the content of the TextNote is the content of the file
	 * 
	 * @param File f
	 */
	public TextNote(File f){
		
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
		
	}
	
	/**
	 * get the content of a file
	 * 
	 * @param absolutePath
	 * @return
	 */
	
	public String getTextFromFile(String absolutePath) {
		
		String result = "";
		String bufferString = "";
		
		try {
			FileInputStream fis = new FileInputStream(absolutePath);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			
			while( (bufferString = buffer.readLine()) !=null) {
				
				result = result + bufferString;
				
			}
			
			buffer.close();
			isr.close();
			fis.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
		
		
	}
	
	public void exportTextToFile(String pathFolder) {
		if(pathFolder == "") {
			pathFolder = ".";
		}
		
		File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ","_") + ".txt"); 
		
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(this.content);
			bw.flush();
			
			bw.close();
			fw.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	public void setContent(String content) {
		
		this.content = content;
		
	}
	
}
