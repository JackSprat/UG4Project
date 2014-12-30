package project.ug4.parser;

import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

public class QuickRun {

	public static void main(String[] args) {
		
		try {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			GDSParser p = new GDSParser(fc.getSelectedFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		byte[] b = new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
		Record r = new Record(1, 6, 0);
		r.data = b;
		
		System.out.println(r.getInt32()[0]);
		*/
		
	}
	
}
