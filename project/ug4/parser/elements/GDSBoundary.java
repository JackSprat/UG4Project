package project.ug4.parser.elements;

import project.ug4.math.BoundingAABB;
import project.ug4.math.IntVec2D;
import project.ug4.parser.GDSParser;
import project.ug4.parser.records.Record;

public class GDSBoundary {

	public short layer;
	public BoundingAABB bounds;
	private long pathDataIndex;
	
	public GDSBoundary() {
		
		while (true) {
			
			Record rec = GDSParser.parseRecord();
			
			switch (rec.record) {
				case LAYER:
					layer = rec.getInt16()[0]; break;
				case XY:
					bounds = new BoundingAABB(rec.getIntVec2D());
					pathDataIndex = rec.index;
					break;
				case ENDEL:
					return;
				case DATATYPE:
					break;
			default:
				System.out.println("UNIMPLEMENTED BOUNDARY: " + rec.record);
				break;
					
			}
			
		}
		
	}
	
	public IntVec2D[] getPoints() {
		
		Record r = GDSParser.parseRecord(pathDataIndex);
		return r.getIntVec2D();
		
	}
	
}
