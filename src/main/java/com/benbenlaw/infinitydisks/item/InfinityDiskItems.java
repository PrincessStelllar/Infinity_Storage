package com.benbenlaw.infinitydisks.item;

import com.benbenlaw.infinitydisks.InfinityDisks;
import com.refinedmods.refinedstorage.common.storage.ItemStorageVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InfinityDiskItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(InfinityDisks.MOD_ID);

    public static final DeferredItem<Item> COBBLESTONE_INFINITY_DISK = ITEMS.register("cobblestone_infinity_disk",
            () -> new InfinityDiskItem(new ItemStack(Blocks.COBBLESTONE)));

}
