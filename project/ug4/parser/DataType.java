package project.ug4.parser;

public enum DataType {
	
	INVALID,
	NO_DATA,
	BIT_ARRAY,
	INT_16,
	INT_32,
	REAL_32,
	REAL_64,
	STRING;
	
	public static DataType getDataType(int value) {
		switch (value) {
			case 0x00: return NO_DATA;
			case 0x01: return BIT_ARRAY;
			case 0x02: return INT_16;
			case 0x03: return INT_32;
			case 0x04: return REAL_32;
			case 0x05: return REAL_64;
			case 0x06: return STRING;
		}
		return INVALID;
	}
}
