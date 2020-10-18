package xyz.przemyk.spookyarms;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

//TODO: better name?
//TODO: explosive variant
public class PumpkinRocketEntity extends PumpkinBulletEntity {

    public PumpkinRocketEntity(EntityType<? extends ProjectileEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PumpkinRocketEntity(World world) {
        super(EntityRegistry.PUMPKIN_ROCKET.get(), world);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.PUMPKIN);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult entityRayTraceResult) {
        Entity entity = entityRayTraceResult.getEntity();
        Entity shooter = func_234616_v_();
        DamageSource damagesource;
        if (shooter == null) {
            damagesource = new IndirectEntityDamageSource("bazooka", this, this).setProjectile();
        } else {
            damagesource = new IndirectEntityDamageSource("bazooka", this, shooter).setProjectile();
            if (shooter instanceof LivingEntity) {
                ((LivingEntity)shooter).setLastAttackedEntity(entity);
            }
        }
        entity.attackEntityFrom(damagesource, 10.0f);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.PUMPKIN.getDefaultState()), getPosX(), getPosY(), getPosZ(), 0.0, 0.0, 0.0);
    }
}
