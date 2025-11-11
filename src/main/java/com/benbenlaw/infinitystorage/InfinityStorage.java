package com.benbenlaw.infinitystorage;

import com.benbenlaw.infinitystorage.block.ISBlockEntities;
import com.benbenlaw.infinitystorage.block.ISBlocks;
import com.benbenlaw.infinitystorage.item.ISCreativeTab;
import com.benbenlaw.infinitystorage.item.ISItems;
import com.benbenlaw.infinitystorage.screen.ISMenuTypes;
import com.benbenlaw.infinitystorage.screen.InfinityStorageDriveScreen;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(InfinityStorage.MOD_ID)
public class InfinityStorage {
    public static final String MOD_ID = "infinitystorage";

    public InfinityStorage(IEventBus eventBus) {

        ISBlocks.BLOCKS.register(eventBus);
        ISBlockEntities.BLOCK_ENTITIES.register(eventBus);
        ISItems.ITEMS.register(eventBus);
        ISCreativeTab.CREATIVE_MODE_TABS.register(eventBus);

        ISMenuTypes.MENUS.register(eventBus);

        eventBus.addListener(this::registerCapabilities);

    }

    @EventBusSubscriber(modid = InfinityStorage.MOD_ID)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ISMenuTypes.INFINITY_STORAGE_DRIVE_MENU.get(), InfinityStorageDriveScreen::new);
        }
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        ISBlockEntities.registerCapabilities(event);
    }}