package ECS.processor.logic.interaction.damage;

import com.simsilica.es.Entity;

import ECS.component.combat.damage.DamageCapacity;
import ECS.component.combat.damage.Damaging;
import ECS.component.combat.resistance.Attrition;
import ECS.component.combat.resistance.Shield;
import ECS.component.lifeCycle.ToRemove;
import model.ECS.pipeline.Processor;

public class DamagingProc extends Processor {
	
	@Override
	protected void registerSets() {
		registerDefault(Damaging.class, DamageCapacity.class);
	}
	
	@Override
	protected void onEntityAdded(Entity e) {
		Damaging damaging = e.get(Damaging.class);
		Attrition att = entityData.getComponent(damaging.target, Attrition.class);
		if(att != null){
			// the target is damageable by attrition
			DamageCapacity capacity = e.get(DamageCapacity.class);
			DamageApplier applier = new DamageApplier(att, capacity.getType(), capacity.getBase());
			entityData.setComponent(damaging.target, applier.getResult());
			
			if(applier.getDamageOnShield() > 0){
				DamageFloatingLabelCreator.create(entityData, damaging.target, capacity.getType(), applier.getDamageOnShield(), true, false);
				Shield shield = entityData.getComponent(damaging.target, Shield.class);
				if(shield != null)
					entityData.setComponent(damaging.target, new Shield(shield.getCapacity(), shield.getRechargeRate(), shield.getRechargeDelay(), shield.getRechargeDelay()));
			}
			if(applier.getDamageOnHitPoints() > 0)
				DamageFloatingLabelCreator.create(entityData, damaging.target, capacity.getType(), applier.getDamageOnHitPoints(), false, false);
		}
		setComp(e, new ToRemove());
	}
}