package lykrast.mysticalwildlife.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFuel extends Item {
	private int burnTime;
	
	public ItemFuel(int burnTime, Item.Properties properties) {
		super(properties);
		this.burnTime = burnTime;
	}
	
	@Override
	public int getBurnTime(ItemStack fuel) {
    	return burnTime;
    }

}
