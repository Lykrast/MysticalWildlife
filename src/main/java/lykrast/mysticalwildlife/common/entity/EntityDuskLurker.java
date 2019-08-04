package lykrast.mysticalwildlife.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.mysticalwildlife.common.init.ModEntities;
import lykrast.mysticalwildlife.common.init.ModItems;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityDuskLurker extends EntityFurzard {
    public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("dusk_lurker");
    public static final ResourceLocation LOOT_BRUSH = ResourceUtil.getSpecialLootTable("brush_dusk_lurker");
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.RABBIT, Items.COOKED_RABBIT, ModItems.cicapteraRaw, ModItems.cicapteraCooked);
	
	public EntityDuskLurker(EntityType<? extends EntityDuskLurker> type, World worldIn) {
		super(ModEntities.duskLurker, worldIn);
	}
	
    @Override
	protected void registerGoals() {
    	goalSelector.addGoal(0, new SwimGoal(this));
    	goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        goalSelector.addGoal(4, new AITempt(this, 1.2D, false, TEMPTATION_ITEMS));
        goalSelector.addGoal(5, new AvoidEntityGoal<>(this, PlayerEntity.class, 10.0F, 1.0D, 1.25D));
        goalSelector.addGoal(6, new FollowParentGoal(this, 1.1D));
        goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 24.0F, 1.0F));
        goalSelector.addGoal(9, new LookRandomlyGoal(this));
    }
    
    @Override
	protected void registerAttributes() {
        super.registerAttributes();
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
    
    private void spawnAshParticles() {
    	for (int i = 0; i < 10; ++i)
    	{
    		world.addParticle(ParticleTypes.LARGE_SMOKE, 
    				posX + (rand.nextDouble() - 0.5D) * (double)getWidth(), 
    				posY + rand.nextDouble() * (double)getHeight(), 
    				posZ + (rand.nextDouble() - 0.5D) * (double)getWidth(), 
    				0.0D, 0.0D, 0.0D);
    	}
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
    	if (world.isRemote && amount > 0) spawnAshParticles();
    	
    	return super.attackEntityFrom(source, amount);
    }

	@Override
	public List<ItemStack> onBrushed(PlayerEntity player, ItemStack item, BlockPos pos) {		
		//Spawns the particles
        this.world.setEntityState(this, (byte)10);
        playSound(ModSounds.brushing, 1.0F, 1.0F);
        
        if (rand.nextInt(5) == 0) setBrushTimer(3600 + rand.nextInt(2401));
        
    	LootTable loottable = world.getServer().getLootTableManager().getLootTableFromLocation(LOOT_BRUSH);
        LootContext.Builder builder = (new LootContext.Builder((ServerWorld)world)).withRandom(rand).withParameter(LootParameters.THIS_ENTITY, this).withParameter(LootParameters.POSITION, new BlockPos(this));
        
		return loottable.generate(builder.build(LootParameterSets.ENTITY));
	}

    /**
     * Handler for {@link World#setEntityState}
     */
    @Override
    @OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) spawnAshParticles();
		else super.handleStatusUpdate(id);
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return ModEntities.duskLurker.create(world);
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
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
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
	public boolean isBreedingItem(ItemStack stack)
    {
    	if (world.isDaytime()) return false;
        return TEMPTATION_ITEMS.test(stack);
    }
    
    private static class AITempt extends TemptGoal {
		public AITempt(CreatureEntity temptedEntityIn, double speedIn, boolean scaredByPlayerMovementIn, Ingredient temptItemIn) {
			super(temptedEntityIn, speedIn, scaredByPlayerMovementIn, temptItemIn);
		}

		@Override
	    public boolean shouldExecute() {
			if (creature.world.isDaytime()) return false;
			return super.shouldExecute();
		}
    }
}
