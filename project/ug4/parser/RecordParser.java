package project.ug4.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import project.ug4.parser.records.Record;

public class RecordParser {

	private RandomAccessFile gdsFile;
	
	public RecordParser(File file) {
		try {
			gdsFile = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Record parseRecord() {
		
		byte b[] = new byte[4];
		
		try {
			
			long index = gdsFile.getFilePointer();
			
			gdsFile.read(b);
			
			int length = ((b[0] & 0xFF) << 8) + (b[1] & 0xFF);
			
			if (length % 2 == 1) { length++; } //Odd length, make even
			
			Record rec = new Record(index, length, b[2] & 0xFF);
			
			if (length > 4) {
				byte[] data = new byte[length - 4];
				gdsFile.read(data);
				rec.data = data;
			}
			
			return rec;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Record parseRecord(long index) {
		
		byte b[] = new byte[4];
		
		try {
			
			long currIndex = gdsFile.getFilePointer();
			
			gdsFile.seek(index);
			gdsFile.read(b);
			
			int length = ((b[0] & 0xFF) << 8) + (b[1] & 0xFF);
			
			if (length % 2 == 1) { length++; } //Odd length, make even
			
			Record rec = new Record(index, length, b[2] & 0xFF);
			
			if (length > 4) {
				byte[] data = new byte[length - 4];
				gdsFile.read(data);
				rec.data = data;
			}
			
			gdsFile.seek(currIndex);
			
			return rec;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
