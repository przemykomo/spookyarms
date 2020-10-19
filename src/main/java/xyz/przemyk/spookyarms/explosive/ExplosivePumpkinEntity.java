package xyz.przemyk.spookyarms.explosive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.przemyk.spookyarms.registry.EntityRegistry;

public class ExplosivePumpkinEntity extends TNTEntity {

    public ExplosivePumpkinEntity(EntityType<? extends ExplosivePumpkinEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ExplosivePumpkinEntity(World worldIn, double x, double y, double z) {
        super(EntityRegistry.EXPLOSIVE_PUMPKIN.get(), worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        this.setMotion(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
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
