package project.ug4.parser.elements;

import java.util.List;

import org.lwjgl.util.vector.Matrix4f;

import project.ug4.math.IntVec2D;
import project.ug4.renderer.RenderState;

public abstract class GDSRenderable {

	private RenderState state;
	private IntVec2D points;
	private int layer;
	private List<Matrix4f> instances;
	
	public abstract void render();
	public abstract void load();
	public abstract void unload();
	
}