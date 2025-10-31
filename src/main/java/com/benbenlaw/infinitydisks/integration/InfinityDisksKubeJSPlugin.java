package com.benbenlaw.infinitydisks.integration;

import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import net.minecraft.core.registries.Registries;

public class InfinityDisksKubeJSPlugin implements KubeJSPlugin {

    @Override
    public void registerBuilderTypes(BuilderTypeRegistry registry) {

        registry.of(Registries.ITEM,
                r -> r.add("infinity_disk", InfinityDiskBuilder.class, InfinityDiskBuilder::new));

        KubeJSPlugin.super.registerBuilderTypes(registry);
    }
}
