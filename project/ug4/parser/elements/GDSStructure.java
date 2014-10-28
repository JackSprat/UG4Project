package project.ug4.parser.elements;

import java.util.ArrayList;
import java.util.List;

import project.ug4.parser.GDSParser;
import project.ug4.parser.records.Record;

public class GDSStructure {

	public String name;
	//public AABB boundingBox;
	public boolean finalised = false;
	public List<GDSStructureReference> srefs = new ArrayList<GDSStructureReference>();
	public List<GDSArrayReference> arefs = new ArrayList<GDSArrayReference>();
	public List<GDSBoundary> boundaries = new ArrayList<GDSBoundary>();
	public List<GDSPath> paths = new ArrayList<GDSPath>();
	
	public GDSStructure() {
		
		while (true) {
			Record rec = GDSParser.parseRecord();
			
			switch (rec.record) {
				case ENDSTR:
					return;
				case STRNAME:
					name = rec.getString(); break;
				case SREF:
					srefs.add(new GDSStructureReference()); break;
				case AREF:
					arefs.add(new GDSArrayReference()); break;
				case BOUNDARY:
					boundaries.add(new GDSBoundary()); break;
				case PATH:
					paths.add(new GDSPath()); break;
			default:
				System.out.println("UNIMPLEMENTED STRUCTURE " + name + ": " + rec.record);
				break;
					
			}
			
		}
		
	}
	
	public void render() {
		
		
		
	}
	
}