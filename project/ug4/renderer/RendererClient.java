package project.ug4.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class RendererClient extends Thread {

	public void run() {
		
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		int vertexArrayID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vertexArrayID);
		float[] vertex_data = {-1f, -1f, 0f, 1f, -1f, 0f, 0f, 1f, 0f};
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertex_data.length * 4).order(ByteOrder.nativeOrder());
		FloatBuffer f = vbb.asFloatBuffer();
		f.put(vertex_data).position(0);
		
		int vertexBufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, f, GL15.GL_STATIC_DRAW);
		// init OpenGL here
		
		while (!Display.isCloseRequested()) {
			
			GL20.glEnableVertexAttribArray(0);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
			GL20.glVertexAttribPointer(0, 3, vertexBufferID, false, 0, 0);
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
			GL20.glDisableVertexAttribArray(0);
			Display.update();
			
		}
		
		Display.destroy();
		
	}
	
	private void loadShaders(String vertexShader, String fragmentShader) {
		
		
		
	}
	
}
