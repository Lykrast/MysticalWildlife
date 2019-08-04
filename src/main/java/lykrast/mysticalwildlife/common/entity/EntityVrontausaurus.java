package lykrast.mysticalwildlife.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.mysticalwildlife.common.init.ModEntities;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;

public class EntityVrontausaurus extends EntityFurzard {
    public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("vrontausaurus");
    public static final ResourceLocation LOOT_BRUSH = ResourceUtil.getSpecialLootTable("brush_vrontausaurus");
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.BEEF, Items.COOKED_BEEF, Items.MUTTON, Items.COOKED_MUTTON);
	
	public EntityVrontausaurus(EntityType<? extends EntityVrontausaurus> type, World worldIn) {
		super(ModEntities.vrontausaurus, worldIn);
	}

    @Override
	protected void registerGoals() {
        goalSelector.addGoal(0, new SwimGoal(this));
        goalSelector.addGoal(1, new AIPanic(this, 1.2D));
        goalSelector.addGoal(2, new AIAttackMeleeShortrange(this, 1.2D, false));
        goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        goalSelector.addGoal(4, new AITempt(this, 1.2D, false, TEMPTATION_ITEMS));
        goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(8, new LookRandomlyGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }
    
    @Override
	protected void registerAttributes()
    {
        super.registerAttributes();
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.24D);
    }
    
    @Override
	protected void updateAITasks()
    {
    	if (world.getDifficulty() == Difficulty.PEACEFUL) {
        	if (this.getAttackTarget() != null) this.setAttackTarget(null);
        	if (this.getRevengeTarget() != null) this.setRevengeTarget(null);
    	}
    	
    	super.updateAITasks();
    }

	@Override
	public boolean isBrushable(PlayerEntity player, ItemStack item, BlockPos pos) {
		return !isChild() && isBrushable() && getAttackTarget() == null;
	}

	@Override
	public List<ItemStack> onBrushed(PlayerEntity player, ItemStack item, BlockPos pos) {
		if (rand.nextInt(4) == 0) player.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 2.0F);

        playSound(ModSounds.brushing, 1.0F, 1.0F);
        playSound(ModSounds.spark, 1.0F, 1.0F);
        
        if (rand.nextInt(5) == 0) setBrushTimer(3600 + rand.nextInt(2401));
        
    	LootTable loottable = world.getServer().getLootTableManager().getLootTableFromLocation(LOOT_BRUSH);
        LootContext.Builder builder = (new LootContext.Builder((ServerWorld)world)).withRandom(rand).withParameter(LootParameters.THIS_ENTITY, this).withParameter(LootParameters.POSITION, new BlockPos(this));
        
		return loottable.generate(builder.build(LootParameterSets.ENTITY));
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return ModEntities.vrontausaurus.create(world);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.lizardIdle;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		return ModSounds.lizardHurt;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.lizardDeath;
	}

    @Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

	@Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }
	
    @Override
	public boolean isBreedingItem(ItemStack stack) {
		if (!world.isRaining()) return false;
		return TEMPTATION_ITEMS.test(stack);
	}
    
    static class AIPanic extends PanicGoal {
        public AIPanic(CreatureEntity creature, double speed) {
            super(creature, speed);
        }
        
        @Override
		public boolean shouldExecute() {
            return !creature.isChild() && !creature.isBurning() ? false : super.shouldExecute();
        }
    }
    
    private static class AIAttackMeleeShortrange extends MeleeAttackGoal {
		public AIAttackMeleeShortrange(CreatureEntity creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}

		@Override
        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(9.0F + attackTarget.getWidth());
        }
    	
    }
    
    private static class AITempt extends TemptGoal {
		public AITempt(CreatureEntity temptedEntityIn, double speedIn, boolean scaredByPlayerMovementIn, Ingredient temptItemIn) {
			super(temptedEntityIn, speedIn, scaredByPlayerMovementIn, temptItemIn);
		}

		@Override
	    public boolean shouldExecute() {
			if (!creature.world.isRaining()) return false;
			return super.shouldExecute();
		}
    }

}
