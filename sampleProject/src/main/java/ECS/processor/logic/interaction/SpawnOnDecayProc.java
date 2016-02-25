package ECS.processor.logic.interaction;

import com.simsilica.es.Entity;
import com.simsilica.es.EntityId;

import ECS.component.lifeCycle.SpawnOnDecay;
import ECS.component.lifeCycle.ToRemove;
import ECS.component.motion.PlanarStance;
import model.ECS.blueprint.BlueprintLibrary;
import model.ECS.pipeline.Processor;

/**
 * Must be called before ToRemove processor
 * @author Benoît
 *
 */
public class SpawnOnDecayProc extends Processor {

	@Override
	protected void registerSets() {
		registerDefault(SpawnOnDecay.class, ToRemove.class);
	}
	
	@Override
	protected void onEntityAdded(Entity e) {
		SpawnOnDecay spawn = e.get(SpawnOnDecay.class);

		for(String bpName : spawn.getBlueprintNames()) {
			EntityId spawned = BlueprintLibrary.getBlueprint(bpName).createEntity(entityData, null);
			PlanarStance stance = entityData.getComponent(e.getId(), PlanarStance.class);
			if(stance != null && entityData.getComponent(spawned, PlanarStance.class) != null)
				entityData.setComponent(spawned, stance);
		}
	}
}