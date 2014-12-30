package project.ug4.parser.elements;

import java.util.ArrayList;
import java.util.List;

import project.ug4.math.BoundingAABB;
import project.ug4.parser.GDSParser;
import project.ug4.parser.records.Record;
import project.ug4.renderer.RenderState;

public class GDSStructure extends GDSRenderable {

	public String name;
	public BoundingAABB boundingBox;
	public RenderState state = RenderState.DISABLED;
	public List<GDSStructureReference> srefs = new ArrayList<GDSStructureReference>();
	public List<GDSArrayReference> arefs = new ArrayList<GDSArrayReference>();
	public List<GDSBoundary> boundaries = new ArrayList<GDSBoundary>();
	public List<GDSPath> paths = new ArrayList<GDSPath>();
	
	public GDSStructure() {
		
		while (true) {
			
			Record rec = GDSParser.parser.parseRecord();
			
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
	
	@Override
	public void render() {		
		
		for (GDSBoundary b : boundaries) {
			b.render();
		}
		for (GDSPath p : paths) {
			p.render();
		}
		
	}

	@Override
	public void load() {
		for (GDSBoundary b : boundaries) {
			b.load();
		}
		for (GDSPath p : paths) {
			p.load();
		}
		
	}
	
}