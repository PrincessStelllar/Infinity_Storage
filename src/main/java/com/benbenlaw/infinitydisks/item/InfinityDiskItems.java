package com.benbenlaw.infinitydisks.item;

import com.benbenlaw.infinitydisks.InfinityDisks;
import com.benbenlaw.infinitydisks.block.InfinityDiskBlocks;
import com.refinedmods.refinedstorage.common.storage.ItemStorageVariant;
import com.refinedmods.refinedstorage.common.support.NetworkNodeBlockItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InfinityDiskItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(InfinityDisks.MOD_ID);

    public static final DeferredItem<Item> COBBLESTONE_INFINITY_DISK = ITEMS.register("cobblestone_infinity_disk",
            () -> new InfinityDiskItem(new ItemStack(Blocks.COBBLESTONE)));

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.registerItem("test", properties -> new NetworkNodeBlockItem(InfinityDiskBlocks.TEST_BLOCK.get(), Component.literal("Okay")));

}
