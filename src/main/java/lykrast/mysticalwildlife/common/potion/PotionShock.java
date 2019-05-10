package lykrast.mysticalwildlife.common.potion;

import net.minecraft.entity.EntityLivingBase;

public class PotionShock extends PotionGeneric {

	public PotionShock() {
		super(true, 0x07c4ff, 0, 2);
	}
	
	@Override
	public void performEffect(EntityLivingBase entity, int amplifier) {
		float multiplier = (amplifier + 1) * 20.0F;
		
		entity.rotationPitch += (entity.world.rand.nextFloat() - 0.5F) * multiplier;
		entity.rotationYaw += (entity.world.rand.nextFloat() - 0.5F) * multiplier;
    }

}
