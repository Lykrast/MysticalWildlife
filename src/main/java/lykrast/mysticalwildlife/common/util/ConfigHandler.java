package lykrast.mysticalwildlife.common.util;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ConfigHandler {
	
	public static class Common {
		public static final ForgeConfigSpec CONFIG_SPEC;
		public static final Common CONFIG;

		static {
			Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);

			CONFIG_SPEC = specPair.getRight();
			CONFIG = specPair.getLeft();
		}
		
		public IntValue krillForageTime;
		public IntValue cicapteraLovelyEssenceTime;
		
		public Common(ForgeConfigSpec.Builder builder) {
			krillForageTime = builder
					.translation("config.mysticalwildlife.krill.forage_time")
					.comment("Base time between Krill forages (can be up to 2x that amount in game)")
					.defineInRange("krillForageTime", 1800, 1, Integer.MAX_VALUE / 2);
			
			cicapteraLovelyEssenceTime = builder
					.translation("config.mysticalwildlife.cicaptera_lovely.essence_time")
					.comment("Base time between Aphrodite Essence drops (can be up to 2x that amount in game)")
					.defineInRange("cicapteraLovelyEssenceTime", 12000, 1, Integer.MAX_VALUE / 2);
		}
	}
	
	//Don't seem we need it for now
//	public static void configChanged(ModConfigEvent event) {
//		MysticalWildlife.LOGGER.info("Config changed!");
//		if(event.getConfig().getModId().equals(MysticalWildlife.MODID)) { 
//			MysticalWildlife.LOGGER.info("It's ours!");
//		}
//	}
}
