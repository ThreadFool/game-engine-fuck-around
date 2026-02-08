package threadfool.op.engine.math;

import org.joml.Matrix4f;

import threadfool.op.engine.render.Camera;
import threadfool.op.engine.scene.Transform;

public class MathUtil
{
	public static Matrix4f buildMVP(Transform t, Camera cam, float w, float h){
		Matrix4f model = t.getModelMatrix();
		Matrix4f view = cam.getViewMatrix();
		Matrix4f proj = cam.getProjectionMatrix(w,h);

		return new Matrix4f(proj).mul(view).mul(model);
	}
}
