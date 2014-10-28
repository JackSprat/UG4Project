package project.ug4.parser;

import java.io.FileNotFoundException;

import project.ug4.parser.records.Record;

public class QuickRun {

	public static void main(String[] args) {
		
		try {
			GDSParser p = new GDSParser("C:\\Users\\Administrator\\Desktop\\GDS3D_1.8\\gds\\example.gds");
			p.parse();
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
