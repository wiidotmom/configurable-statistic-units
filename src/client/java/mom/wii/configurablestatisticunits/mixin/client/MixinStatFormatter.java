package mom.wii.configurablestatisticunits.mixin.client;

import mom.wii.configurablestatisticunits.config.Config;
import net.minecraft.stat.StatFormatter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static mom.wii.configurablestatisticunits.config.Config.TimeUnit;
import static mom.wii.configurablestatisticunits.config.Config.DistanceUnit;
import static mom.wii.configurablestatisticunits.ConfigurableStatisticUnits.CONFIG;

@Mixin(StatFormatter.class)
public interface MixinStatFormatter {
    @Inject(method = "method_16819(I)Ljava/lang/String;", at = @At("RETURN"), cancellable = true)
    private static void configurablestatisticunits$TIME(int ticks, CallbackInfoReturnable<String> cir) {
        if (!CONFIG.enabled) return;
        TimeUnit timeUnit = CONFIG.timeUnit;
        if (timeUnit.equals(TimeUnit.VANILLA_BEHAVIOR)) return;
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

    @Inject(method = "method_16816(I)Ljava/lang/String;", at = @At("RETURN"), cancellable = true)
    private static void configurablestaticunits$DISTANCE(int cm, CallbackInfoReturnable<String> cir) {
        if (!CONFIG.enabled) return;
        DistanceUnit distanceUnit = CONFIG.distanceUnit;
        if (distanceUnit.equals(DistanceUnit.VANILLA_BEHAVIOR)) return;

        double d = (double)cm / (double)100.0F;
        double e = d / (double)1000.0F;
        if (distanceUnit.equals(DistanceUnit.KILOMETERS)) {
            cir.setReturnValue(StatFormatter.DECIMAL_FORMAT.format(e) + " km");
        } else if (distanceUnit.equals(DistanceUnit.METERS)) {
            cir.setReturnValue(StatFormatter.DECIMAL_FORMAT.format(d) + " m");
        } else {
            cir.setReturnValue(cm + " cm");
        }
    }
}
