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
	
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
	{
		initSmelting();
	}
	
	public static void initOreDict()
	{
		//Meat
		OreDictionary.registerOre("listAllmeatraw", ModItems.vrontausurusRaw);
		OreDictionary.registerOre("listAllmeatcooked", ModItems.vrontausurusCooked);
	}
	
	public static void initSmelting()
	{
		GameRegistry.addSmelting(ModItems.vrontausurusRaw, new ItemStack(ModItems.vrontausurusCooked), 0.35F);
	}
}
