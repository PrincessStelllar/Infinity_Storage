package com.benbenlaw.infinitystorage.item;

import com.benbenlaw.infinitystorage.InfinityStorage;
import com.benbenlaw.infinitystorage.block.ISBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ISCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InfinityStorage.MOD_ID);

    public static final Supplier<CreativeModeTab> CASTING_TAB = CREATIVE_MODE_TABS.register("casting", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ISBlocks.INFINITY_STORAGE_DRIVE.get().asItem().getDefaultInstance())
            .title(Component.translatable("itemGroup.infinitystorage"))

            .displayItems(
                    (parameters, output) -> ISBlocks.BLOCKS.getEntries().forEach(item -> output.accept(item.get()))
            )
            .displayItems(
                    (parameters, output) -> ISItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()))
            )

            .build());

}
