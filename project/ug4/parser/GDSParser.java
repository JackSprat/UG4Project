package project.ug4.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import project.ug4.parser.elements.*;
import project.ug4.parser.records.Record;

public class GDSParser {

	public static RecordParser parser;
	
	private static HashMap<String, GDSStructure> structures = new HashMap<String, GDSStructure>();
	private static String topcell;
	
	public GDSParser(File file) throws FileNotFoundException {
		parser = new RecordParser(file);
		parse();
	}
	
	private void parse() {
		
		boolean loop = true;
		
		while (loop) {
			
			Record rec = parser.parseRecord();
			
			switch (rec.record) {
			
				case BGNSTR:
					GDSStructure g = new GDSStructure();
					structures.put(g.name, g); break;
					
				case ENDLIB:
					loop = false; break;
					
			default:
				break;
			}
			
			
		}
		
		HashMap<String, Boolean> cellCandidates = new HashMap<String, Boolean>();
		
		for (GDSStructure str : structures.values()) {
			
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
				topcell = candidate.getKey();
				System.out.println("Topcell candidate: " + topcell);
			}
		}
		
	}
	
	public GDSStructure getTopCell() {
		return structures.get(topcell);
	}
	
	public GDSStructure getCell(String name) {
		try {
			return structures.get(name);
		} catch (Exception e) {
			System.out.println("Issue getting cell " + name + ", perhaps it does not exist?");
			return null;
		}
	}
	
}