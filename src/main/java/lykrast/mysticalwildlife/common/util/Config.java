package lykrast.mysticalwildlife.common.util;

import org.apache.logging.log4j.Level;

import lykrast.mysticalwildlife.core.CommonProxy;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraftforge.common.config.Configuration;

public class Config {
	private static final String CATEGORY_GENERAL = "General";
	
	//General
	public static int test;
	
	//Automation
	public static int linearExtractorDelay, linearExtractorMaxStack;
	
	public static void readConfig() {
		Configuration cfg = CommonProxy.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
		} catch (Exception e) {
			MysticalWildlife.logger.log(Level.WARN, "Problem loading config file!", e);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}
	
	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");

		//-----------
		// General
		//-----------
		test = cfg.getInt("test", CATEGORY_GENERAL, 0, -100, 100, 
				"This does nothing");
	}

}
