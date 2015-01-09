package project.ug4.renderer;

public class VertexData {
	private float[] xyzw = new float[] {0f, 0f, 0f, 1f};
	private static float[] rgba = new float[] {1f, 1f, 1f, 1f};
	private float[] st = new float[] {0f, 0f};

	public static final int elementBytes = 4;
	
	public static final int positionElementCount = 4;
	public static final int colorElementCount = 4;
	public static final int textureElementCount = 2;
	
	public static final int positionByteCount = positionElementCount * elementBytes;
	public static final int colorByteCount = colorElementCount * elementBytes;
	public static final int textureByteCount = textureElementCount * elementBytes;
	
	public static final int positionByteOffset = 0;
	public static final int colorByteOffset = positionByteOffset + positionByteCount;
	public static final int textureByteOffset = colorByteOffset + colorByteCount;
	
	public static final int elementCount = positionElementCount + colorElementCount + textureElementCount;	
	// The size of a vertex in bytes
	public static final int stride = positionByteCount + colorByteCount + textureByteCount;
	
	// Setters
	public VertexData setXYZ(float x, float y, float z) {
		return setXYZW(x, y, z, 1f);
	}
	
	public VertexData setRGB(float r, float g, float b) {
		return setRGBA(r, g, b, 1f);
	}
	
	public VertexData setST(float s, float t) {
		st = new float[] {s, t};
		return this;
	}
	
	private VertexData setXYZW(float x, float y, float z, float w) {
		xyzw = new float[] {x, y, z, w};
		return this;
	}
	
	private VertexData setRGBA(float r, float g, float b, float a) {
		return this;
	}
	
	// Getters	
	public float[] getElements() {
		float[] out = new float[VertexData.elementCount];
		int i = 0;
		
		// Insert XYZW elements
		out[i++] = this.xyzw[0];
		out[i++] = this.xyzw[1];
		out[i++] = this.xyzw[2];
		out[i++] = 1;//this.xyzw[3];
		// Insert RGBA elements
		out[i++] = rgba[0];
		out[i++] = rgba[1];
		out[i++] = rgba[2];
		out[i++] = rgba[3];
		
		// Insert ST elements
		out[i++] = st[0];
		out[i++] = st[1];
		
		return out;
	}
	
	public float[] getXYZW() {
		return xyzw.clone();
	}
	
	public float[] getRGBA() {
		return rgba.clone();
	}
	
	public float[] getST() {
		return st.clone();
	}
}
