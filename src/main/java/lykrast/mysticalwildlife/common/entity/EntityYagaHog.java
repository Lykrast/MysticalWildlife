package lykrast.mysticalwildlife.common.entity;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import lykrast.mysticalwildlife.common.util.ResourceUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityYagaHog extends EntityAnimal {
    public static final ResourceLocation LOOT = ResourceUtil.getEntityLootTable("yaga_hog");
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.BREAD);
	
	public EntityYagaHog(World worldIn)
	{
		super(worldIn);
        this.setSize(0.9F, 0.9F);
	}

    protected void entityInit()
    {
        super.entityInit();
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public boolean isPotionApplicable(PotionEffect potioneffectIn)
    {
    	Potion potion = potioneffectIn.getPotion();
    	if (potion == MobEffects.POISON) return false;

    	return super.isPotionApplicable(potioneffectIn);
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
    	if (!world.isRemote && amount > 0)
    	{
    		int amplifier = (int)Math.floor(((amount / 10.0F) * 3.0F));
    		amplifier = MathHelper.clamp(amplifier, 0, 3);
    		
    		spawnLingeringCloud(amplifier);
    	}
    	
    	return super.attackEntityFrom(source, amount);
    }

    private void spawnLingeringCloud(int amplifier)
    {
    	EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.world, this.posX, this.posY, this.posZ);
    	entityareaeffectcloud.setRadius(2.5F);
    	entityareaeffectcloud.setRadiusOnUse(-0.5F);
    	entityareaeffectcloud.setWaitTime(10);
    	entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
    	entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
    	
    	entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.POISON, 200, amplifier));

    	this.world.spawnEntity(entityareaeffectcloud);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return new EntityYagaHog(world);
	}
    
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return SoundEvents.ENTITY_PIG_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
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
    public boolean isBreedingItem(ItemStack stack)
    {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }
}
