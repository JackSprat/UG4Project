package project.ug4.parser.elements;

import project.ug4.math.BoundingAABB;
import project.ug4.math.IntVec2D;
import project.ug4.parser.GDSParser;
import project.ug4.parser.records.Record;

public class GDSPath extends GDSRenderable {

	public short layer;
	public BoundingAABB bounds;
	public long pathDataIndex;
	
	public GDSPath() {
		
		while (true) {
			
			Record rec = GDSParser.parser.parseRecord();
			
			switch (rec.record) {
				case LAYER:
					layer = rec.getInt16()[0]; break;
				case XY:
					pathDataIndex = rec.index;
					bounds = new BoundingAABB(rec.getIntVec2D()); break;
				case ENDEL:
					return;
			default:
				System.out.println("UNIMPLEMENTED PATH: " + rec.record);
				break;
					
			}
			
		}
		
	}
	
	public IntVec2D[] getPoints() {
		
		Record r = GDSParser.parser.parseRecord(pathDataIndex);
		return r.getIntVec2D();
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

}