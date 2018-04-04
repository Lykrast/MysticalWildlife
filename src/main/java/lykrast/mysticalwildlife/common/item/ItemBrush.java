package lykrast.mysticalwildlife.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBrush extends Item {
    public ItemBrush(int damage) {
        this.setMaxStackSize(1);
        this.setMaxDamage(damage);
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, EntityLivingBase entity, net.minecraft.util.EnumHand hand)
    {
        if (entity.world.isRemote)
        {
            return false;
        }
        //TODO: brush
//        if (entity instanceof net.minecraftforge.common.IShearable)
//        {
//            net.minecraftforge.common.IShearable target = (net.minecraftforge.common.IShearable)entity;
//            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
//            if (target.isShearable(itemstack, entity.world, pos))
//            {
//                java.util.List<ItemStack> drops = target.onSheared(itemstack, entity.world, pos,
//                        net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.FORTUNE, itemstack));
//
//                java.util.Random rand = new java.util.Random();
//                for(ItemStack stack : drops)
//                {
//                    net.minecraft.entity.item.EntityItem ent = entity.entityDropItem(stack, 1.0F);
//                    ent.motionY += rand.nextFloat() * 0.05F;
//                    ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
//                    ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
//                }
//                itemstack.damageItem(1, entity);
//            }
//            return true;
//        }
        return false;
    }
}
