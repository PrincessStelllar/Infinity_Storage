package com.benbenlaw.infinitydisks.item;

import net.minecraft.world.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class InfinityDiskManager {

    // Keep track of all your infinite ItemStacks
    private static final Set<ItemStack> INFINITE_STACKS = new HashSet<>();

    public static void registerInfinite(ItemStack stack) {
        INFINITE_STACKS.add(stack.copy());
    }

    public static boolean isInfinite(ItemStack stack) {
        for (ItemStack infiniteStack : INFINITE_STACKS) {
            if (ItemStack.isSameItemSameComponents(stack, infiniteStack)) {
                return true;
            }
        }
        return false;
    }
}