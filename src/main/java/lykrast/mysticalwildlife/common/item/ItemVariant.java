package lykrast.mysticalwildlife.common.item;

import java.util.stream.IntStream;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;

public class ItemVariant extends Item implements IItemCustomModel {
	public final String[] VARIANTS;
	
	public ItemVariant(String... variants) {
		//Very fancy way of making everything lowercase
		VARIANTS = new String[variants.length];
		IntStream.range(0, variants.length).forEach(i -> VARIANTS[i] = variants[i].toLowerCase());
		
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		int i = stack.getMetadata();
		if (i < 0 || i >= VARIANTS.length) i = 0;
		return super.getTranslationKey() + "." + VARIANTS[i];
	}

	@Override
	public void initModel() {
		for (int i = 0; i < VARIANTS.length; i++)
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(this.getRegistryName() + "_" + VARIANTS[i], "inventory"));
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (this.isInCreativeTab(tab))
		{
			for (int i = 0; i < VARIANTS.length; ++i) items.add(new ItemStack(this, 1, i));
		}
	}

}
