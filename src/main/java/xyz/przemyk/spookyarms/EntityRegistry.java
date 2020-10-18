package xyz.przemyk.spookyarms;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, SpookyArms.MODID);

    public static void init() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<EntityType<PumpkinBulletEntity>> PUMPKIN_BULLET = ENTITIES.register("pumpkin_bullet", () -> EntityType.Builder.<PumpkinBulletEntity>create(PumpkinBulletEntity::new, EntityClassification.MISC).size(0.3125F, 0.3125F).func_233606_a_(4).func_233608_b_(10).build("pumpkin_bullet"));
}
