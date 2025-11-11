package com.benbenlaw.infinitystorage.screen;

import com.benbenlaw.infinitystorage.InfinityStorage;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ISMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, InfinityStorage.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<InfinityStorageDriveMenu>> INFINITY_STORAGE_DRIVE_MENU;

    static {
        INFINITY_STORAGE_DRIVE_MENU = MENUS.register("infinity_storage_drive_menu", () ->
                IMenuTypeExtension.create(InfinityStorageDriveMenu::new));
    }
}
