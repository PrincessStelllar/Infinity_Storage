package com.benbenlaw.infinitystorage.item;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.Map;
import java.util.Optional;

public class InfinityDrive extends Item {

    private final InfinityContent content;

    public InfinityDrive(Properties properties, InfinityContent content) {
        super(properties);
        this.content = content;
    }

    public InfinityContent getContent() {
        return content;
    }

    public ItemStack getInfinityStack() {
        return content.getInfinityStack();
    }

    public FluidStack getInfinityFluidStack() {
        return content.getInfinityFluidStack();
    }
}
