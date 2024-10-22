package com.lou.sao.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.bettercombat.api.WeaponAttributes;
import net.bettercombat.logic.PlayerAttackHelper;

@Mixin(PlayerAttackHelper.class)
public class BetterCombatPlayerAttackHelperMixin {
    @Inject(at = @At("HEAD"), method = "evaluateCondition", cancellable = true)
    private void evaluateCondition(WeaponAttributes.Condition condition, CallbackInfoReturnable<Boolean> cir) {
        
    }
}
