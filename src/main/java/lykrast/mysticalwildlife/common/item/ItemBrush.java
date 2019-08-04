package lykrast.mysticalwildlife.common.item;

import java.util.List;
import java.util.Random;

import lykrast.mysticalwildlife.common.entity.IBrushable;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.core.MysticalWildlife;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MysticalWildlife.MODID)
public class ItemBrush extends Item {
    public ItemBrush(Item.Properties properties) {
    	super(properties);
    }

	@SubscribeEvent
	public static void onInteract(EntityInteract evt) {
		//Prevents tamed animals from sitting
		if (evt.getItemStack().getItem() instanceof ItemBrush && evt.getTarget() instanceof LivingEntity) {
			ItemStack item = evt.getItemStack();
			if (item.getItem().itemInteractionForEntity(item, evt.getEntityPlayer(), (LivingEntity) evt.getTarget(), evt.getHand())) {
				evt.setCanceled(true);
				evt.setCancellationResult(ActionResultType.SUCCESS);
			}
		}
	}

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (entity instanceof IBrushable) {
        	IBrushable target = (IBrushable)entity;
            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
            if (target.isBrushable(player, itemstack, pos)) {
                if (!entity.world.isRemote) {
                	List<ItemStack> drops = target.onBrushed(player, itemstack, pos);

                    if (!drops.isEmpty()) {
                        Random rand = new Random();
                        for(ItemStack stack : drops) {
                            ItemEntity ent = entity.entityDropItem(stack, 1.0F);
                            ent.setMotion(
                            		(rand.nextFloat() - rand.nextFloat()) * 0.1F, 
                            		rand.nextFloat() * 0.05F, 
                            		(rand.nextFloat() - rand.nextFloat()) * 0.1F);
                        }
                    }
                    itemstack.damageItem(1, player, e -> e.sendBreakAnimation(hand));
                }
                else player.swingArm(hand);
            }
            return true;
        }
        else if (entity instanceof TameableEntity) {
        	TameableEntity tameable = (TameableEntity)entity;
        	if (tameable.isTamed()) {
        		if (!tameable.world.isRemote) {
        			tameable.playSound(ModSounds.brushing, 1.0F, 1.0F);
        			//tameable.playTameEffect(true);
        			//Play tamed particles
        			tameable.world.setEntityState(tameable, (byte)7);
        		}
                else player.swingArm(hand);
        		return true;
        	}
        }
        return false;
    }
	
	@Override
    public int getItemEnchantability() {
        return 1;
    }
}
