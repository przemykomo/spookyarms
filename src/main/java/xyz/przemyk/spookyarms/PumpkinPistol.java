package xyz.przemyk.spookyarms;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PumpkinPistol extends Item {

    public PumpkinPistol() {
        super(new Properties().maxStackSize(1).group(ItemsRegistry.MOD_ITEM_GROUP));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {


        PumpkinBulletEntity pumpkinBulletEntity = new PumpkinBulletEntity(worldIn);
        pumpkinBulletEntity.setPosition(playerIn.getPosX(), playerIn.getPosY() + 1, playerIn.getPosZ());
        pumpkinBulletEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);

        worldIn.addEntity(pumpkinBulletEntity);

        playerIn.getCooldownTracker().setCooldown(this, 26);

        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}
