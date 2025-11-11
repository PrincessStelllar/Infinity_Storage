package com.benbenlaw.infinitystorage.item;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public class InfinityContent {

    private final ItemStack infinityStack;
    private final FluidStack fluidStack;

    public InfinityContent(ItemStack infinityStack, FluidStack fluidStack) {
        this.infinityStack = infinityStack == null ? ItemStack.EMPTY : infinityStack;
        this.fluidStack = fluidStack == null ? FluidStack.EMPTY : fluidStack;
    }

    public static InfinityContent of (ItemStack stack) {
        return new InfinityContent(stack, FluidStack.EMPTY);
    }

    public static InfinityContent of (FluidStack stack) {
        return new InfinityContent(ItemStack.EMPTY, stack);
    }

    public boolean isItem() {
        return infinityStack != null && !infinityStack.isEmpty();
    }

    public boolean isFluid() {
        return fluidStack != null && !fluidStack.isEmpty();
    }


    public ItemStack getInfinityStack() {
        return infinityStack;
    }

    public FluidStack getInfinityFluidStack() {
        return fluidStack;
    }
}
