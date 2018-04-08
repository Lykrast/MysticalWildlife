package lykrast.mysticalwildlife.common.init;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import lykrast.mysticalwildlife.client.render.*;
import lykrast.mysticalwildlife.common.entity.*;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ModEntities {
	
	private static int id = 1;
	private static List<EntityEntry> entityList = new ArrayList<>();
	
	public static void init()
	{
		register(create(EntityVrontausaurus.class, "vrontausaurus", 0x515151, 0xd3d3d3)
				.spawn(EnumCreatureType.CREATURE, 6, 3, 4, BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN)));
        LootTableList.register(EntityVrontausaurus.LOOT);
        
		register(create(EntityYagaHog.class, "yaga_hog", 0x9c7f5f, 0x443225)
				.spawn(EnumCreatureType.CREATURE, 12, 4, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP)));
        LootTableList.register(EntityYagaHog.LOOT);
        
		register(create(EntityDuskLurker.class, "dusk_lurker", 0x262626, 0x808080)
				.spawn(EnumCreatureType.CREATURE, 8, 2, 4, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
				.spawn(EnumCreatureType.CREATURE, 14, 4, 6, BiomeDictionary.getBiomes(BiomeDictionary.Type.SPOOKY)));
        LootTableList.register(EntityDuskLurker.LOOT);
        
		register(create(EntityCicaptera.class, "cicaptera_azure", 0x0084d7, 0x262626)
				.spawn(EnumCreatureType.CREATURE, 12, 4, 6, BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS)));
        LootTableList.register(EntityCicaptera.LOOT);
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
	{
		MysticalWildlife.logger.log(Level.INFO, "Registerings ENTITIES");
		for (EntityEntry e : entityList) event.getRegistry().register(e);
	}
	
	public static void register(EntityEntryBuilder<? extends Entity> entry) {
		entityList.add(entry.build());
	}
	
	public static EntityEntryBuilder<? extends Entity> create(Class<? extends Entity> entityClass, String name, int colorBack, int colorFront)
	{
		EntityEntryBuilder<Entity> builder = EntityEntryBuilder.create()
				.entity(entityClass)
				.name(MysticalWildlife.MODID + "." + name)
				.id(new ResourceLocation(MysticalWildlife.MODID, name), id++)
				.tracker(64, 3, true)
				.egg(colorBack, colorFront);
		return builder;
	}
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityVrontausaurus.class, RenderVrontausaurus.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityYagaHog.class, RenderYagaHog.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityDuskLurker.class, RenderDuskLurker.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityCicaptera.class, RenderCicapteraAzure.FACTORY);
    }

}
