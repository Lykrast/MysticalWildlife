package lykrast.mysticalwildlife.common.init;

import lykrast.mysticalwildlife.common.item.ItemBrush;
import lykrast.mysticalwildlife.common.item.ItemFuel;
import lykrast.mysticalwildlife.common.util.ItemGroupMysticalWildlife;
import lykrast.mysticalwildlife.common.util.ModConfig;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
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
    	
    	Item.Properties def = defProps();
    	
		//Tools
		if (ModConfig.brushEnabled) brush = initItem(reg, new ItemBrush(new Item.Properties().defaultMaxDamage(65).group(ItemGroupMysticalWildlife.INSTANCE)), "brush");
		
		//Monsters
		vrontausaurusFur = initItem(reg, new Item(def), "vrontausaurus_fur");
		vrontausaurusFurTuft = initItem(reg, new Item(def), "vrontausaurus_fur_tuft");
		vrontausaurusRaw = initItem(reg, new Item(defProps().food(food(3, 0.4).meat().build())), "vrontausaurus_meat_raw");
		vrontausaurusCooked = initItem(reg, new Item(defProps().food(food(10, 0.9).meat().build())), "vrontausaurus_meat_cooked");
		
		yagaHogRaw = initItem(reg, new Item(defProps().food(food(2, 0.3).meat().effect(new EffectInstance(Effects.HUNGER, 600, 0), 0.6F).build())), "yaga_hog_meat_raw");
		yagaHogCooked = initItem(reg, new Item(defProps().food(food(6, 0.7).meat().build())), "yaga_hog_meat_cooked");

		duskAsh = initItem(reg, new Item(def), "dusk_ash");
		duskLurkerFur = initItem(reg, new Item(def), "dusk_lurker_fur");
		duskLurkerFurTuft = initItem(reg, new Item(def), "dusk_lurker_fur_tuft");
		duskLurkerRaw = initItem(reg, new Item(defProps().food(food(2, 0.5).meat().build())), "dusk_lurker_meat_raw");
		duskLurkerCooked = initItem(reg, new Item(defProps().food(food(6, 1.2).meat().build())), "dusk_lurker_meat_cooked");
		
		cicapteraRaw = initItem(reg, new Item(defProps().food(food(3, 0.4).meat().build())), "cicaptera_meat_raw");
		cicapteraCooked = initItem(reg, new Item(defProps().food(food(8, 0.8).meat().build())), "cicaptera_meat_cooked");
		cicapteraHuskAzure = initItem(reg, new Item(def), "cicaptera_husk_azure");
		cicapteraHuskVerdant = initItem(reg, new Item(def), "cicaptera_husk_verdant");
		cicapteraHuskCrimson = initItem(reg, new Item(def), "cicaptera_husk_crimson");
		cicapteraHuskSandy = initItem(reg, new Item(def), "cicaptera_husk_sandy");
		cicapteraHuskWintry = initItem(reg, new Item(def), "cicaptera_husk_wintry");
		cicapteraHuskLovely = initItem(reg, new Item(def), "cicaptera_husk_lovely");
		aphroditeEssence = initItem(reg, new Item(def), "aphrodite_essence");
		
		plumperBlubber = initItem(reg, new ItemFuel(200, def), "plumper_blubber");
		plumperRaw = initItem(reg, new Item(defProps().food(food(2, 0.6).meat().build())), "plumper_meat_raw");
		plumperCooked = initItem(reg, new Item(defProps().food(food(8, 1.0).meat().build())), "plumper_meat_cooked");
		
		krillRaw = initItem(reg, new Item(defProps().food(food(2, 0.1).meat().build())), "krill_meat_raw");
		krillCooked = initItem(reg, new Item(defProps().food(food(4, 0.6).meat().build())), "krill_meat_cooked");
		
		//Create the entities and thus their eggs
		ModEntities.initEntities();
		ModEntities.eggs.forEach(reg::register);
		//Offer the list to the garbage collector now that it has outlive its usefulness
		ModEntities.eggs = null;
	}
    
    //Gosh that's a lot of helper functions
    public static Item.Properties defProps() {
    	return new Item.Properties().group(ItemGroupMysticalWildlife.INSTANCE);
    }
    private static Food.Builder food(int hunger, double saturation) {
    	return (new Food.Builder()).hunger(hunger).saturation((float)saturation);
    }
	
	public static Item initItem(IForgeRegistry<Item> reg, Item item, String name) {
		item.setRegistryName(MysticalWildlife.MODID, name);
		reg.register(item);
		return item;
	}
}
