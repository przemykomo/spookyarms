package xyz.przemyk.spookyarms;

import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.RandomValueRange;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.przemyk.spookyarms.registry.ItemsRegistry;

@Mod.EventBusSubscriber
public class ModEvents {

    @SubscribeEvent
    public static void lootTableLoad(LootTableLoadEvent event) {
        event.getTable().addPool(
                LootPool.builder().name(LootTables.CHESTS_SIMPLE_DUNGEON.toString())
                        .rolls(new RandomValueRange(0, 1))
                        .bonusRolls(0, 1)
                        .addEntry(ItemLootEntry.builder(ItemsRegistry.UPGRADE_KIT.get()))
                        .build()
        );
    }
}
