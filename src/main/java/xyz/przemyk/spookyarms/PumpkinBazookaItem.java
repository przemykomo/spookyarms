package xyz.przemyk.spookyarms;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class PumpkinBazookaItem extends ShootableItem {

    public PumpkinBazookaItem() {
        super(new Properties().maxStackSize(1).group(ItemsRegistry.MOD_ITEM_GROUP));
}

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        ItemStack ammo = playerIn.findAmmo(heldItem);

        if (!ammo.isEmpty() || playerIn.abilities.isCreativeMode) {
            PumpkinRocketEntity pumpkinRocketEntity = new PumpkinRocketEntity(worldIn, ammo.getItem() == ItemsRegistry.EXPLOSIVE_PUMPKIN.get());
            pumpkinRocketEntity.setPosition(playerIn.getPosX(), playerIn.getPosY() + 1, playerIn.getPosZ());
            pumpkinRocketEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);

            worldIn.addEntity(pumpkinRocketEntity);

            playerIn.getCooldownTracker().setCooldown(this, 45);

            if (!ammo.isEmpty()) {
                ammo.shrink(1);
            }
            return ActionResult.resultSuccess(heldItem);
        }

        return ActionResult.resultPass(heldItem);
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return itemStack -> itemStack.getItem() == Items.PUMPKIN || itemStack.getItem() == ItemsRegistry.EXPLOSIVE_PUMPKIN.get() || itemStack.getItem() == Items.CARVED_PUMPKIN;
    }

    @Override
    public int func_230305_d_() {
        return 0;
    }
}
