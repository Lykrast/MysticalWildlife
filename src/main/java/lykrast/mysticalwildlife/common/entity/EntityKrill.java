package lykrast.mysticalwildlife.common.entity;

import javax.annotation.Nullable;

import lykrast.mysticalwildlife.common.entity.ai.EntityAIForage;
import lykrast.mysticalwildlife.common.init.ModEntities;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.ConfigHandler;
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
import net.minecraft.nbt.CompoundNBT;
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

public class EntityKrill extends AnimalEntity {
    public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("krill");
    public static final ResourceLocation LOOT_FORAGE = ResourceUtil.getSpecialLootTable("krill_forage");
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS);
    
    public int forageProgress;
    public int forageCooldown;
    private EntityAIForage forageAI;
	
	public EntityKrill(EntityType<? extends EntityKrill> type, World worldIn) {
		super(ModEntities.krill, worldIn);
		resetForageCooldown();
	}

    @Override
    protected void registerGoals() {
    	forageAI = new EntityAIForage(this);
        goalSelector.addGoal(0, new SwimGoal(this));
        goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        goalSelector.addGoal(3, new TemptGoal(this, 1.2D, false, TEMPTATION_ITEMS));
        goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        goalSelector.addGoal(5, forageAI);
        goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
        getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
        getAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(8.0D);
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26D);
    }
	
	private void resetForageCooldown() {
		int base = ConfigHandler.krillForageTime.get();
		forageCooldown = rand.nextInt(base) + base;
	}
    
    @Override
    public void eatGrassBonus() {
    	//Forage something
    	LootTable loottable = world.getServer().getLootTableManager().getLootTableFromLocation(LOOT_FORAGE);
        LootContext.Builder builder = (new LootContext.Builder((ServerWorld)world)).withRandom(rand).withParameter(LootParameters.THIS_ENTITY, this).withParameter(LootParameters.POSITION, new BlockPos(this));
        
        loottable.generate(builder.build(LootParameterSets.GIFT), this::entityDropItem);
        resetForageCooldown();
    }

    @Override
    protected void updateAITasks() {
    	forageProgress = forageAI.getForageProgress();
        super.updateAITasks();
    }
    
    @Override
    public void livingTick() {
        if (world.isRemote && forageProgress > 0) forageProgress--;
        if (!world.isRemote && !isChild() && forageCooldown > 0) forageCooldown--;
        
        super.livingTick();
    }
	

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("ForageCooldown")) forageCooldown = compound.getInt("ForageCooldown");
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("ForageCooldown", forageCooldown);
	}
    
    @OnlyIn(Dist.CLIENT)
	@Override
    public void handleStatusUpdate(byte id) {
        if (id == 10) forageProgress = 40;
        else super.handleStatusUpdate(id);
    }

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return ModEntities.krill.create(world);
	}

	@Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.krillIdle;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return ModSounds.krillHurt;
    }

	@Override
    protected SoundEvent getDeathSound() {
        return ModSounds.krillDeath;
    }

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
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
