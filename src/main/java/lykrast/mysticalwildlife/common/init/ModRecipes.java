package lykrast.mysticalwildlife.common.init;

import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MysticalWildlife.MODID)
public class ModRecipes {
	public static final String meatRaw = "listAllmeatraw",
			meatCooked = "listAllmeatcooked",
			leather = "leather";
	//TODO: Brewing and tags (need forge on those)
	
//	@SubscribeEvent
//	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
//		initSmelting();
//		initBrewing();
//	}
	
	private static void initBrewing() {
//		if (ModConfig.potionBreedingEnabled) {
//			PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.aphroditeEssence, ModPotions.potBreeding);
//			PotionHelper.addMix(ModPotions.potBreeding, Items.REDSTONE, ModPotions.potBreedingLong);
//			PotionHelper.addMix(ModPotions.potBreeding, Items.GLOWSTONE_DUST, ModPotions.potBreedingStrong);
//			if (ModConfig.potionBreedingInstantEnabled) PotionHelper.addMix(ModPotions.potBreeding, Items.SUGAR, ModPotions.potBreedingInstant);
//		}
//		else if (ModConfig.potionBreedingInstantEnabled) PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.aphroditeEssence, ModPotions.potBreedingInstant);
	}
}
