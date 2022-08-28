package daniking.vinery.mixin;

import daniking.vinery.item.WinemakerArmorItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin {
	//	@Inject(method = "useOnFertilizable", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V", shift = At.Shift.BEFORE), cancellable = true)
	//	private static void useOnFertilizable(ItemStack stack, World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
	//
	//	}
	
	@Inject(method = "useOnBlock", at = @At("RETURN"))
	public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
		if (cir.getReturnValue() == ActionResult.CONSUME) {
			PlayerEntity player = context.getPlayer();
			ItemStack helmet = player.getEquippedStack(EquipmentSlot.HEAD);
			ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);
			ItemStack leggings = player.getEquippedStack(EquipmentSlot.LEGS);
			ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
			if (helmet != null && helmet.getItem() instanceof WinemakerArmorItem &&
					chestplate != null && chestplate.getItem() instanceof WinemakerArmorItem &&
					leggings != null && leggings.getItem() instanceof WinemakerArmorItem &&
					boots != null && boots.getItem() instanceof WinemakerArmorItem) {
				context.getStack().increment(1);
			}
		}
	}
}