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

@Mod(SpookyArms.MODID)
public class SpookyArms {
    public static final String MODID = "spookyarms";

    public SpookyArms() {
        ItemsRegistry.init();
        EntityRegistry.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.PUMPKIN_BULLET.get(), new PumpkinBulletRenderFactory());
    }

    private static class PumpkinBulletRenderFactory implements IRenderFactory<PumpkinBulletEntity> {
        @Override
        public EntityRenderer<? super PumpkinBulletEntity> createRenderFor(EntityRendererManager manager) {
            return new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer());
        }
    }
}
