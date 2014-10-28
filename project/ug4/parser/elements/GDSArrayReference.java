package project.ug4.parser.elements;

import project.ug4.math.IntVec2D;
import project.ug4.parser.GDSParser;
import project.ug4.parser.records.Record;

public class GDSArrayReference {

	public String structureName;
	public IntVec2D position, rowPosition, columnPosition;
	public double rot, mag;
	public int rows, columns;
	public boolean reflected, absoluteRotation, absoluteMagnification;
	
	public GDSArrayReference() {
		
		mag = 1;
		rot = 0;
		reflected = absoluteRotation = absoluteMagnification = false;
		
		while (true) {
			
			Record rec = GDSParser.parseRecord();
			
			switch (rec.record) {
				case SNAME:
					structureName = rec.getString(); break;
				case MAG:
					mag = rec.getDouble(); break;
				case ANGLE:
					rot = rec.getDouble(); break;
				case COLROW:
					short[] colrow = rec.getInt16();
					columns = colrow[0];
					rows = colrow[1]; break;
				case XY:
					int[] tempInt32 = rec.getInt32();
					position = new IntVec2D(tempInt32[0], tempInt32[1]);
					columnPosition = new IntVec2D(tempInt32[1], tempInt32[2]);
					rowPosition = new IntVec2D(tempInt32[3], tempInt32[4]); break;
				case STRANS:
					short bitflags = rec.getInt16()[0];
					reflected = (bitflags & 0x80) != 0;
					absoluteRotation = (bitflags & 0x02) != 0;
					absoluteMagnification = (bitflags & 0x01) != 0; break;
				case ENDEL:
					return;
			default:
				System.out.println("UNIMPLEMENTED AREF " + structureName + ": " + rec.record);
				break;
					
			}
			
		}
		
	}
	
}
