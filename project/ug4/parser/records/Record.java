package project.ug4.parser.records;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import project.ug4.math.IntVec2D;
import project.ug4.parser.RecordType;
import project.ug4.parser.elements.*;

public class Record {

	public long index;
	public int length;
	public RecordType record;
	public byte[] data;
	
	public Record(long index, int length, int recordtype) {
		this.index = index;
		this.length = length;
		this.record = RecordType.getRecordFromValue(recordtype);
	}
	
	public String getString() {
		
		//Truncate null terminator
		if (data[data.length - 1] == 0xFF) {
			data = Arrays.copyOf(data, data.length - 1);			
		}
		
		try {
			return new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.exit(2);
		}
		
		return null;
		
	}
	
	public float getFloat() {
		
		int sign = data[0] >> 7 == 0 ? 1 : -1;
		int exponent = (data[0] & 0x7F) - 64;
		double mantissa = (((data[1] & 0xFF) << 16) + ((data[2] & 0xFF) << 8) + (data[3] & 0xFF))/Math.pow(2, 24);
		
		return (float) (sign * (mantissa * Math.pow(16, exponent)));
		
	}
	
	public double getDouble() {
		
		int sign = data[0] >> 7 == 0 ? 1 : -1;
		int exponent = (data[0] & 0x7F) - 64;
		
		double mantissa = 0;
		for (int i = 1; i < 8; i++) {
			mantissa += ((data[i] & 0xFF) << (56 - i*8));
		}
		mantissa /= Math.pow(2, 24);
		
		return (float) (sign * (mantissa * Math.pow(16, exponent)));
		
	}
	
	public int[] getInt32() {
		
		int[] int32 = new int[data.length/4];
		
		for (int i = 0; i < data.length; i += 4) {
			int32[i/4] = ByteBuffer.wrap(data, i, 4).getInt();
		}
		
		return int32;
		
	}
	
	public short[] getInt16() {

		short[] int16 = new short[data.length/2];
		
		for (int i = 0; i < data.length; i += 2) {
			int16[i/2] = ByteBuffer.wrap(data, i, 2).getShort();
		}
		
		return int16;
		
	}
	
	public IntVec2D[] getIntVec2D() {

		IntVec2D[] points = new IntVec2D[data.length/8];
		
		for (int i = 0; i < data.length; i += 8) {
			int posX = ByteBuffer.wrap(data, i, 4).getInt();
			int posY = ByteBuffer.wrap(data, i + 4, 4).getInt();
			points[i/8] = new IntVec2D(posX, posY);
		}
		
		return points;
		
	}
	
}