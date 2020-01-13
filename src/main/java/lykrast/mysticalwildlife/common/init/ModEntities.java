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
import lykrast.mysticalwildlife.common.util.ConfigHandler;
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
		makeSpawns();
	}

	public static void makeSpawns() {
		if (ConfigHandler.vrontausaurus.isSpawnEnabled()) BiomeDictionary.getBiomes(Type.MOUNTAIN).stream().filter(ModEntities::isOverworld)
			.forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(vrontausaurus, ConfigHandler.vrontausaurus.getWeight(), ConfigHandler.vrontausaurus.getMin(), ConfigHandler.vrontausaurus.getMax())));
		if (ConfigHandler.yagaHog.isSpawnEnabled()) BiomeDictionary.getBiomes(Type.SWAMP).stream().filter(ModEntities::isOverworld)
			.forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(yagaHog, ConfigHandler.yagaHog.getWeight(), ConfigHandler.yagaHog.getMin(), ConfigHandler.yagaHog.getMax())));
		BiomeDictionary.getBiomes(Type.FOREST).stream().filter(ModEntities::isOverworld).forEach(b -> {
			if (ConfigHandler.duskLurker.isSpawnEnabled()) b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(duskLurker, ConfigHandler.duskLurker.getWeight(), ConfigHandler.duskLurker.getMin(), ConfigHandler.duskLurker.getMax()));
			if (ConfigHandler.cicapteraVerdant.isSpawnEnabled()) b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(cicapteraVerdant, ConfigHandler.cicapteraVerdant.getWeight(), ConfigHandler.cicapteraVerdant.getMin(), ConfigHandler.cicapteraVerdant.getMax()));
		});
		//BiomeDictionary.getBiomes(Type.SPOOKY).stream().filter(ModEntities::isOverworld).forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(new SpawnListEntry(duskLurker, 14, 4, 6)));
		if (ConfigHandler.cicapteraAzure.isSpawnEnabled()) BiomeDictionary.getBiomes(Type.PLAINS).stream().filter(ModEntities::isOverworld)
			.forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(cicapteraAzure, ConfigHandler.cicapteraAzure.getWeight(), ConfigHandler.cicapteraAzure.getMin(), ConfigHandler.cicapteraAzure.getMax())));
		if (ConfigHandler.cicapteraCrimson.isSpawnEnabled()) BiomeDictionary.getBiomes(Type.HOT).stream().filter(ModEntities::isOverworld)
			.forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(cicapteraCrimson, ConfigHandler.cicapteraCrimson.getWeight(), ConfigHandler.cicapteraCrimson.getMin(), ConfigHandler.cicapteraCrimson.getMax())));
		if (ConfigHandler.cicapteraSandy.isSpawnEnabled()) BiomeDictionary.getBiomes(Type.SANDY).stream().filter(ModEntities::isOverworld)
			.forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(cicapteraSandy, ConfigHandler.cicapteraSandy.getWeight(), ConfigHandler.cicapteraSandy.getMin(), ConfigHandler.cicapteraSandy.getMax())));
		if (ConfigHandler.cicapteraWintry.isSpawnEnabled()) BiomeDictionary.getBiomes(Type.SNOWY).stream().filter(ModEntities::isOverworld)
			.forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(cicapteraWintry, ConfigHandler.cicapteraWintry.getWeight(), ConfigHandler.cicapteraWintry.getMin(), ConfigHandler.cicapteraWintry.getMax())));
		if (ConfigHandler.cicapteraLovely.isSpawnEnabled()) getLovelyBiomes()
			.forEach(b -> b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(cicapteraLovely, ConfigHandler.cicapteraLovely.getWeight(), ConfigHandler.cicapteraLovely.getMin(), ConfigHandler.cicapteraLovely.getMax())));
		BiomeDictionary.getBiomes(Type.BEACH).stream().filter(ModEntities::isOverworld).forEach(b -> {
			if (ConfigHandler.plumper.isSpawnEnabled()) b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(plumper, ConfigHandler.plumper.getWeight(), ConfigHandler.plumper.getMin(), ConfigHandler.plumper.getMax()));
			if (ConfigHandler.krill.isSpawnEnabled()) b.getSpawns(EntityClassification.CREATURE).add(
					new SpawnListEntry(krill, ConfigHandler.krill.getWeight(), ConfigHandler.krill.getMin(), ConfigHandler.krill.getMax()));
		});
	}
	
	private static boolean isOverworld(Biome b) {
		return !BiomeDictionary.hasType(b, Type.NETHER) && !BiomeDictionary.hasType(b, Type.END);
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
