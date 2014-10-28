package project.ug4.renderer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private static Vector3f cameraPos = new Vector3f(0,0,-1);
	private static Vector2f cameraRot = new Vector2f(0,0);;
	
	public static Matrix4f updateViewMatrix(Matrix4f viewMatrix) {
		while (Mouse.next()){
			cameraRot.x += Mouse.getEventDX()*0.001;
			cameraRot.y += -Mouse.getEventDY()*0.001;
		}
		
		float movementMultiplier = 0.1f;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			movementMultiplier *= 3;
		}
	
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			cameraPos.z += Math.cos(cameraRot.x)*movementMultiplier;
			cameraPos.x -= Math.sin(cameraRot.x)*movementMultiplier;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			cameraPos.z -= Math.cos(cameraRot.x)*movementMultiplier;
			cameraPos.x += Math.sin(cameraRot.x)*movementMultiplier;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			cameraPos.z += Math.sin(cameraRot.x)*movementMultiplier;
			cameraPos.x += Math.cos(cameraRot.x)*movementMultiplier;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			cameraPos.z -= Math.sin(cameraRot.x)*movementMultiplier;
			cameraPos.x -= Math.cos(cameraRot.x)*movementMultiplier;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			cameraPos.y -= movementMultiplier;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			cameraPos.y += movementMultiplier;
		}
		if (cameraRot.y > Math.PI/2) {
			cameraRot.y = (float) (Math.PI/2);
		}
		if (cameraRot.y < Math.PI/-2) {
			cameraRot.y = (float) (Math.PI/-2);
		}
		if (cameraRot.x > Math.PI*2) {
			cameraRot.x -= Math.PI*2;
		}
		if (cameraRot.x < 0) {
			cameraRot.x += Math.PI*2;
		}
		
		viewMatrix.rotate(cameraRot.x, new Vector3f(0f, 1f, 0f));
		viewMatrix.rotate(cameraRot.y, new Vector3f((float)Math.cos(cameraRot.x), 0f, (float)Math.sin(cameraRot.x)));
		return Matrix4f.translate(cameraPos, viewMatrix, viewMatrix);
	}
}
