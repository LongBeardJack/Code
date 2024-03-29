package fr.bipbipgaming.engine.io;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class Input {
	
	private static boolean[] keys = new boolean[GLFW_KEY_LAST];
	private static boolean[] buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];
	private static double mouseX, mouseY;
	private static double scrollX, scrollY;
	
	private GLFWKeyCallback keyboard;
	private GLFWCursorPosCallback mouseMove;
	private GLFWMouseButtonCallback mouseButtons;
	private GLFWScrollCallback scroll;
	
	public Input() {
		
		keyboard = new GLFWKeyCallback() {
			
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				keys[key] = (action != GLFW_RELEASE);
			}
		};
		
		mouseMove = new GLFWCursorPosCallback() {

			@Override
			public void invoke(long window, double xPos, double yPos) {
				
				mouseX = xPos;
				mouseY = yPos;
				
			}
		};
		
		mouseButtons = new GLFWMouseButtonCallback() {
			
			@Override
			public void invoke(long window, int button, int action, int mods) {
				buttons[button] = (action != GLFW_RELEASE);
			}

		};
		
		scroll = new GLFWScrollCallback() {
			
			@Override
			public void invoke(long window, double x, double y) {
				
				scrollX += x;
				scrollY += y;
				
			}
		};
		
	}
	
	public static boolean isKeyDown(int key) {
		return keys[key];
	}
	
	public static boolean isMouseButtonDown(int button) {
		return buttons[button];
	}
	
	public void destroy() {
		keyboard.free();
		mouseMove.free();
		mouseButtons.free();
		scroll.free();
	}

	public static double getMouseX() {
		return mouseX;
	}

	public static double getMouseY() {
		return mouseY;
	}
	
	public static double getMouseScrollX() {
		return scrollX;
	}

	public static double getMouseScrollY() {
		return scrollY;
	}

	public GLFWKeyCallback getKeyboardCallback() {
		return keyboard;
	}

	public GLFWCursorPosCallback getMouseMoveCallback() {
		return mouseMove;
	}

	public GLFWMouseButtonCallback getMouseButtonsCallback() {
		return mouseButtons;
	}
	
	public GLFWScrollCallback getMouseScrollCallback() {
		return scroll;
	}
	
}
