package xyz.przemyk.spookyarms.registry;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.przemyk.spookyarms.explosive.ExplosivePumpkinBlock;
import xyz.przemyk.spookyarms.SpookyArms;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpookyArms.MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ExplosivePumpkinBlock> EXPLOSIVE_PUMPKIN = BLOCKS.register("explosive_pumpkin", ExplosivePumpkinBlock::new);
}
