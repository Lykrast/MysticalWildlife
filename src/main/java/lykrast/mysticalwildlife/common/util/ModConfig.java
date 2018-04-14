package lykrast.mysticalwildlife.common.util;

import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = MysticalWildlife.MODID)
@LangKey("config." + MysticalWildlife.MODID + ".title")
public class ModConfig {
	public static final Spawning SPAWNING = new Spawning();
	
	@LangKey("config." + MysticalWildlife.MODID + ".spawning.title")
	public static class Spawning {
		//Vrontausaurus
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.vrontausaurus.weight")
		public int vrontausaurusWeight = 6;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.vrontausaurus.mingroup")
		public int vrontausaurusMinGroup = 3;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.vrontausaurus.maxgroup")
		public int vrontausaurusMaxGroup = 4;

		//Yaga Hog
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.yaga_hog.weight")
		public int yagaHogWeight = 12;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.yaga_hog.mingroup")
		public int yagaHogMinGroup = 4;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.yaga_hog.maxgroup")
		public int yagaHogMaxGroup = 5;

		//Dusk Lurker
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.dusk_lurker.forest.weight")
		public int duskLurkerForestWeight = 8;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.dusk_lurker.forest.mingroup")
		public int duskLurkerForestMinGroup = 2;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.dusk_lurker.forest.maxgroup")
		public int duskLurkerForestMaxGroup = 4;
		
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.dusk_lurker.spooky.weight")
		public int duskLurkerSpookyWeight = 14;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.dusk_lurker.spooky.mingroup")
		public int duskLurkerSpookyMinGroup = 4;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.dusk_lurker.spooky.maxgroup")
		public int duskLurkerSpookyMaxGroup = 6;
		
		//Cicaptera
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_azure.weight")
		public int cicapetraAzureWeight = 10;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_azure.mingroup")
		public int cicapetraAzureMinGroup = 4;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_azure.maxgroup")
		public int cicapetraAzureMaxGroup = 6;
		
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_verdant.weight")
		public int cicapetraVerdantWeight = 10;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_verdant.mingroup")
		public int cicapetraVerdantMinGroup = 4;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_verdant.maxgroup")
		public int cicapetraVerdantMaxGroup = 6;
		
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_crimson.weight")
		public int cicapetraCrimsonWeight = 6;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_crimson.mingroup")
		public int cicapetraCrimsonMinGroup = 3;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_crimson.maxgroup")
		public int cicapetraCrimsonMaxGroup = 4;
		
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_sandy.weight")
		public int cicapetraSandyWeight = 10;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_sandy.mingroup")
		public int cicapetraSandyMinGroup = 4;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_sandy.maxgroup")
		public int cicapetraSandyMaxGroup = 6;
		
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_wintry.weight")
		public int cicapetraWintryWeight = 10;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_wintry.mingroup")
		public int cicapetraWintryMinGroup = 4;
		@RangeInt(min = 0)
		@RequiresMcRestart
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.cicaptera_wintry.maxgroup")
		public int cicapetraWintryMaxGroup = 6;
	}
	
	@Mod.EventBusSubscriber(modid = MysticalWildlife.MODID)
	private static class EventHandler {

		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(MysticalWildlife.MODID)) {
				ConfigManager.sync(MysticalWildlife.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
