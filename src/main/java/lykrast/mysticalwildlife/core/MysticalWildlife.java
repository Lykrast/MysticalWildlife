package lykrast.mysticalwildlife.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lykrast.mysticalwildlife.common.init.ModEntities;
import lykrast.mysticalwildlife.common.util.ConfigHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MysticalWildlife.MODID)
public class MysticalWildlife {
    public static final String MODID = "mysticalwildlife";
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public MysticalWildlife() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::doClientStuff);
		bus.addListener(this::setupCommon);
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.Common.CONFIG_SPEC);
	}

    private void doClientStuff(final FMLClientSetupEvent event) {
    	ModEntities.initModels();
    }
    
    private void setupCommon(final FMLCommonSetupEvent event) {
    	ModEntities.makeSpawns();
    }
}
