package xyz.przemyk.spookyarms;

import net.minecraft.item.*;
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

    public static final RegistryObject<PumpkinPistolItem> PUMPKIN_PISTOL = ITEMS.register("pumpkin_pistol", () -> new PumpkinPistolItem((byte) 3, 30));
    public static final RegistryObject<PumpkinPistolItem> PUMPKIN_PISTOL_MK2 = ITEMS.register("pumpkin_pistol_mk2", () -> new PumpkinPistolItem((byte) 4, 25));
    public static final RegistryObject<PumpkinPistolItem> PUMPKIN_PISTOL_MK3 = ITEMS.register("pumpkin_pistol_mk3", () -> new PumpkinPistolItem((byte) 5, 20));

    public static final RegistryObject<PumpkinBazookaItem> PUMPKIN_BAZOOKA = ITEMS.register("pumpkin_bazooka", PumpkinBazookaItem::new);

    public static final RegistryObject<Item> PUMPKIN_BULLET = ITEMS.register("pumpkin_bullet", () -> new Item(new Item.Properties().group(MOD_ITEM_GROUP)));
    public static final RegistryObject<BlockItem> EXPLOSIVE_PUMPKIN = ITEMS.register("explosive_pumpkin", () -> new BlockItem(BlockRegistry.EXPLOSIVE_PUMPKIN.get(), new Item.Properties().group(MOD_ITEM_GROUP)));
}
