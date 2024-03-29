package fr.bipbipgaming.main;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F11;

import org.lwjgl.glfw.GLFW;

import fr.bipbipgaming.engine.graphics.Renderer;
import fr.bipbipgaming.engine.graphics.Shader;
import fr.bipbipgaming.engine.io.Input;
import fr.bipbipgaming.engine.io.Window;
import fr.bipbipgaming.engine.math.Vector3f;
import fr.bipbipgaming.engine.objects.Block;
import fr.bipbipgaming.engine.objects.Camera;

public class Main implements Runnable {
	
	public Thread game;
	public static Window window;
	public static Renderer renderer;
	public static Shader shader;
	public static Block blocks;
	public final static int WIDTH = 1280, HEIGHT = 760;
	private static Block block = new Block("/textures/beautiful.png", new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.25f, 0.25f, 0.25f));
	
	public Camera camera = new Camera(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0));
	
	public void start() {
		
		game = new Thread(this, "game");
		game.start();
		
	}
	
	public static void init() {
		
		
		window = new Window(WIDTH, HEIGHT, "GAME");
		shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
		renderer = new Renderer(window, shader);
		window.setBackgroundColor(1.0f, 0.0f, 0.0f);
		window.create();
		shader.create();
		
	}
	
	public void run() {
		
		init();
		while (!window.shouldClose() && !Input.isKeyDown(GLFW_KEY_ESCAPE)) {
			
			update();
			render();
			
			if(Input.isKeyDown(GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
			if (Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) window.setMouseState(true);;
			
		}
		
		close();
		
	}
	
	private void update() {
		
		window.update();
		camera.update();
		
	}
	
	private void close() {
		
		window.destroy();
		blocks.destroy();
		shader.destroy();
		
	}
	
	private void render() {
		
		renderer.renderMesh(block.create(), camera);
		window.swapBuffers();
		
	}
	
	public static void main(String[] args) {
		
		new Main().start();

	}

}
