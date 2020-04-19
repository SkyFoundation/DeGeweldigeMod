package com.pixelsky.cheese.world.populator;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import java.util.Random;

public abstract class BlockPopulator {


    public abstract void populate(World world,int chunkX,int chunkZ, Random random, ChunkPrimer chunkPrimer);
}
