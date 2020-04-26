package com.pixelsky.cheese.world.populator;

import com.pixelsky.cheese.init.CheeseBlocks;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import java.util.Random;

public class GrassPopulator extends BlockPopulator {

    public int getDH(double d){
        if (d>0.5)
            return 5;
        return 4;
    }

    private int getHeight(int x,int z,ChunkPrimer chunkPrimer){
        int ret = 255;
        while(chunkPrimer.getBlockState(x,ret,z).getBlock().equals(Blocks.AIR))
            ret--;
        return ret + 1;
    }


    @Override
    public void populate(World world,int chunkX,int chunkZ, Random random, ChunkPrimer chunkPrimer) {
        NoiseGeneratorPerlin grass = new NoiseGeneratorPerlin(random,10);
        for (int i = 0;i<16;i++)
            for (int j = 0;j<16;j++) {
                int x = i;
                int z = j;
                int height = this.getHeight(x,z,chunkPrimer);
                int dh = getDH(grass.getValue(chunkX*16+x,chunkZ*16+z));
                for (int k = 0;k<dh-1;k++)
                    chunkPrimer.setBlockState(x,height+k,z, CheeseBlocks.CHEESE_DIRT.getDefaultState());
                chunkPrimer.setBlockState(x,height +dh-1,z,CheeseBlocks.CHEESE_GRASS.getDefaultState());
            }
    }
}
