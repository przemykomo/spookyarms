package xyz.przemyk.spookyarms;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class PumpkinPistolItem extends ShootableItem {

    public final byte DAMAGE;
    public final int COOLDOWN;

    public PumpkinPistolItem(byte damage, int cooldown) {
        super(new Properties().maxStackSize(1).group(ItemsRegistry.MOD_ITEM_GROUP));
        DAMAGE = damage;
        COOLDOWN = cooldown;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        ItemStack ammo = playerIn.findAmmo(heldItem);

        if (!ammo.isEmpty() || playerIn.abilities.isCreativeMode) {
            PumpkinBulletEntity pumpkinBulletEntity = new PumpkinBulletEntity(worldIn, DAMAGE);
            pumpkinBulletEntity.setPosition(playerIn.getPosX(), playerIn.getPosY() + 1, playerIn.getPosZ());
            pumpkinBulletEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            worldIn.addEntity(pumpkinBulletEntity);

            playerIn.getCooldownTracker().setCooldown(this, COOLDOWN);

            if (!ammo.isEmpty()) {
                ammo.shrink(1);
            }
            return ActionResult.resultSuccess(heldItem);
        }

        return ActionResult.resultPass(heldItem);
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return itemStack -> itemStack.getItem() == ItemsRegistry.PUMPKIN_BULLET.get();
    }

    @Override
    public int func_230305_d_() {
        return 0;
    }
}
