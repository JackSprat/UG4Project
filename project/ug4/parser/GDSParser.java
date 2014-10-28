package project.ug4.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.ug4.parser.elements.*;
import project.ug4.parser.records.Record;

public class GDSParser {

	private static RandomAccessFile gdsFile;
	
	private static List<GDSStructure> structures = new ArrayList<GDSStructure>();
	
	
	public GDSParser(String f) throws FileNotFoundException {
		gdsFile = new RandomAccessFile(new File(f), "r");
	}
	
	public void parse() {
		
		boolean loop = true;
		
		while (loop) {
			
			Record rec = parseRecord();
			
			switch (rec.record) {
			
				case BGNSTR:
					
					structures.add(new GDSStructure()); break;
					
				case ENDLIB:
					
					loop = false; break;
					
			default:
				break;
			}
			
			
		}
		
		HashMap<String, Boolean> cellCandidates = new HashMap<String, Boolean>();
		
		for (GDSStructure str : structures) {
			
			if (!cellCandidates.containsKey(str.name)) {
				cellCandidates.put(str.name, true);
			}
			
			for (GDSStructureReference sref : str.srefs) {
				cellCandidates.put(sref.structureName, false);
			}
			
			for (GDSArrayReference aref : str.arefs) {
				cellCandidates.put(aref.structureName, false);
			}
			
		}
		
		for (Map.Entry<String, Boolean> candidate : cellCandidates.entrySet()) {
			if (candidate.getValue()) {
				System.out.println("Topcell candidate: " + candidate.getKey());
			}
		}
		
	}
	
	public static Record parseRecord() {
		
		byte b[] = new byte[4];
		
		try {
			
			long index = gdsFile.getFilePointer();
			
			gdsFile.read(b);
			
			int length = ((b[0] & 0xFF) << 8) + (b[1] & 0xFF);
			
			if (length % 2 == 1) { length++; } //Odd length, make even
			
			Record rec = new Record(index, length, b[2] & 0xFF);
			
			if (length > 4) {
				byte[] data = new byte[length - 4];
				gdsFile.read(data);
				rec.data = data;
			}
			
			return rec;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Record parseRecord(long index) {
		
		byte b[] = new byte[4];
		
		try {
			
			long currIndex = gdsFile.getFilePointer();
			
			gdsFile.seek(index);
			gdsFile.read(b);
			
			int length = ((b[0] & 0xFF) << 8) + (b[1] & 0xFF);
			
			if (length % 2 == 1) { length++; } //Odd length, make even
			
			Record rec = new Record(index, length, b[2] & 0xFF);
			
			if (length > 4) {
				byte[] data = new byte[length - 4];
				gdsFile.read(data);
				rec.data = data;
			}
			
			gdsFile.seek(currIndex);
			
			return rec;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private static Record parseUntil(RecordType type) {
		
		while (true) {
			
			Record rec = parseRecord();
			if (rec.record == type) { return rec;}
			
		}
		
	}
	
}