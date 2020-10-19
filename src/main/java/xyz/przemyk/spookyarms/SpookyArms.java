package xyz.przemyk.spookyarms;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.przemyk.spookyarms.explosive.ExplosivePumpkinEntity;
import xyz.przemyk.spookyarms.explosive.ExplosivePumpkinRenderer;
import xyz.przemyk.spookyarms.guns.PumpkinBulletEntity;
import xyz.przemyk.spookyarms.registry.BlockRegistry;
import xyz.przemyk.spookyarms.registry.EntityRegistry;
import xyz.przemyk.spookyarms.registry.ItemsRegistry;

@Mod(SpookyArms.MODID)
public class SpookyArms {
    public static final String MODID = "spookyarms";

    public SpookyArms() {
        BlockRegistry.init();
        ItemsRegistry.init();
        EntityRegistry.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.PUMPKIN_BULLET.get(), new PumpkinBulletRenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.PUMPKIN_ROCKET.get(), new PumpkinBulletRenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.EXPLOSIVE_PUMPKIN.get(),new ExplosivePumpkinRenderFactory());
    }

    private static class PumpkinBulletRenderFactory implements IRenderFactory<PumpkinBulletEntity> {
        @Override
        public EntityRenderer<? super PumpkinBulletEntity> createRenderFor(EntityRendererManager manager) {
            return new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer());
        }
    }

    private static class ExplosivePumpkinRenderFactory implements IRenderFactory<ExplosivePumpkinEntity> {
        @Override
        public EntityRenderer<? super ExplosivePumpkinEntity> createRenderFor(EntityRendererManager manager) {
            return new ExplosivePumpkinRenderer(manager);
        }
    }
}
