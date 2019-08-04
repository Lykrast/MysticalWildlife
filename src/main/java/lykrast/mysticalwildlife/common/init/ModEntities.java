package lykrast.mysticalwildlife.common.init;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
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
	public static List<Item> eggs;
	
	public static void initEntities() {
		//Need them constructed to register their spawn eggs
		//So this is called in ModItems
		entities = new ArrayList<>();
		eggs = new ArrayList<>();
		
		vrontausaurus = create(EntityVrontausaurus::new, "vrontausaurus", 2.2F, 1.4F, 0x515151, 0xd3d3d3);
		yagaHog = create(EntityYagaHog::new, "yaga_hog", 0.9F, 0.9F, 0x9c7f5f, 0x443225);
		duskLurker = create(EntityDuskLurker::new, "dusk_lurker", 0.9F, 0.9F, 0x262626, 0x808080);
		cicapteraAzure = create(EntityCicaptera.Azure::new, "cicaptera_azure", 0.9F, 0.4F, 0x0084d7, 0x262626);
		cicapteraVerdant = create(EntityCicaptera.Verdant::new, "cicaptera_verdant", 0.9F, 0.4F, 0x4f6028, 0x262626);
		cicapteraCrimson = create(EntityCicaptera.Crimson::new, "cicaptera_crimson", 0.9F, 0.4F, 0x8b2b29, 0x262626);
		cicapteraSandy = create(EntityCicaptera.Sandy::new, "cicaptera_sandy", 0.9F, 0.4F, 0xbdb98A, 0x262626);
		cicapteraWintry = create(EntityCicaptera.Wintry::new, "cicaptera_wintry", 0.9F, 0.4F, 0xcad7d7, 0x262626);
		cicapteraLovely = create(EntityCicaptera.Lovely::new, "cicaptera_lovely", 0.9F, 0.4F, 0xdf64cf, 0x262626);
		plumper = create(EntityPlumper::new, "plumper", 0.9F, 0.6F, 0x9a947b, 0x797461);
		krill = create(EntityKrill::new, "krill", 0.75F, 0.45F, 0xfc6800, 0xf58000);
	}
	
	public static <T extends Entity> EntityType<T> create(EntityType.IFactory<T> factory, String name, float width, float height, int colorPrimary, int colorSecondary) {
		//Trying vanilla values for tracking range
		EntityType<T> e = EntityType.Builder.create(factory, EntityClassification.CREATURE).size(width, height).setTrackingRange(4).setUpdateInterval(3).build(name);
		e.setRegistryName(MysticalWildlife.MODID, name);
		
		entities.add(e);
		
		//Spawn egg
		Item egg= new SpawnEggItem(e, colorPrimary, colorSecondary, ModItems.defProps());
		egg.setRegistryName(MysticalWildlife.MODID, name + "_spawn_egg");
		eggs.add(egg);
		
		return e;
	}

	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		IForgeRegistry<EntityType<?>> reg = event.getRegistry();
		
		entities.forEach(reg::register);
		//Offer the list to the garbage collector now that it has outlive its usefulness
		entities = null;
		
//		EntityEntryBuilder<?> builder;
//		builder = create(EntityVrontausaurus.class, "vrontausaurus", 0x515151, 0xd3d3d3);
//		if (SPAWNING.vrontausaurus.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.vrontausaurus.weight, SPAWNING.vrontausaurus.minGroup, SPAWNING.vrontausaurus.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN));
//		register(builder);
//
//		builder = create(EntityYagaHog.class, "yaga_hog", 0x9c7f5f, 0x443225);
//		if (SPAWNING.yagaHog.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.yagaHog.weight, SPAWNING.yagaHog.minGroup, SPAWNING.yagaHog.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
//		register(builder);
//
//		builder = create(EntityDuskLurker.class, "dusk_lurker", 0x262626, 0x808080);
//		if (SPAWNING.duskLurkerForest.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.duskLurkerForest.weight, SPAWNING.duskLurkerForest.minGroup, SPAWNING.duskLurkerForest.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
//		if (SPAWNING.duskLurkerSpooky.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.duskLurkerSpooky.weight, SPAWNING.duskLurkerSpooky.minGroup, SPAWNING.duskLurkerSpooky.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.SPOOKY));
//		register(builder);
//        
//		builder = create(EntityCicaptera.Azure.class, "cicaptera_azure", 0x0084d7, 0x262626);
//		if (SPAWNING.cicapteraAzure.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraAzure.weight, SPAWNING.cicapteraAzure.minGroup, SPAWNING.cicapteraAzure.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
//		register(builder);
//		builder = create(EntityCicaptera.Verdant.class, "cicaptera_verdant", 0x4f6028, 0x262626);
//		if (SPAWNING.cicapteraVerdant.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraVerdant.weight, SPAWNING.cicapteraVerdant.minGroup, SPAWNING.cicapteraVerdant.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
//		register(builder);
//		builder = create(EntityCicaptera.Crimson.class, "cicaptera_crimson", 0x8b2b29, 0x262626);
//		if (SPAWNING.cicapteraCrimson.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraCrimson.weight, SPAWNING.cicapteraCrimson.minGroup, SPAWNING.cicapteraCrimson.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.HOT));
//		register(builder);
//		builder = create(EntityCicaptera.Sandy.class, "cicaptera_sandy", 0xbdb98A, 0x262626);
//		if (SPAWNING.cicapteraSandy.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraSandy.weight, SPAWNING.cicapteraSandy.minGroup, SPAWNING.cicapteraSandy.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
//		register(builder);
//		builder = create(EntityCicaptera.Wintry.class, "cicaptera_wintry", 0xcad7d7, 0x262626);
//		if (SPAWNING.cicapteraWintry.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraWintry.weight, SPAWNING.cicapteraWintry.minGroup, SPAWNING.cicapteraWintry.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
//		register(builder);
//		builder = create(EntityCicaptera.Lovely.class, "cicaptera_lovely", 0xdf64cf, 0x262626);
//		if (SPAWNING.cicapteraLovely.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.cicapteraLovely.weight, SPAWNING.cicapteraLovely.minGroup, SPAWNING.cicapteraLovely.maxGroup, 
//				getLovelyBiomes());
//		register(builder);
//        
//		builder = create(EntityPlumper.class, "plumper", 0x9a947b, 0x797461);
//		if (SPAWNING.plumper.weight > 0) builder.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.plumper.weight, SPAWNING.plumper.minGroup, SPAWNING.plumper.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH));
//		register(builder);
//        
//		builder = create(EntityKrill.class, "krill", 0xfc6800, 0xf58000);
//		if (SPAWNING.krill.weight > 0) builder
//		.spawn(EnumCreatureType.CREATURE, 
//				SPAWNING.krill.weight, SPAWNING.krill.minGroup, SPAWNING.krill.maxGroup, 
//				BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH));
//		register(builder);
	}

	public static void makeSpawns() {
		BiomeDictionary.getBiomes(Type.MOUNTAIN).forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(vrontausaurus, 6, 3, 4)));
		BiomeDictionary.getBiomes(Type.SWAMP).forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(yagaHog, 12, 4, 5)));
		BiomeDictionary.getBiomes(Type.FOREST).forEach(b -> {
			b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(duskLurker, 8, 2, 4));
			b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(cicapteraVerdant, 10, 4, 6));
		});
		BiomeDictionary.getBiomes(Type.SPOOKY).forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(duskLurker, 14, 4, 6)));
		BiomeDictionary.getBiomes(Type.PLAINS).forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(cicapteraAzure, 10, 4, 6)));
		BiomeDictionary.getBiomes(Type.HOT).forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(cicapteraCrimson, 6, 3, 4)));
		BiomeDictionary.getBiomes(Type.SANDY).forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(cicapteraSandy, 10, 4, 6)));
		BiomeDictionary.getBiomes(Type.SNOWY).forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(cicapteraWintry, 10, 4, 6)));
		getLovelyBiomes().forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(cicapteraLovely, 6, 2, 3)));
		BiomeDictionary.getBiomes(Type.BEACH).forEach(b -> {
			b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(plumper, 12, 3, 5));
			b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(krill, 12, 1, 3));
		});
	}
	
	private static Set<Biome> getLovelyBiomes() {
		Set<Biome> set = new HashSet<>();
		set.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.MAGICAL));
		set.add(Biomes.SUNFLOWER_PLAINS);
		set.add(Biomes.FLOWER_FOREST);
		
		return set;
	}
	
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
