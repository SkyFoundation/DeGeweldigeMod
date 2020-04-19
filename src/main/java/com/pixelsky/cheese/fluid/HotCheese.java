package com.pixelsky.cheese.fluid;

import com.pixelsky.cheese.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class HotCheese extends Fluid {

    protected static int mapColor = 0xFFFFFFFF;
    protected static float overlayAlpha = 0.2F;
   // protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
   // protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
    protected static Material material = Material.WATER;


    public HotCheese(){
        super("cheese_hot", new ResourceLocation(Reference.MODID, "fluids/cheese_still"),  new ResourceLocation(Reference.MODID, "fluids/cheese_flow") ,new ResourceLocation(Reference.MODID, "fluids/cheese_overlay"));

        setRarity(EnumRarity.COMMON);
        setLuminosity(0);
        setDensity(2000);
        setViscosity(1000);
        setTemperature(120);
        setFillSound(SoundEvents.ITEM_BUCKET_FILL);
        FluidRegistry.addBucketForFluid(this);
    }

    @Override
    public int getColor() {
        return mapColor;
    }


    public Material getMaterial()
    {
        return material;
    }

    @Override
    public boolean doesVaporize(FluidStack fluidStack)
    {
        if (block == null)
            return false;
        return block.getDefaultState().getMaterial() == getMaterial();
    }
}
