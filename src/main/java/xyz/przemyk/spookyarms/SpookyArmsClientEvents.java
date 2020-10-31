package xyz.przemyk.spookyarms;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.przemyk.spookyarms.guns.PumpkinBazookaItem;
import xyz.przemyk.spookyarms.guns.PumpkinPistolItem;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class SpookyArmsClientEvents {

    @SubscribeEvent
    public static void playerRender(RenderPlayerEvent.Pre event) {
        PlayerEntity playerEntity = event.getPlayer();

        Item item = playerEntity.getHeldItem(Hand.MAIN_HAND).getItem();
        if (item instanceof PumpkinPistolItem || item instanceof PumpkinBazookaItem) {
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
