package xyz.przemyk.spookyarms.guns;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import xyz.przemyk.spookyarms.registry.EntityRegistry;

public class PumpkinRocketEntity extends PumpkinBulletEntity {
    public static final DataParameter<Boolean> EXPLOSIVE = EntityDataManager.createKey(PumpkinRocketEntity.class, DataSerializers.BOOLEAN);

    public PumpkinRocketEntity(EntityType<? extends ProjectileEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PumpkinRocketEntity(World world, boolean explosive) {
        super(EntityRegistry.PUMPKIN_ROCKET.get(), world);
        dataManager.set(DAMAGE, (byte) 10);
        dataManager.set(EXPLOSIVE, explosive);
    }

    @Override
    public void tick() {
        super.tick();
        if (!hasNoGravity()) {
            setMotion(getMotion().add(0.0D, -0.04D, 0.0D));
        }
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(EXPLOSIVE, false);
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("explosive", dataManager.get(EXPLOSIVE));
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        dataManager.set(EXPLOSIVE, compound.getBoolean("explosive"));
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.PUMPKIN);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.PUMPKIN.getDefaultState()), getPosX(), getPosY(), getPosZ(), 0.0, 0.0, 0.0);
        if (!world.isRemote()) {
            if (dataManager.get(EXPLOSIVE)) {
                this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), 3.0F, Explosion.Mode.BREAK);
            } else {
                this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), 1.0F, Explosion.Mode.BREAK);
            }
        }
    }
}
