package lykrast.mysticalwildlife.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import lykrast.mysticalwildlife.common.init.ModEntities;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.AreaEffectCloudEntity;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;

public class EntityYagaHog extends AnimalEntity implements IBrushable {
    public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("yaga_hog");
    public static final ResourceLocation LOOT_BRUSH = ResourceUtil.getSpecialLootTable("brush_yaga_hog");
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.BREAD);
    
    private static final DataParameter<Boolean> DIRTY = EntityDataManager.<Boolean>createKey(EntityYagaHog.class, DataSerializers.BOOLEAN);
    private int dirtTime;
	
	public EntityYagaHog(EntityType<? extends EntityYagaHog> type, World worldIn) {
		super(ModEntities.yagaHog, worldIn);
	}

	@Override
    protected void registerData() {
        super.registerData();
        getDataManager().register(DIRTY, Boolean.TRUE);
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
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
	
	public boolean isDirty() {
		return world.isRemote ? ((Boolean)getDataManager().get(DIRTY)).booleanValue() : dirtTime <= 0;
	}
	
    public void setDirtTimer(int timer) {
        getDataManager().set(DIRTY, Boolean.valueOf(timer <= 0));
        this.dirtTime = timer;
    }
    
    @Override
    public void livingTick() {
        super.livingTick();
        
        if (!world.isRemote && !isDirty()) {
        	setDirtTimer(Math.max(0, dirtTime - (isWet() ? 3 : 1)));
        }
    }

    @Override
    public boolean isPotionApplicable(EffectInstance potioneffectIn) {
    	if (potioneffectIn.getPotion() == Effects.POISON) return false;
    	else return super.isPotionApplicable(potioneffectIn);
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
    	if (!world.isRemote && amount > 0) {
    		int amplifier = (int)Math.floor(((amount / 10.0F) * 3.0F));
    		amplifier = MathHelper.clamp(amplifier, 0, 3);
    		
    		spawnLingeringCloud(amplifier);
    	}
    	
    	return super.attackEntityFrom(source, amount);
    }

    private void spawnLingeringCloud(int amplifier) {
    	//Adapted from the Creeper
		AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(world, posX, posY, posZ);
		areaeffectcloudentity.setRadius(2.5F);
		areaeffectcloudentity.setRadiusOnUse(-0.5F);
		areaeffectcloudentity.setWaitTime(10);
		areaeffectcloudentity.setDuration(areaeffectcloudentity.getDuration() / 2);
		areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());

		areaeffectcloudentity.addEffect(new EffectInstance(Effects.POISON, 200, amplifier));

		world.addEntity(areaeffectcloudentity);
    }

	@Override
	public boolean isBrushable(PlayerEntity player, ItemStack item, BlockPos pos) {
		return !isChild() && isDirty();
	}

	@Override
	public List<ItemStack> onBrushed(PlayerEntity player, ItemStack item, BlockPos pos) {		
        playSound(SoundEvents.ENTITY_SLIME_JUMP, 1.0F, 1.0F);
        
        if (rand.nextInt(3) == 0) setDirtTimer(3600 + rand.nextInt(2401));
        
    	LootTable loottable = world.getServer().getLootTableManager().getLootTableFromLocation(LOOT_BRUSH);
        LootContext.Builder builder = (new LootContext.Builder((ServerWorld)world)).withRandom(rand).withParameter(LootParameters.THIS_ENTITY, this).withParameter(LootParameters.POSITION, new BlockPos(this)).withParameter(LootParameters.DAMAGE_SOURCE, DamageSource.GENERIC);
        
		return loottable.generate(builder.build(LootParameterSets.ENTITY));
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return ModEntities.yagaHog.create(world);
	}

	@Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return SoundEvents.ENTITY_PIG_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

	@Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
    }

	@Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("DirtTimer", dirtTime);
    }

	@Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        setDirtTimer(compound.getInt("DirtTimer"));
    }

	@Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}
}
