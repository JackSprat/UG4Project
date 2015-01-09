package project.ug4.renderer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import project.ug4.parser.GDSParser;

public class RendererClient extends Thread {

	GDSParser parser;
	
	public RendererClient(GDSParser p) {
		parser = p;
	}

	public void run() {
		
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		parser.getTopCell().load();

		System.out.println(parser.getTopCell().name);
		System.out.println(parser.getTopCell().boundaries.size());
		
		while (!Display.isCloseRequested()) {
			
			Display.sync(60);
			parser.getTopCell().render();
			
		}
		
		Display.destroy();
		
	}
	
}
