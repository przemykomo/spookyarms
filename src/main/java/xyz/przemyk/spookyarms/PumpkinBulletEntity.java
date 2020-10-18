package xyz.przemyk.spookyarms;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class PumpkinBulletEntity extends ProjectileEntity implements IRendersAsItem {

    public PumpkinBulletEntity(EntityType<? extends ProjectileEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PumpkinBulletEntity(World world) {
        super(EntityRegistry.PUMPKIN_BULLET.get(), world);
    }

    @Override
    protected void registerData() {

    }

    @Override
    public void tick() {
        Entity entity = this.func_234616_v_();
        if (this.world.isRemote || (entity == null || !entity.removed) && this.world.isBlockLoaded(this.func_233580_cy_())) {
            super.tick();

            RayTraceResult raytraceresult = ProjectileHelper.func_234618_a_(this, this::func_230298_a_);
            if (raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onImpact(raytraceresult);
            }

            this.doBlockCollisions();
            Vector3d vector3d = this.getMotion();
            double d0 = this.getPosX() + vector3d.x;
            double d1 = this.getPosY() + vector3d.y;
            double d2 = this.getPosZ() + vector3d.z;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            if (this.isInWater()) {
                for(int i = 0; i < 4; ++i) {
                    this.world.addParticle(ParticleTypes.BUBBLE, d0 - vector3d.x * 0.25D, d1 - vector3d.y * 0.25D, d2 - vector3d.z * 0.25D, vector3d.x, vector3d.y, vector3d.z);
                }
            }
            this.setPosition(d0, d1, d2);
        } else {
            this.remove();
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult entityRayTraceResult) {
        Entity entity = entityRayTraceResult.getEntity();
        Entity shooter = func_234616_v_();
        DamageSource damagesource;
        if (shooter == null) {
            damagesource = new IndirectEntityDamageSource("pistol", this, this).setProjectile();
        } else {
            damagesource = new IndirectEntityDamageSource("pistol", this, shooter).setProjectile();
            if (shooter instanceof LivingEntity) {
                ((LivingEntity)shooter).setLastAttackedEntity(entity);
            }
        }
        entity.attackEntityFrom(damagesource, 3.0f);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemsRegistry.PUMPKIN_BULLET.get());
    }
}
