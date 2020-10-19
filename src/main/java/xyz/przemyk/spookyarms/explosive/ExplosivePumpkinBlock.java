package xyz.przemyk.spookyarms.explosive;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ExplosivePumpkinBlock extends TNTBlock {

    public ExplosivePumpkinBlock() {
        super(Properties.create(Material.TNT).zeroHardnessAndResistance().sound(SoundType.PLANT));
        this.setDefaultState(this.stateContainer.getBaseState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.NORTH).with(UNSTABLE, false));
    }

    @Override
    public void catchFire(BlockState state, World worldIn, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        if (!worldIn.isRemote) {
            ExplosivePumpkinEntity explosivePumpkinEntity = new ExplosivePumpkinEntity(worldIn, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            worldIn.addEntity(explosivePumpkinEntity);
            worldIn.playSound(null, explosivePumpkinEntity.getPosX(), explosivePumpkinEntity.getPosY(), explosivePumpkinEntity.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        if (!worldIn.isRemote) {
            ExplosivePumpkinEntity explosivePumpkinEntity = new ExplosivePumpkinEntity(worldIn, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D);
            explosivePumpkinEntity.setFuse((short)(worldIn.rand.nextInt(explosivePumpkinEntity.getFuse() / 4) + explosivePumpkinEntity.getFuse() / 8));
            worldIn.addEntity(explosivePumpkinEntity);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HorizontalBlock.HORIZONTAL_FACING);
    }
}
