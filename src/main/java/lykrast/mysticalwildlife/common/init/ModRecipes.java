package lykrast.mysticalwildlife.common.init;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber
public class ModRecipes {
	public static final String meatRaw = "listAllmeatraw",
			meatCooked = "listAllmeatcooked",
			leather = "leather";
	
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
	{
		initSmelting();
	}
	
	public static void initOreDict()
	{
		//Meat
		OreDictionary.registerOre(meatRaw, ModItems.vrontausaurusRaw);
		OreDictionary.registerOre(meatCooked, ModItems.vrontausaurusCooked);
		OreDictionary.registerOre(meatRaw, ModItems.yagaHogRaw);
		OreDictionary.registerOre(meatCooked, ModItems.yagaHogCooked);
		OreDictionary.registerOre(meatRaw, ModItems.duskLurkerRaw);
		OreDictionary.registerOre(meatCooked, ModItems.duskLurkerCooked);
		
		//Leather
		OreDictionary.registerOre(leather, ModItems.vrontausaurusFur);
		OreDictionary.registerOre(leather, ModItems.duskLurkerFur);
		
		//Other
		OreDictionary.registerOre("dustAsh", ModItems.duskAsh);
	}
	
	public static void initSmelting()
	{
		GameRegistry.addSmelting(ModItems.vrontausaurusRaw, new ItemStack(ModItems.vrontausaurusCooked), 0.35F);
		GameRegistry.addSmelting(ModItems.yagaHogRaw, new ItemStack(ModItems.yagaHogCooked), 0.35F);
		GameRegistry.addSmelting(ModItems.duskLurkerRaw, new ItemStack(ModItems.duskLurkerCooked), 0.35F);
	}
}
