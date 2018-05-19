package lykrast.mysticalwildlife.core;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MysticalWildlife.MODID, name = MysticalWildlife.NAME, version = MysticalWildlife.VERSION, acceptedMinecraftVersions = "[1.12, 1.13)")
public class MysticalWildlife
{
    public static final String MODID = "mysticalwildlife";
    public static final String NAME = "Mystical Wildlife";
    public static final String VERSION = "@VERSION@";

	@Instance
	public static MysticalWildlife instance;
	
    public static Logger logger;
    
    @SidedProxy(clientSide = "lykrast.mysticalwildlife.core.ClientProxy", serverSide = "lykrast.mysticalwildlife.core.CommonProxy")
	public static CommonProxy proxy;
    
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}
