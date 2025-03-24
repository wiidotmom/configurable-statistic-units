package mom.wii.configurablestatisticunits.mixin.client;

import mom.wii.configurablestatisticunits.config.Config;
import net.minecraft.stat.StatFormatter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import mom.wii.configurablestatisticunits.config.Config.TimeUnit;

@Mixin(StatFormatter.class)
public interface MixinStatFormatter {
    @Inject(method = "method_16819(I)Ljava/lang/String;", at = @At("RETURN"), cancellable = true)
    private static void configurablestatisticunits$TIME(int ticks, CallbackInfoReturnable<String> cir) {
        TimeUnit timeUnit = Config.timeUnit;
        if (timeUnit.equals(TimeUnit.NO_CHANGE)) return;
        double d = (double)ticks / (double)20.0F;
        double e = d / (double)60.0F;
        double f = e / (double)60.0F;
        double g = f / (double)24.0F;
        double h = g / (double)365.0F;

        if (timeUnit.equals(TimeUnit.YEARS)) {
            cir.setReturnValue(StatFormatter.DECIMAL_FORMAT.format(h) + " y");
        } else if (timeUnit.equals(TimeUnit.DAYS)) {
            cir.setReturnValue(StatFormatter.DECIMAL_FORMAT.format(g) + " d");
        } else if (timeUnit.equals(TimeUnit.HOURS)) {
            cir.setReturnValue(StatFormatter.DECIMAL_FORMAT.format(f) + " h");
        } else {
            cir.setReturnValue(e > (double)0.5F ? StatFormatter.DECIMAL_FORMAT.format(e) + " min" : d + " s");
        }
    }
}
