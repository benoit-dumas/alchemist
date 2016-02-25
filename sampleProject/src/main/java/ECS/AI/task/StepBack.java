package ECS.AI.task;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

import ECS.AI.blackboard.ShipBlackboard;
import ECS.component.motion.PlanarNeededThrust;

public class StepBack extends LeafTask<ShipBlackboard> {
	@Override
	public void run() {
		ShipBlackboard bb = getObject();
		bb.entityData.setComponent(bb.eid, new PlanarNeededThrust(bb.getVectorToEnemy().getNegation()));
		success();
	}

	@Override
	protected Task<ShipBlackboard> copyTo(Task<ShipBlackboard> task) {
		return task;
	}

}