package com.benbenlaw.infinitystorage.block;

import com.benbenlaw.infinitystorage.InfinityStorage;
import com.benbenlaw.infinitystorage.block.custom.InfinityStorageDriveBlock;
import com.benbenlaw.infinitystorage.item.ISItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ISBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(InfinityStorage.MOD_ID);

    public static final DeferredBlock<Block> INFINITY_STORAGE_DRIVE = registerBlock("infinity_storage_drive",
            () -> new InfinityStorageDriveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ISItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

}
