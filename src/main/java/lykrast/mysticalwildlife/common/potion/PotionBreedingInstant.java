package lykrast.mysticalwildlife.common.potion;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;

public class PotionBreedingInstant extends PotionGeneric {

	public PotionBreedingInstant() {
		super(false, 0xb113ff, 2, 0);
	}
	
	@Override
	public void performEffect(EntityLivingBase entity, int amplifier) {
		if (!(entity instanceof EntityAnimal)) {
			entity.removePotionEffect(this);
			return;
		}
		
		EntityAnimal animal = (EntityAnimal)entity;
		//Ready to breed
		if (animal.getGrowingAge() == 0) {
			animal.setInLove(null);
		}
		//In breeding cooldown
		else if (animal.getGrowingAge() > 0) {
			animal.setGrowingAge(0);
			animal.setInLove(null);
		}
    }

    @Override
	public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entity, int amplifier, double health) {
		if (!(entity instanceof EntityAnimal)) {
			return;
		}
		
		EntityAnimal animal = (EntityAnimal)entity;
		EntityPlayer sourcePlayer = null;
		if (indirectSource instanceof EntityPlayer) sourcePlayer = (EntityPlayer)indirectSource;
		//Ready to breed
		if (animal.getGrowingAge() == 0) {
			animal.setInLove(sourcePlayer);
		}
		//In breeding cooldown
		else if (animal.getGrowingAge() > 0) {
			animal.setGrowingAge(0);
			animal.setInLove(sourcePlayer);
		}
	}
	
	@Override
	public boolean isInstant() {
		return true;
	}

}
