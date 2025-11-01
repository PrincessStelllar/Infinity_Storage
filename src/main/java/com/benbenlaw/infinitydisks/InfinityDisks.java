package com.benbenlaw.infinitydisks;

import com.benbenlaw.infinitydisks.block.InfinityDiskBlocks;
import com.benbenlaw.infinitydisks.item.InfinityDiskItems;
import com.refinedmods.refinedstorage.neoforge.api.RefinedStorageNeoForgeApi;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(InfinityDisks.MOD_ID)
public class InfinityDisks {
    public static final String MOD_ID = "infinitydisks";



    public InfinityDisks(IEventBus eventBus) {
        InfinityDiskItems.ITEMS.register(eventBus);
        InfinityDiskBlocks.BLOCK_ENTITIES.register(eventBus);
        InfinityDiskBlocks.BLOCKS.register(eventBus);

        eventBus.addListener(this::onRegister);
    }

    public void onRegister(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                RefinedStorageNeoForgeApi.INSTANCE.getNetworkNodeContainerProviderCapability(),
                InfinityDiskBlocks.TEST_BLOCK_ENTITY.get(),
                (be, side) -> be.getContainerProvider()
        );
    }
}