package com.pixelsky.cheese.entities.cheesecow;

import com.pixelsky.cheese.entities.LootTables;
import com.pixelsky.cheese.init.CheeseItems;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityCheeseCow extends EntityCow {
	public EntityCheeseCow(World worldIn) {
		super(worldIn);
		this.setSize(0.9F, 1.4F);
	}

	public static void registerFixesCheeseCow(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, EntityCheeseCow.class);
	}

	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 2.0D));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, CheeseItems.CHEESE, false));
		this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}
	
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack) {
        return stack != null && stack.getItem() == CheeseItems.CHEESE;
    }
/*
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_COW_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_COW_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_COW_DEATH;
	}

	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
	}

	protected float getSoundVolume() {
		return 0.4F;
	}
*/
	@Nullable
	protected ResourceLocation getLootTable() {
		return LootTables.ENTITIES_CHEESE_COW;
	}

	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (stack != null && stack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode && !this.isChild()) {
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
			stack.shrink(1);;
			if (stack.isEmpty()) {
				player.setHeldItem(hand, new ItemStack(CheeseItems.CHEESE_BUCKET));
			} else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET))) {
                player.dropItem(new ItemStack(CheeseItems.CHEESE_BUCKET), false);
            }
			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}

	public EntityCheeseCow createChild(EntityAgeable ageable) {
		return new EntityCheeseCow(this.world);
	}

	public float getEyeHeight() {
		return this.isChild() ? this.height : 1.3F;
	}
}