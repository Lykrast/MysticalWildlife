package lykrast.mysticalwildlife.common.entity;

import javax.annotation.Nullable;

import lykrast.mysticalwildlife.common.init.ModEntities;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPlumper extends AnimalEntity {
    public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("plumper");
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.COD, Items.COOKED_COD, Items.SALMON, Items.COOKED_SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH);
	
	public EntityPlumper(EntityType<? extends EntityPlumper> type, World worldIn) {
		super(ModEntities.plumper, worldIn);
	}

    @Override
	protected void registerGoals() {
    	goalSelector.addGoal(0, new SwimGoal(this));
    	goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, TEMPTATION_ITEMS));
        goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }
    
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.16D);
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return ModEntities.plumper.create(world);
	}
    
	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.plumperIdle;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		return ModSounds.plumperHurt;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.plumperDeath;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
	}

	@Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}
}
