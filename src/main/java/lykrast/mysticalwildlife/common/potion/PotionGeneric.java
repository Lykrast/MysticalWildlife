package lykrast.mysticalwildlife.common.potion;

import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionGeneric extends Potion {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(MysticalWildlife.MODID, "textures/gui/potion_effects.png");
	protected int tickrate;

	public PotionGeneric(boolean isBadEffectIn, int liquidColorIn, int icon, int rate) {
		super(isBadEffectIn, liquidColorIn);
		setIconIndex(icon % 8, icon / 8);
		tickrate = rate;
	}

	@Override
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		return super.getStatusIconIndex();
	}

	@Override
	public void performEffect(EntityLivingBase entity, int amplifier)
    {
    }
	
	@Override
    public boolean isReady(int duration, int amplifier)
    {
		if (tickrate < 0) return false;
		
		return tickrate <= 0 || duration % tickrate == 0;
    }

}
