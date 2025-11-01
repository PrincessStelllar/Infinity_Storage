package com.benbenlaw.infinitydisks.block.refined;

import com.benbenlaw.infinitydisks.block.Tickable;
import com.refinedmods.refinedstorage.common.support.AbstractBaseBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class TestBlock extends AbstractBaseBlock implements EntityBlock {
    public TestBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new TestBlockEntity(p_153215_, p_153216_);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return new BlockEntityTicker<T>() {
            @Override
            public void tick(Level level, BlockPos pos, BlockState p_155255_, T entity) {
                if (entity instanceof Tickable tickingBlockEntity) {
                    tickingBlockEntity.tick();
                }
            }
        };
    }
}
