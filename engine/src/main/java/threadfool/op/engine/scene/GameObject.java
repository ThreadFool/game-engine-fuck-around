package threadfool.op.engine.scene;

import threadfool.op.engine.platform.window.Window;
import threadfool.op.engine.render.Camera;
import threadfool.op.engine.render.Renderer;

public class GameObject
{
	public Transform transform = new Transform();
	private Renderer renderer;

	public GameObject(Renderer renderer){
		this.renderer = renderer;
	}

	public void update(){

	}

	public void render(Camera cam, Window window){
		renderer.render(transform, cam, window);
	}
}
