package project.ug4.wrappers;

import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

import project.ug4.parser.GDSParser;
import project.ug4.renderer.RendererClient;

public class GraphicsClient {

	public static void main(String[] args) {
			
		GDSParser parser = null;
		
		try {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			parser = new GDSParser(fc.getSelectedFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		byte[] b = new byte[]{(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
		Record r = new Record(1, 6, 0);
		r.data = b;
		
		System.out.println(r.getInt32()[0]);
		*/
		
		RendererClient c = new RendererClient(parser);
		c.start();
		
	}
	
}
