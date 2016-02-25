package ECS.processor.rendering;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.simsilica.es.Entity;

import ECS.component.motion.ChasingCamera;
import model.ECS.pipeline.Processor;
import model.tempImport.RendererPlatform;
import model.tempImport.TranslateUtil;

public class CameraPlacingProc extends Processor {

	@Override
	protected void registerSets() {
		registerDefault(ChasingCamera.class);
	}
	
	@Override
	protected void onEntityEachTick(Entity e) {
		ChasingCamera cc = e.get(ChasingCamera.class);
		Camera cam = RendererPlatform.getApp().getCamera();
		
		cam.setLocation(TranslateUtil.toVector3f(cc.pos));
		cam.lookAt(TranslateUtil.toVector3f(cc.target), Vector3f.UNIT_Y);
	}
}