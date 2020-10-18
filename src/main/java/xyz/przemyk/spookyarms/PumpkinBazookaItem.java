package xyz.przemyk.spookyarms;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PumpkinBazookaItem extends Item {

    public PumpkinBazookaItem() {
        super(new Properties().maxStackSize(1).group(ItemsRegistry.MOD_ITEM_GROUP));
}

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        PumpkinRocketEntity pumpkinRocketEntity = new PumpkinRocketEntity(worldIn);
        pumpkinRocketEntity.setPosition(playerIn.getPosX(), playerIn.getPosY() + 1, playerIn.getPosZ());
        pumpkinRocketEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);

        worldIn.addEntity(pumpkinRocketEntity);

        playerIn.getCooldownTracker().setCooldown(this, 45);

        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}
