package com.lou.sao.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WeaponAttributes.class)
public class WeaponAttributes{
	@Inject(at = @At("HEAD"), method = "Condition")
	public void Condition(CallbackInfo info){ 
		// This code is injected into the start of WeaponAttributes.Condition
		KEY_IS_PRESSED,
	}
}
	
