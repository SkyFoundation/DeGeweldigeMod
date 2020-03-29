package com.pixelsky.cheese.blocks;

import com.pixelsky.cheese.DeGeweldigeMod;
import com.pixelsky.cheese.handler.CheeseGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CheeseCookieBlock extends Block {
	public CheeseCookieBlock() {
		super(Material.ROCK);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float hitX, float hitY, float hitZ) {
		playerIn.openGui(DeGeweldigeMod.DGMInstance, CheeseGuiHandler.COOKIEGUIID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, hitX, hitY, hitZ);
	}
}
