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

public class CheeseStore extends Block {
	public CheeseStore() {
		super(Material.PISTON);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		playerIn.openGui(DeGeweldigeMod.DGMInstance, CheeseGuiHandler.STOREID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
