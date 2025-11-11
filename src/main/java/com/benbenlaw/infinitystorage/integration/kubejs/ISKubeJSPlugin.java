package com.benbenlaw.infinitystorage.integration.kubejs;

import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import net.minecraft.core.registries.Registries;

public class ISKubeJSPlugin implements KubeJSPlugin {


    @Override
    public void registerBuilderTypes(BuilderTypeRegistry registry) {
        registry.of(Registries.ITEM, r -> r.add("infinity_drive", InfinityDriveBuilder.class, InfinityDriveBuilder::new));
        KubeJSPlugin.super.registerBuilderTypes(registry);
    }
}
