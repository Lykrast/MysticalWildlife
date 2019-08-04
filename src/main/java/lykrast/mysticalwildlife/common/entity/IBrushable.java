package lykrast.mysticalwildlife.common.entity;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

/**
 * Based on the IShearable interface, but for the Brush.
 */
public interface IBrushable {
    /**
     * Checks if the object is currently brushable
     * Example: Vrontausaurus return false when they are attacking something
     *
     * @param player The EntityPlayer that is attempting to brush, may be null
     * @param item The ItemStack that is being used, may be empty.
     * @param world The current world.
     * @param pos Block's position in world.
     * @return If this is brushable, and onBrushed should be called.
     */
    boolean isBrushable(@Nullable PlayerEntity player, @Nonnull ItemStack item, BlockPos pos);

    /**
     * Performs the brush function on this object.
     * This is called only on the server.
     * The object should perform all actions related to being brushed,
     * except for dropping of the items, and removal of the block.
     * As those are handled by ItemBrush itself.
     *
     * Returns a list of items that resulted from the brushing process.
     *
     * For entities, they should trust their internal location information
     * over the values passed into this function.
     *
     * @param player The EntityPlayer that is brushing, may be null
     * @param item The ItemStack that is being used, may be empty.
     * @param world The current world.
     * @param pos If this is a block, the block's position in world.
     * @return A List containing all items from this shearing. May be empty.
     */
    @Nonnull
    List<ItemStack> onBrushed(@Nullable PlayerEntity player, @Nonnull ItemStack item, BlockPos pos);
}
