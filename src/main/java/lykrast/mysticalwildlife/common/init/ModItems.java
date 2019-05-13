package lykrast.mysticalwildlife.common.init;

import lykrast.mysticalwildlife.common.item.ItemBrush;
import lykrast.mysticalwildlife.common.item.ItemFuel;
import lykrast.mysticalwildlife.common.util.ItemGroupMysticalWildlife;
import lykrast.mysticalwildlife.common.util.ModConfig;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.entity.EntityType;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MysticalWildlife.MODID)
public class ModItems {
	public static Item brush,
		vrontausaurusFur, vrontausaurusFurTuft, vrontausaurusRaw, vrontausaurusCooked,
		yagaHogRaw, yagaHogCooked,
		duskAsh, duskLurkerFur, duskLurkerFurTuft, duskLurkerRaw, duskLurkerCooked,
		cicapteraRaw, cicapteraCooked, cicapteraHuskAzure, cicapteraHuskVerdant, cicapteraHuskCrimson, cicapteraHuskSandy, cicapteraHuskWintry, cicapteraHuskLovely, aphroditeEssence,
		plumperBlubber, plumperRaw, plumperCooked,
		krillRaw, krillCooked;

    @SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
    	IForgeRegistry<Item> reg = event.getRegistry();
    	
    	Item.Properties def = new Item.Properties().group(ItemGroupMysticalWildlife.INSTANCE);
    	
		//Tools
		if (ModConfig.brushEnabled) brush = initItem(reg, new ItemBrush(new Item.Properties().defaultMaxDamage(65).group(ItemGroupMysticalWildlife.INSTANCE)), "brush");
		
		//Monsters
		vrontausaurusFur = initItem(reg, new Item(def), "vrontausaurus_fur");
		vrontausaurusFurTuft = initItem(reg, new Item(def), "vrontausaurus_fur_tuft");
		vrontausaurusRaw = initItem(reg, new ItemFood(3, 0.4F, true, def), "vrontausaurus_meat_raw");
		vrontausaurusCooked = initItem(reg, new ItemFood(10, 0.9F, true, def), "vrontausaurus_meat_cooked");
		
		yagaHogRaw = initItem(reg, new ItemFood(2, 0.3F, true, def).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.6F), "yaga_hog_meat_raw");
		yagaHogCooked = initItem(reg, new ItemFood(6, 0.7F, true, def), "yaga_hog_meat_cooked");

		duskAsh = initItem(reg, new Item(def), "dusk_ash");
		duskLurkerFur = initItem(reg, new Item(def), "dusk_lurker_fur");
		duskLurkerFurTuft = initItem(reg, new Item(def), "dusk_lurker_fur_tuft");
		duskLurkerRaw = initItem(reg, new ItemFood(2, 0.5F, true, def), "dusk_lurker_meat_raw");
		duskLurkerCooked = initItem(reg, new ItemFood(6, 1.2F, true, def), "dusk_lurker_meat_cooked");
		
		cicapteraRaw = initItem(reg, new ItemFood(3, 0.4F, true, def), "cicaptera_meat_raw");
		cicapteraCooked = initItem(reg, new ItemFood(8, 0.8F, true, def), "cicaptera_meat_cooked");
		cicapteraHuskAzure = initItem(reg, new Item(def), "cicaptera_husk_azure");
		cicapteraHuskVerdant = initItem(reg, new Item(def), "cicaptera_husk_verdant");
		cicapteraHuskCrimson = initItem(reg, new Item(def), "cicaptera_husk_crimson");
		cicapteraHuskSandy = initItem(reg, new Item(def), "cicaptera_husk_sandy");
		cicapteraHuskWintry = initItem(reg, new Item(def), "cicaptera_husk_wintry");
		cicapteraHuskLovely = initItem(reg, new Item(def), "cicaptera_husk_lovely");
		aphroditeEssence = initItem(reg, new Item(def), "aphrodite_essence");
		
		plumperBlubber = initItem(reg, new ItemFuel(200, def), "plumper_blubber");
		plumperRaw = initItem(reg, new ItemFood(2, 0.6F, true, def), "plumper_meat_raw");
		plumperCooked = initItem(reg, new ItemFood(8, 1.0F, true, def), "plumper_meat_cooked");
		
		krillRaw = initItem(reg, new ItemFood(2, 0.1F, true, def), "krill_meat_raw");
		krillCooked = initItem(reg, new ItemFood(4, 0.6F, true, def), "krill_meat_cooked");
		
		//Eggs
		Item.Properties egg = new Item.Properties().group(ItemGroup.MISC);
		for (EntityType<?> e : ModEntities.entities) {
			initItem(reg, new ItemSpawnEgg(e, 0xffffff, 0xffffff, egg), e.getRegistryName().getPath() + "_spawn_egg");
		}
	}
	
	public static Item initItem(IForgeRegistry<Item> reg, Item item, String name) {
		item.setRegistryName(MysticalWildlife.MODID, name);
		reg.register(item);
		return item;
	}
}
