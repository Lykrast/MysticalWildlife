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
	@LangKey("config." + MysticalWildlife.MODID + ".spawning.title")
	public static final Spawning SPAWNING = new Spawning();

	@RequiresMcRestart
	@LangKey("config." + MysticalWildlife.MODID + ".brush")
	public static boolean brushEnabled = true;
	
	@RangeInt(min = 0)
	@LangKey("config." + MysticalWildlife.MODID + ".krill.forage_chance")
	public static int krillForageChance = 2000;
	
	@RangeInt(min = 0)
	@LangKey("config." + MysticalWildlife.MODID + ".cicaptera_lovely.essence_time.base")
	public static int cicapteraLovelyEssenceTimeBase = 12000;
	@RangeInt(min = 0)
	@LangKey("config." + MysticalWildlife.MODID + ".cicaptera_lovely.essence_time.extra")
	public static int cicapteraLovelyEssenceTimeExtra = 12000;
	
	public static class Spawning {

		@LangKey("entity." + MysticalWildlife.MODID + ".vrontausaurus.name")
		public Entry vrontausaurus = new Entry(6, 3, 4);
		
		@LangKey("entity." + MysticalWildlife.MODID + ".yaga_hog.name")
		public Entry yagaHog = new Entry(12, 4, 5);
		
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.dusk_lurker.forest")
		public Entry duskLurkerForest = new Entry(8, 2, 4);
		@LangKey("config." + MysticalWildlife.MODID + ".spawning.dusk_lurker.spooky")
		public Entry duskLurkerSpooky = new Entry(14, 4, 6);
		
		@LangKey("entity." + MysticalWildlife.MODID + ".cicaptera_azure.name")
		public Entry cicapteraAzure = new Entry(10, 4, 6);
		@LangKey("entity." + MysticalWildlife.MODID + ".cicaptera_verdant.name")
		public Entry cicapteraVerdant = new Entry(10, 4, 6);
		@LangKey("entity." + MysticalWildlife.MODID + ".cicaptera_crimson.name")
		public Entry cicapteraCrimson = new Entry(6, 3, 4);
		@LangKey("entity." + MysticalWildlife.MODID + ".cicaptera_sandy.name")
		public Entry cicapteraSandy = new Entry(10, 4, 6);
		@LangKey("entity." + MysticalWildlife.MODID + ".cicaptera_wintry.name")
		public Entry cicapteraWintry = new Entry(10, 4, 6);
		@LangKey("entity." + MysticalWildlife.MODID + ".cicaptera_lovely.name")
		public Entry cicapteraLovely = new Entry(6, 2, 3);
		
		@LangKey("entity." + MysticalWildlife.MODID + ".plumper.name")
		public Entry plumper = new Entry(12, 3, 5);
		
		@LangKey("entity." + MysticalWildlife.MODID + ".krill.name")
		public Entry krill = new Entry(12, 1, 3);
		
		public static class Entry {
			@RangeInt(min = 0)
			@RequiresMcRestart
			@LangKey("config." + MysticalWildlife.MODID + ".spawning.weight")
			public int weight;
			@RangeInt(min = 0)
			@RequiresMcRestart
			@LangKey("config." + MysticalWildlife.MODID + ".spawning.mingroup")
			public int minGroup;
			@RangeInt(min = 0)
			@RequiresMcRestart
			@LangKey("config." + MysticalWildlife.MODID + ".spawning.maxgroup")
			public int maxGroup;
			
			public Entry(int weight, int minGroup, int maxGroup) {
				this.weight = weight;
				this.minGroup = minGroup;
				this.maxGroup = maxGroup;
			}
		}
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
