package xyz.przemyk.spookyarms;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ItemsRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpookyArms.MODID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final ItemGroup MOD_ITEM_GROUP = new ItemGroup("spookyarms") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.PUMPKIN);
        }
    };

    public static final RegistryObject<PumpkinPistol> PUMPKIN_PISTOL = ITEMS.register("pumpkin_pistol", PumpkinPistol::new);
    public static final RegistryObject<Item> PUMPKIN_BULLET = ITEMS.register("pumpkin_bullet", () -> new Item(new Item.Properties().group(MOD_ITEM_GROUP)));
}
