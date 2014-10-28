package project.ug4.math;

import org.lwjgl.opengl.GL11;

public class BoundingAABB {
	
	private IntVec2D aa, bb, midpoint;
	private double boundingCircleRadius;
	
	public BoundingAABB(IntVec2D[] points) {
		
		int minX = points[0].getX();
		int maxX = points[0].getX();
		int minY = points[0].getY();
		int maxY = points[0].getY();
		
		for (IntVec2D point : points) {
			
			int xVal = point.getX();
			if (xVal < minX) {
				minX = xVal;
			} else if (xVal > maxX) {
				maxX = xVal;
			}
			
			int yVal = point.getY();
			if (yVal < minY) {
				minY = yVal;
			} else if (yVal > maxY) {
				maxY = yVal;
			}
			
		}
		
		aa = new IntVec2D(minX, minY);
		bb = new IntVec2D(maxX, maxY);
		
	}
	
	public BoundingAABB(BoundingAABB[] children) {
		
		int minX = children[0].aa.getX();
		int maxX = children[0].bb.getX();
		int minY = children[0].aa.getY();
		int maxY = children[0].bb.getY();
		
		for (BoundingAABB child : children) {
			
			if (child.aa.getX() < minX) {
				minX = child.aa.getX();
			} else if (child.bb.getX() > maxX) {
				maxX = child.bb.getX();
			}
			
			if (child.aa.getY() < minY) {
				minY = child.aa.getY();
			} else if (child.bb.getY() > maxY) {
				maxY = child.bb.getY();
			}
			
		}
		
		aa = new IntVec2D(minX, minY);
		bb = new IntVec2D(maxX, maxY);
		midpoint = aa.add(bb).scale(0.5f);
		boundingCircleRadius = midpoint.sub(aa).length();
		
	}
	
	public boolean visibilityCheck(IntVec2D point, float distance) {
		return midpoint.sub(point).length() < distance;
	}
	
	@Override
	public String toString() {
		return aa.toString() + bb.toString();
	}
	
	public void renderDebug(int layer) {
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glVertex3f(aa.getX(),aa.getY(),layer);
		GL11.glVertex3f(bb.getX(),aa.getY(),layer);
		GL11.glVertex3f(bb.getX(),bb.getY(),layer);
		GL11.glVertex3f(aa.getX(),bb.getY(),layer);
	 
		GL11.glVertex3f(aa.getX(),aa.getY(),layer-1);
		GL11.glVertex3f(bb.getX(),aa.getY(),layer-1);
		GL11.glVertex3f(bb.getX(),bb.getY(),layer-1);
		GL11.glVertex3f(aa.getX(),bb.getY(),layer-1);
	 
		GL11.glVertex3f(aa.getX(),aa.getY(),layer);
		GL11.glVertex3f(aa.getX(),bb.getY(),layer);
		GL11.glVertex3f(aa.getX(),bb.getY(),layer-1);
		GL11.glVertex3f(aa.getX(),aa.getY(),layer-1);
	 
		GL11.glVertex3f(bb.getX(),aa.getY(),layer);
		GL11.glVertex3f(bb.getX(),bb.getY(),layer);
		GL11.glVertex3f(bb.getX(),bb.getY(),layer-1);
		GL11.glVertex3f(bb.getX(),aa.getY(),layer-1);
	 
		GL11.glVertex3f(aa.getX(),aa.getY(),layer);
		GL11.glVertex3f(bb.getX(),aa.getY(),layer);
		GL11.glVertex3f(bb.getX(),aa.getY(),layer-1);
		GL11.glVertex3f(aa.getX(),aa.getY(),layer-1);
	 
		GL11.glVertex3f(aa.getX(),bb.getY(),layer);
		GL11.glVertex3f(bb.getX(),bb.getY(),layer);
		GL11.glVertex3f(bb.getX(),bb.getY(),layer-1);
	    GL11.glVertex3f(aa.getX(),bb.getY(),layer-1);
	 
	    GL11.glEnd();
		
	}
	
}