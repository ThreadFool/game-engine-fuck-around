package threadfool.op.engine.core;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.glfw.GLFW;

import threadfool.op.engine.platform.input.InputSystem;
import threadfool.op.engine.platform.window.Window;
import threadfool.op.engine.render.Camera;
import threadfool.op.engine.render.shapes.SquareRenderer;
import threadfool.op.engine.render.shapes.TriangleRenderer;
import threadfool.op.engine.scene.Transform;

public class GameLoop
{
	float x = 0f;
	float y = 0f;
	float speed = 1.5f;
	boolean isfirtsrun = true;
	private final Window window;
	private final TriangleRenderer triangleRenderer;
	private final SquareRenderer squareRenderer;

	public GameLoop(Window window){
		this.window = window;
		this.triangleRenderer = new TriangleRenderer();
		this.squareRenderer = new SquareRenderer();
	}

	public void run() throws InterruptedException
	{
		Time.init();

		Transform t1 = new Transform();
		Transform t2 = new Transform();

		Camera cam = new Camera();

		t1.position.set(100,100);
		t2.position.set(300,100);

		InputSystem input = new InputSystem(window);

		while(!window.shouldClose()){
			Time.update();

			if(input.isKeyDown(GLFW.GLFW_KEY_A)) x -= speed * Time.delta();
			if(input.isKeyDown(GLFW.GLFW_KEY_D)) x += speed * Time.delta();
			if(input.isKeyDown(GLFW.GLFW_KEY_W)) y += speed * Time.delta();
			if(input.isKeyDown(GLFW.GLFW_KEY_S)) y -= speed * Time.delta();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


			t1.scale.set(100, 100);
			t1.rotation += 1.5f * Time.delta();
			t1.position.set(400, 300);
			squareRenderer.render(t1, cam, window);

			window.update();
		}
	}
}
