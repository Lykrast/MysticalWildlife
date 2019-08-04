package lykrast.mysticalwildlife.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;

public class ItemAphroditeEssence extends Item {
	public ItemAphroditeEssence(Item.Properties builder) {
		super(builder);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
		if (entity.world.isRemote) return false;
		if (entity instanceof AnimalEntity) {
			AnimalEntity target = (AnimalEntity) entity;
			if (target.getGrowingAge() >= 0) {
				//Reset breeding cooldown
				if (target.getGrowingAge() > 0) target.setGrowingAge(0);
				
				target.setInLove(playerIn);
				if (!playerIn.abilities.isCreativeMode) stack.shrink(1);
				playerIn.addStat(Stats.ITEM_USED.get(this));
			}
			return true;
		}
		return false;
	}

}
