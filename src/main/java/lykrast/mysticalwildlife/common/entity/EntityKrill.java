package lykrast.mysticalwildlife.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import lykrast.mysticalwildlife.common.entity.ai.EntityAIForage;
import lykrast.mysticalwildlife.common.init.ModSounds;
import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityKrill extends EntityAnimal {
    public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("krill");
    public static final ResourceLocation LOOT_FORAGE = ResourceUtil.getSpecialLootTable("krill_forage");
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS);
    
    public int forageTimer;
    private EntityAIForage forageAI;
	
	public EntityKrill(World worldIn) {
		super(worldIn);
        this.setSize(0.75F, 0.45F);
	}

    @Override
    protected void initEntityAI() {
    	forageAI = new EntityAIForage(this);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, forageAI);
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26D);
    }
    
    @Override
    public void eatGrassBonus() {
    	//Forage something
    	LootTable loottable = world.getLootTableManager().getLootTableFromLocation(LOOT_FORAGE);
        LootContext.Builder builder = (new LootContext.Builder((WorldServer)world)).withLootedEntity(this);
        
		for (ItemStack itemstack : loottable.generateLootForPools(rand, builder.build())) entityDropItem(itemstack, 0.0F);
    }

    @Override
    protected void updateAITasks() {
    	forageTimer = forageAI.getForageTime();
        super.updateAITasks();
    }
    
    @Override
    public void onLivingUpdate()
    {
        if (this.world.isRemote) forageTimer = Math.max(0, forageTimer - 1);
        super.onLivingUpdate();
    }
    
    @SideOnly(Side.CLIENT)
	@Override
    public void handleStatusUpdate(byte id) {
        if (id == 10) forageTimer = 40;
        else super.handleStatusUpdate(id);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return new EntityKrill(world);
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
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
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
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }
}
