package project.ug4.parser.elements;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import project.ug4.math.IntVec2D;
import project.ug4.renderer.RenderState;
import project.ug4.renderer.VertexData;

public abstract class GDSRenderable {

	private RenderState state;
	private IntVec2D points;
	private int layer;
	
	public abstract IntVec2D[] getPoints();
	
	public void render() {
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiID);
		
		GL20.glVertexAttribPointer(0, VertexData.positionElementCount, GL11.GL_FLOAT, false, VertexData.stride, VertexData.positionByteOffset);
		GL20.glVertexAttribPointer(1, VertexData.colorElementCount, GL11.GL_FLOAT, false, VertexData.stride, VertexData.colorByteOffset);
		GL20.glVertexAttribPointer(2, VertexData.textureElementCount, GL11.GL_FLOAT, false, VertexData.stride, VertexData.textureByteOffset);

		GL11.glDrawElements(GL11.GL_TRIANGLES, indicesCount, GL11.GL_UNSIGNED_INT, 0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	
}
