package xyz.przemyk.spookyarms.registry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.przemyk.spookyarms.guns.PumpkinBazookaItem;
import xyz.przemyk.spookyarms.guns.PumpkinPistolItem;
import xyz.przemyk.spookyarms.SpookyArms;

import java.util.List;

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

    public static final RegistryObject<PumpkinPistolItem> PUMPKIN_PISTOL = ITEMS.register("pumpkin_pistol", () -> new PumpkinPistolItem((byte) 7, 30));
    public static final RegistryObject<PumpkinPistolItem> PUMPKIN_PISTOL_MK2 = ITEMS.register("pumpkin_pistol_mk2", () -> new PumpkinPistolItem((byte) 8, 25));
    public static final RegistryObject<PumpkinPistolItem> PUMPKIN_PISTOL_MK3 = ITEMS.register("pumpkin_pistol_mk3", () -> new PumpkinPistolItem((byte) 8, 15));

    public static final RegistryObject<PumpkinBazookaItem> PUMPKIN_BAZOOKA = ITEMS.register("pumpkin_bazooka", PumpkinBazookaItem::new);

    public static final RegistryObject<BlockItem> EXPLOSIVE_PUMPKIN = ITEMS.register("explosive_pumpkin", () -> new BlockItem(BlockRegistry.EXPLOSIVE_PUMPKIN.get(), new Item.Properties().group(MOD_ITEM_GROUP)));

    public static final RegistryObject<Item> PUMPKIN_BULLET = ITEMS.register("pumpkin_bullet", () -> new Item(new Item.Properties().group(MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> INFINITE_AMMO_POUCH = ITEMS.register("infinite_ammo_pouch", () -> new Item(new Item.Properties().group(MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> PUMPKIN_CIRCUIT = ITEMS.register("pumpkin_circuit", () -> new Item(new Item.Properties().group(MOD_ITEM_GROUP)));

    private static final TranslationTextComponent TOOLTIP = new TranslationTextComponent(SpookyArms.MODID + ".pumpkin_circuit.info");
    public static final RegistryObject<Item> UPGRADE_KIT = ITEMS.register("upgrade_kit", () -> new Item(new Item.Properties().group(MOD_ITEM_GROUP).rarity(Rarity.UNCOMMON)) {
        @Override
        public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
            tooltip.add(TOOLTIP);
        }
    });

}
