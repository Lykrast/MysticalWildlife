package lykrast.mysticalwildlife.common.init;

import java.util.ArrayList;
import java.util.List;

import lykrast.mysticalwildlife.common.potion.PotionGeneric;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModPotions {
	
	public static Potion shocked;
	private static List<Potion> potionList = new ArrayList<>();
	
	static
	{
		shocked = register(new PotionGeneric(true, 0x07c4ff, 0, 2) {
			@Override
			public void performEffect(EntityLivingBase entity, int amplifier)
		    {
				float multiplier = (amplifier + 1) * 20.0F;
				
				entity.rotationPitch += (entity.world.rand.nextFloat() - 0.5F) * multiplier;
				entity.rotationYaw += (entity.world.rand.nextFloat() - 0.5F) * multiplier;
		    }
		}, "shocked");
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event)
	{
		for (Potion p : potionList) event.getRegistry().register(p);
	}
	
	public static Potion register(Potion p, String name)
	{
		p.setPotionName("potion.effect." + MysticalWildlife.MODID + "." + name);
		p.setRegistryName(MysticalWildlife.MODID, name);
		potionList.add(p);
		return p;
	}

}
