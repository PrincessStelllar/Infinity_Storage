package com.benbenlaw.infinitydisks.block;

import com.benbenlaw.infinitydisks.InfinityDisks;
import com.benbenlaw.infinitydisks.block.refined.TestBlock;
import com.benbenlaw.infinitydisks.block.refined.TestBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class InfinityDiskBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(InfinityDisks.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, InfinityDisks.MOD_ID);

    public static final DeferredHolder<Block, TestBlock> TEST_BLOCK = BLOCKS.registerBlock("test", TestBlock::new);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TestBlockEntity>> TEST_BLOCK_ENTITY = BLOCK_ENTITIES.register("test",
            () -> BlockEntityType.Builder.of(TestBlockEntity::new, TEST_BLOCK.get()).build(null)
    );
}
