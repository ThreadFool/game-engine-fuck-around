package threadfool.op.engine.render;

import org.joml.Matrix4f;

import threadfool.op.engine.gpu.Mesh;
import threadfool.op.engine.gpu.Shader;
import threadfool.op.engine.math.MathUtil;
import threadfool.op.engine.platform.window.Window;
import threadfool.op.engine.scene.Transform;

public class Renderer
{

	protected Shader shader;

	protected static final String VERTEX = """
			#version 330 core
			layout (location = 0) in vec3 aPos;
			   
			uniform mat4 u_MVP;
			   
			void main(){
			    gl_Position = u_MVP * vec4(aPos, 1.0);
			}""";

	protected static final String FRAGMENT = """
			#version 330 core
			out vec4 FragColor;
			   
			void main(){
			    FragColor = vec4(0.2, 0.6, 1.0, 1.0);
			}""";

	public Renderer()
	{
		shader = new Shader(VERTEX,FRAGMENT);
	}

	public void render(Transform transform, Camera cam, Window window)
	{
		shader.bind();
		var triangle = new Mesh(new float[]{-0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.0f, 0.5f, 0f});
		Matrix4f mvp = MathUtil.buildMVP(transform, cam, 800, 600);

		shader.setMat4("u_MVP", mvp);
		triangle.draw();
	}
}
