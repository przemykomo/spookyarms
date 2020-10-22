package xyz.przemyk.spookyarms;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.przemyk.spookyarms.guns.PumpkinBazookaItem;
import xyz.przemyk.spookyarms.guns.PumpkinPistolItem;
import xyz.przemyk.spookyarms.registry.ItemsRegistry;

@Mod.EventBusSubscriber
public class ModEvents {

    @SubscribeEvent
    public static void lootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(LootTables.CHESTS_SIMPLE_DUNGEON)) {
            event.getTable().addPool(
                    LootPool.builder().name(LootTables.CHESTS_SIMPLE_DUNGEON.toString())
                            .rolls(new RandomValueRange(0, 1))
                            .bonusRolls(0, 1)
                            .addEntry(ItemLootEntry.builder(ItemsRegistry.UPGRADE_KIT.get()))
                            .build()
            );
        }
    }

    @SubscribeEvent
    public static void playerRender(RenderPlayerEvent.Pre event) {
        PlayerEntity playerEntity = event.getPlayer();

        Item item = playerEntity.getHeldItem(Hand.MAIN_HAND).getItem();
        if (item instanceof  PumpkinPistolItem || item instanceof PumpkinBazookaItem) {
            PlayerModel<AbstractClientPlayerEntity> playerModel = event.getRenderer().getEntityModel();
            if (playerEntity.getPrimaryHand() == HandSide.LEFT) {
                playerModel.leftArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
            } else {
                playerModel.rightArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
            }
        } else {
            Item item2 = playerEntity.getHeldItem(Hand.OFF_HAND).getItem();
            if (item2 instanceof PumpkinPistolItem || item2 instanceof PumpkinBazookaItem) {
                PlayerModel<AbstractClientPlayerEntity> playerModel = event.getRenderer().getEntityModel();
                if (playerEntity.getPrimaryHand() == HandSide.RIGHT) {
                    playerModel.leftArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
                } else {
                    playerModel.rightArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
                }
            }
        }
    }
}
