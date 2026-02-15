package threadfool.op.engine.core;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.Random;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import threadfool.op.engine.platform.input.InputSystem;
import threadfool.op.engine.platform.window.Window;
import threadfool.op.engine.render.Camera;
import threadfool.op.engine.render.Renderer;
import threadfool.op.engine.render.shapes.SquareRenderer;
import threadfool.op.engine.render.shapes.TriangleRenderer;
import threadfool.op.engine.scene.GameObject;
import threadfool.op.engine.scene.Scene;
import threadfool.op.engine.scene.Transform;

public class GameLoop
{
	float x = 0f;
	float y = 0f;
	float speed = 1.5f;
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
		Scene scene = new Scene();
		Camera cam = new Camera();
		t1.position.set(100,100);


		GameObject gameObject = new GameObject(squareRenderer);
		gameObject.transform.position.set(300f, 300f);
		scene.add(gameObject);
		gameObject.transform.scale.set(100, 100);

		InputSystem input = new InputSystem(window);

		while(!window.shouldClose()){
			Time.update();

			Random random = new Random();
			float rf = random.nextFloat();
			if(input.isKeyDown(GLFW.GLFW_KEY_A)) x -= speed * Time.delta();
			if(input.isKeyDown(GLFW.GLFW_KEY_D)) x += speed * Time.delta();
			if(input.isKeyDown(GLFW.GLFW_KEY_W)) y += speed * Time.delta();
			if(input.isKeyDown(GLFW.GLFW_KEY_S)) y -= speed * Time.delta();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			gameObject.transform.position=new Vector2f(300+rf,300f+rf);
			scene.update();
			scene.render(cam, window);

			//squareRenderer.render(t1, cam, window);

			window.update();
		}
	}
}
