package com.pixelsky.cheese.world.populator;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public abstract class BlockDecorator {
    public abstract void decorate(World world, int chunkX, int chunkZ, Random random, Chunk chunk);
}
