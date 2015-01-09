package project.ug4.parser.elements;

import org.lwjgl.opengl.GL11;

import project.ug4.math.BoundingAABB;
import project.ug4.math.IntVec2D;
import project.ug4.parser.GDSParser;
import project.ug4.parser.records.Record;

public class GDSBoundary extends GDSRenderable{

	public short layer;
	public BoundingAABB bounds;
	private long pathDataIndex;
	private IntVec2D[] points;
	
	public GDSBoundary() {
		
		while (true) {
			
			Record rec = GDSParser.parser.parseRecord();
			
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
		
		Record r = GDSParser.parser.parseRecord(pathDataIndex);
		return r.getIntVec2D();
		
	}

	@Override
	public void render() {
		
		GL11.glVertexPointer(size, stride, pointer);
		
	}

	@Override
	public void load() {
		points = GDSParser.parser.parseRecord(pathDataIndex).getIntVec2D();
	}
	
}
