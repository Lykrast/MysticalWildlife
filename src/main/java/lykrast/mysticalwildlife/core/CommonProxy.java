package lykrast.mysticalwildlife.core;

import lykrast.mysticalwildlife.common.init.ModEntities;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public static Configuration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
//		File directory = e.getModConfigurationDirectory();
//        config = new Configuration(new File(directory.getPath(), "mystical_wildlife.cfg"));
//        ModConfig.readConfig();
        
		ModEntities.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	}

}
