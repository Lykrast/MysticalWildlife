package lykrast.mysticalwildlife.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lykrast.mysticalwildlife.common.init.ModEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MysticalWildlife.MODID)
public class MysticalWildlife {
    public static final String MODID = "mysticalwildlife";
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public MysticalWildlife() {
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
	}
	
//	private void setup(final FMLCommonSetupEvent event) {
//    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    	ModEntities.initModels();
    }
}
