package lykrast.mysticalwildlife.common.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;

public class PotionBreeding extends PotionGeneric {

	public PotionBreeding() {
		super(false, 0xff13b0, 1, 10);
	}
	
	@Override
	public void performEffect(EntityLivingBase entity, int amplifier) {
		if (!(entity instanceof EntityAnimal)) {
			entity.removePotionEffect(this);
			return;
		}
		
		EntityAnimal animal = (EntityAnimal)entity;
		//Ready to breed
		if (animal.getGrowingAge() == 0 && !animal.isInLove()) {
			animal.setInLove(null);
		}
		//In breeding cooldown
		else if (animal.getGrowingAge() > 0) {
			animal.setGrowingAge(Math.max(0, animal.getGrowingAge() - (amplifier + 1) * tickrate));
		}
    }

}
