package lykrast.mysticalwildlife.common.init;

import static lykrast.mysticalwildlife.common.util.ModConfig.SPAWNING;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import lykrast.mysticalwildlife.client.render.RenderCicaptera;
import lykrast.mysticalwildlife.client.render.RenderDuskLurker;
import lykrast.mysticalwildlife.client.render.RenderKrill;
import lykrast.mysticalwildlife.client.render.RenderPlumper;
import lykrast.mysticalwildlife.client.render.RenderVrontausaurus;
import lykrast.mysticalwildlife.client.render.RenderYagaHog;
import lykrast.mysticalwildlife.common.entity.EntityCicaptera;
import lykrast.mysticalwildlife.common.entity.EntityDuskLurker;
import lykrast.mysticalwildlife.common.entity.EntityKrill;
import lykrast.mysticalwildlife.common.entity.EntityPlumper;
import lykrast.mysticalwildlife.common.entity.EntityVrontausaurus;
import lykrast.mysticalwildlife.common.entity.EntityYagaHog;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MysticalWildlife.MODID)
public class ModEntities {
	public static EntityType<EntityVrontausaurus> vrontausaurus;
	public static EntityType<EntityYagaHog> yagaHog;
	public static EntityType<EntityDuskLurker> duskLurker;
	public static EntityType<EntityCicaptera.Azure> cicapteraAzure;
	public static EntityType<EntityCicaptera.Verdant> cicapteraVerdant;
	public static EntityType<EntityCicaptera.Crimson> cicapteraCrimson;
	public static EntityType<EntityCicaptera.Sandy> cicapteraSandy;
	public static EntityType<EntityCicaptera.Wintry> cicapteraWintry;
	public static EntityType<EntityCicaptera.Lovely> cicapteraLovely;
	public static EntityType<EntityPlumper> plumper;
	public static EntityType<EntityKrill> krill;
	
	public static List<EntityType<?>> entities;
	
	public static void initEntities() {
		//Need them constructed to register their spawn eggs
		//So this is called in ModItems
		entities = new ArrayList<>();
		
		vrontausaurus = create(EntityVrontausaurus.class, EntityVrontausaurus::new, "vrontausaurus");
		yagaHog = create(EntityYagaHog.class, EntityYagaHog::new, "yaga_hog");
		duskLurker = create(EntityDuskLurker.class, EntityDuskLurker::new, "dusk_lurker");
		cicapteraAzure = create(EntityCicaptera.Azure.class, EntityCicaptera.Azure::new, "cicaptera_azure");
		cicapteraVerdant = create(EntityCicaptera.Verdant.class, EntityCicaptera.Verdant::new, "cicaptera_verdant");
		cicapteraCrimson = create(EntityCicaptera.Crimson.class, EntityCicaptera.Crimson::new, "cicaptera_crimson");
		cicapteraSandy = create(EntityCicaptera.Sandy.class, EntityCicaptera.Sandy::new, "cicaptera_sandy");
		cicapteraWintry = create(EntityCicaptera.Wintry.class, EntityCicaptera.Wintry::new, "cicaptera_wintry");
		cicapteraLovely = create(EntityCicaptera.Lovely.class, EntityCicaptera.Lovely::new, "cicaptera_lovely");
		plumper = create(EntityPlumper.class, EntityPlumper::new, "plumper");
		krill = create(EntityKrill.class, EntityKrill::new, "krill");
	}
	
	public static <T extends Entity> EntityType<T> create(Class<? extends T> clazz, Function<? super World, ? extends T> factory, String name) {
		EntityType<T> e = EntityType.Builder.create(clazz, factory).tracker(64, 3, true).build(name);
		e.setRegistryName(MysticalWildlife.MODID, name);
		entities.add(e);
		return e;
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		IForgeRegistry<EntityType<?>> reg = event.getRegistry();
		
		entities.forEach(reg::register);
		
//		EntityEntryBuilder<?> builder;
//		builder = create(EntityVrontausaurus.class, "vrontausaurus", 0x515151, 0xd3d3d3);
//		if (SPAWNING.vrontausaurus.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.vrontausaurus.weight, SPAWNING.vrontausaurus.minGroup, SPAWNING.vrontausaurus.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN));
//		register(builder);
//        LootTableList.register(EntityVrontausaurus.LOOT);
//        LootTableList.register(EntityVrontausaurus.LOOT_BRUSH);
//
//		builder = create(EntityYagaHog.class, "yaga_hog", 0x9c7f5f, 0x443225);
//		if (SPAWNING.yagaHog.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.yagaHog.weight, SPAWNING.yagaHog.minGroup, SPAWNING.yagaHog.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
//		register(builder);
//        LootTableList.register(EntityYagaHog.LOOT);
//        LootTableList.register(EntityYagaHog.LOOT_BRUSH);
//
//		builder = create(EntityDuskLurker.class, "dusk_lurker", 0x262626, 0x808080);
//		if (SPAWNING.duskLurkerForest.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.duskLurkerForest.weight, SPAWNING.duskLurkerForest.minGroup, SPAWNING.duskLurkerForest.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
//		if (SPAWNING.duskLurkerSpooky.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.duskLurkerSpooky.weight, SPAWNING.duskLurkerSpooky.minGroup, SPAWNING.duskLurkerSpooky.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.SPOOKY));
//		register(builder);
//        LootTableList.register(EntityDuskLurker.LOOT);
//        LootTableList.register(EntityDuskLurker.LOOT_BRUSH);
//        
//		builder = create(EntityCicaptera.Azure.class, "cicaptera_azure", 0x0084d7, 0x262626);
//		if (SPAWNING.cicapteraAzure.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraAzure.weight, SPAWNING.cicapteraAzure.minGroup, SPAWNING.cicapteraAzure.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
//		register(builder);
//        LootTableList.register(EntityCicaptera.Azure.LOOT);
//		builder = create(EntityCicaptera.Verdant.class, "cicaptera_verdant", 0x4f6028, 0x262626);
//		if (SPAWNING.cicapteraVerdant.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraVerdant.weight, SPAWNING.cicapteraVerdant.minGroup, SPAWNING.cicapteraVerdant.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
//		register(builder);
//        LootTableList.register(EntityCicaptera.Verdant.LOOT);
//		builder = create(EntityCicaptera.Crimson.class, "cicaptera_crimson", 0x8b2b29, 0x262626);
//		if (SPAWNING.cicapteraCrimson.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraCrimson.weight, SPAWNING.cicapteraCrimson.minGroup, SPAWNING.cicapteraCrimson.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.HOT));
//		register(builder);
//        LootTableList.register(EntityCicaptera.Crimson.LOOT);
//		builder = create(EntityCicaptera.Sandy.class, "cicaptera_sandy", 0xbdb98A, 0x262626);
//		if (SPAWNING.cicapteraSandy.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraSandy.weight, SPAWNING.cicapteraSandy.minGroup, SPAWNING.cicapteraSandy.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
//		register(builder);
//        LootTableList.register(EntityCicaptera.Sandy.LOOT);
//		builder = create(EntityCicaptera.Wintry.class, "cicaptera_wintry", 0xcad7d7, 0x262626);
//		if (SPAWNING.cicapteraWintry.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraWintry.weight, SPAWNING.cicapteraWintry.minGroup, SPAWNING.cicapteraWintry.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
//		register(builder);
//        LootTableList.register(EntityCicaptera.Wintry.LOOT);
//		builder = create(EntityCicaptera.Lovely.class, "cicaptera_lovely", 0xdf64cf, 0x262626);
//		if (SPAWNING.cicapteraLovely.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraLovely.weight, SPAWNING.cicapteraLovely.minGroup, SPAWNING.cicapteraLovely.maxGroup, 
//				getLovelyBiomes());
//		register(builder);
//        LootTableList.register(EntityCicaptera.Lovely.LOOT);
//        
//		builder = create(EntityPlumper.class, "plumper", 0x9a947b, 0x797461);
//		if (SPAWNING.plumper.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.plumper.weight, SPAWNING.plumper.minGroup, SPAWNING.plumper.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH));
//		register(builder);
//        LootTableList.register(EntityPlumper.LOOT);
//        
//		builder = create(EntityKrill.class, "krill", 0xfc6800, 0xf58000);
//		if (SPAWNING.krill.weight > 0) builder
//		.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.krill.weight, SPAWNING.krill.minGroup, SPAWNING.krill.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH));
//		register(builder);
//        LootTableList.register(EntityKrill.LOOT);
//        LootTableList.register(EntityKrill.LOOT_FORAGE);
	}
	
	private static Set<Biome> getLovelyBiomes() {
		Set<Biome> set = new HashSet<>();
		set.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.MAGICAL));
		set.add(Biomes.SUNFLOWER_PLAINS);
		set.add(Biomes.FLOWER_FOREST);
		
		return set;
	}
	
//	public static EntityType.Builder<? extends Entity> create(Class<? extends Entity> entityClass, String name, int colorBack, int colorFront) {
//		EntityType.Builder<Entity> builder = EntityType.Builder.create()
//				.entity(entityClass)
//				.name(MysticalWildlife.MODID + "." + name)
//				.id(new ResourceLocation(MysticalWildlife.MODID, name), id++)
//				.tracker(64, 3, true)
//				.egg(colorBack, colorFront);
//		return builder;
//	}
	
	@OnlyIn(Dist.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityVrontausaurus.class, RenderVrontausaurus::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityYagaHog.class, RenderYagaHog::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDuskLurker.class, RenderDuskLurker::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCicaptera.Azure.class, RenderCicaptera.Azure::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCicaptera.Verdant.class, RenderCicaptera.Verdant::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCicaptera.Crimson.class, RenderCicaptera.Crimson::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCicaptera.Sandy.class, RenderCicaptera.Sandy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCicaptera.Wintry.class, RenderCicaptera.Wintry::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCicaptera.Lovely.class, RenderCicaptera.Lovely::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPlumper.class, RenderPlumper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKrill.class, RenderKrill::new);
    }

}
