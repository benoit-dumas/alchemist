package ECS.processor.logic.combat.resistance;

import com.simsilica.es.Entity;

import ECS.commonLogic.Spawner;
import ECS.component.combat.resistance.Attrition;
import ECS.component.combat.resistance.SpawnOnShieldDepleted;
import model.ECS.pipeline.Processor;

public class SpawnOnShieldDepletedProc extends Processor {

	@Override
	protected void registerSets() {
		registerDefault(SpawnOnShieldDepleted.class, Attrition.class);
	}
	
	@Override
	protected void onEntityUpdated(Entity e) {
		SpawnOnShieldDepleted spawn = e.get(SpawnOnShieldDepleted.class);
		Attrition att = e.get(Attrition.class);
		
		if(att.getActualShield() != spawn.getLastShieldValue()){
			if(att.getActualShield() == 0){
				Spawner.spawnWithSpawnerStance(entityData, e.getId(), spawn.getBlueprintNames());
			}
			setComp(e, new SpawnOnShieldDepleted(spawn.getBlueprintNames(), att.getActualShield()));
		}
	}
}