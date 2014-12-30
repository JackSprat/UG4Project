package project.ug4.parser.elements;

import project.ug4.math.IntVec2D;
import project.ug4.parser.GDSParser;
import project.ug4.parser.records.Record;

public class GDSStructureReference {

	public String structureName;
	public IntVec2D position;
	public double rot, mag;
	public boolean reflected, absoluteRotation, absoluteMagnification;
	
	public GDSStructureReference() {
		
		mag = 1;
		rot = 0;
		reflected = absoluteRotation = absoluteMagnification = false;
		
		while (true) {
			
			Record rec = GDSParser.parser.parseRecord();
			
			switch (rec.record) {
				case SNAME:
					this.structureName = rec.getString(); break;
				case MAG:
					this.mag = rec.getDouble(); break;
				case ANGLE:
					this.rot = rec.getDouble(); break;
				case XY:
					int[] position = rec.getInt32();
					this.position = new IntVec2D(position[0], position[1]); break;
				case STRANS:
					short bitflags = rec.getInt16()[0];
					reflected = (bitflags & 0x80) != 0;
					absoluteRotation = (bitflags & 0x02) != 0;
					absoluteMagnification = (bitflags & 0x01) != 0; break;
				case ENDEL:
					return;
			default:
				System.out.println("UNIMPLEMENTED SREF " + structureName + ": " + rec.record);
				break;
					
			}
			
		}
		
	}
	
}