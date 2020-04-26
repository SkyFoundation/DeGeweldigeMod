package com.pixelsky.cheese.world.populator;

import com.pixelsky.cheese.world.gen.CheeseTreeGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Random;

public class TreeDecorator extends BlockDecorator {

    private CheeseTreeGen cheeseTreeGen = new CheeseTreeGen();

    @Override
    public void decorate(World world, int chunkX, int chunkZ, Random random, Chunk chunk) {
        if (random.nextInt(10) == 0) {
            int dx = random.nextInt(16);
            int dz = random.nextInt(16);
            int x = chunkX*16 + dx;
            int z = chunkZ*16 + dz;
            cheeseTreeGen.generate(world,random,new BlockPos(x,chunk.getHeightValue(dx,dz) + 1,z));
        }
    }
}
