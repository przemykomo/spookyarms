package xyz.przemyk.spookyarms;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ExplosivePumpkinEntity extends TNTEntity {

    public final BlockState BLOCK_STATE;

    public ExplosivePumpkinEntity(EntityType<? extends ExplosivePumpkinEntity> type, World worldIn) {
        super(type, worldIn);
        BLOCK_STATE = BlockRegistry.EXPLOSIVE_PUMPKIN.get().getDefaultState();
    }

    public ExplosivePumpkinEntity(World worldIn, double x, double y, double z, BlockState blockState) {
        super(EntityRegistry.EXPLOSIVE_PUMPKIN.get(), worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        this.setMotion(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.BLOCK_STATE = blockState;
    }

    @Override
    protected void explode() {
        this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), 3.0F, Explosion.Mode.BREAK);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
