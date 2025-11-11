package com.benbenlaw.infinitystorage.block.entity.handlers;

import com.benbenlaw.infinitystorage.item.InfinityDrive;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class InfinityDriveItemHandler extends ItemStackHandler {

    private final InfinityDrive drive;

    public InfinityDriveItemHandler(InfinityDrive drive) {
        super(1); // each drive has 1 slot
        this.drive = drive;
    }

    @Override
    public int getSlotLimit(int slot) {
        return Integer.MAX_VALUE; // infinite
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        if (drive.getContent().isItem()) {
            ItemStack copy = drive.getContent().getInfinityStack().copy();
            copy.setCount(Integer.MAX_VALUE);
            return copy;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (drive.getContent().isItem()) {
            return new ItemStack(drive.getContent().getInfinityStack().getItem(), amount);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (drive.getContent().isItem() && stack.is(drive.getContent().getInfinityStack().getItem())) {
            return ItemStack.EMPTY;
        }
        return stack;
    }
}
