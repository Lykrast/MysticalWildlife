package lykrast.mysticalwildlife.common.init;

import lykrast.mysticalwildlife.common.potion.PotionBreeding;
import lykrast.mysticalwildlife.common.potion.PotionBreedingInstant;
import lykrast.mysticalwildlife.common.potion.PotionShock;
import lykrast.mysticalwildlife.common.util.ModConfig;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ModPotions {
	public static Potion shocked, breeding, breedingInstant;
	public static PotionType potBreeding, potBreedingLong, potBreedingStrong, potBreedingInstant;

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		IForgeRegistry<Potion> reg = event.getRegistry();
		shocked = register(reg, new PotionShock(), "shocked");
		breeding = register(reg, new PotionBreeding(), "breeding");
		breedingInstant = register(reg, new PotionBreedingInstant(), "breeding_instant");
	}
	
	public static Potion register(IForgeRegistry<Potion> reg, Potion p, String name) {
		p.setPotionName("potion.effect." + MysticalWildlife.MODID + "." + name);
		p.setRegistryName(MysticalWildlife.MODID, name);
		reg.register(p);
		return p;
	}
	
	@SubscribeEvent
	public static void registerPotionTypes(RegistryEvent.Register<PotionType> event) {
		IForgeRegistry<PotionType> reg = event.getRegistry();
		if (ModConfig.potionBreedingEnabled) {
			//Animals can baseline only breed every 6000 ticks = 5 minutes
			//Arousal divides that by (level + 1) so 3000 (2.5 minutes) for I and 2000 (1 min 40) for II
			potBreeding = register(reg, new PotionType("breeding", new PotionEffect(breeding, 3*60*20)), "breeding");
			potBreedingLong = register(reg, new PotionType("breeding", new PotionEffect(breeding, 6*60*20)), "breeding_long");
			potBreedingStrong = register(reg, new PotionType("breeding", new PotionEffect(breeding, 2*60*20, 1)), "breeding_strong");
		}
		if (ModConfig.potionBreedingInstantEnabled) potBreedingInstant = register(reg, new PotionType("breeding_instant", new PotionEffect(breedingInstant, 1)), "breeding_instant");
	}
	
	public static PotionType register(IForgeRegistry<PotionType> reg, PotionType p, String name) {
		p.setRegistryName(MysticalWildlife.MODID, name);
		reg.register(p);
		return p;
	}

}
