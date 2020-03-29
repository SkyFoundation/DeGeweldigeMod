package com.pixelsky.cheese.world.gen;

import com.pixelsky.cheese.Reference;
import com.pixelsky.cheese.init.CheeseBlocks;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenCheeseBossHouse extends WorldGenerator {

	private static final BlockStateMatcher IS_GRASS = BlockStateMatcher.forBlock(CheeseBlocks.CHEESE_GRASS);
	private static final BlockStateMatcher IS_HIGH_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS);
	private final ResourceLocation HOUSE = new ResourceLocation(Reference.MODID + ":cheese_boss_house");

	public boolean generate(World worldIn, Random rand, BlockPos position) {
		while (worldIn.isAirBlock(position) && position.getY() > 2) {
			position = position.down();
		}
		if (!IS_GRASS.apply(worldIn.getBlockState(position)) && !IS_HIGH_GRASS.apply(worldIn.getBlockState(position))) {
			return false;
		}
		if(IS_HIGH_GRASS.apply(worldIn.getBlockState(position))) {
			position = position.down();
		}
        Random random = worldIn.getChunkFromChunkCoords(position.getX(), position.getZ()).getRandomWithSeed(worldIn.getSeed());
        Rotation[] arotation = Rotation.values();
        Rotation rotation = arotation[random.nextInt(arotation.length)];
        ChunkPos chunkpos = new ChunkPos(position);
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(chunkpos.getXStart() - 50, 0, chunkpos.getZStart() - 50, chunkpos.getXEnd() + 50, 256, chunkpos.getZEnd() + 50);
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(structureboundingbox).setRandom(random);
        MinecraftServer minecraftserver = worldIn.getMinecraftServer();
		TemplateManager templatemanager = worldIn.getSaveHandler().getStructureTemplateManager();
        Template template = templatemanager.getTemplate(minecraftserver, HOUSE);
        template.addBlocksToWorld(worldIn, position, placementsettings, 3);
		return true;
	}

}
