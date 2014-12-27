package project.ug4.parser.elements;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import project.ug4.renderer.VertexData;

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
			
			Record rec = GDSParser.parseRecord();
			
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
		
		Record r = GDSParser.parseRecord(pathDataIndex);
		return r.getIntVec2D();
		
	}

}