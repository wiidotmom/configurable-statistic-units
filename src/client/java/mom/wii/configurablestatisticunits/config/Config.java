package mom.wii.configurablestatisticunits.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.NameableEnum;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static mom.wii.configurablestatisticunits.ConfigurableStatisticUnitsClient.MOD_ID;

public class Config {
    public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
            .id(Identifier.of(MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("configurable_statistic_units.json"))
                    .setJson5(false)
                    .build())
            .build();

    public enum TimeUnit implements NameableEnum {
        NO_CHANGE,
        MINUTES_SECONDS,
        HOURS,
        DAYS,
        YEARS;

        @Override
        public Text getDisplayName() {
            return Text.translatable("configurablestatisticunits.config.option.timeUnit." + name().toLowerCase());
        }
    }

    public enum DistanceUnit implements NameableEnum {
        NO_CHANGE,
        CENTIMETERS,
        METERS,
        KILOMETERS;

        @Override
        public Text getDisplayName() {
            return Text.translatable("configurablestatisticunits.config.option.distanceUnit." + name().toLowerCase());
        }
    }

    @SerialEntry
    public static TimeUnit timeUnit = TimeUnit.NO_CHANGE;

    @SerialEntry
    public static DistanceUnit distanceUnit = DistanceUnit.NO_CHANGE;

    public static Screen getConfigScreen(Screen parentScreen) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("configurablestatisticunits.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("configurablestatisticunits.config.title"))
                        .option(Option.<TimeUnit>createBuilder()
                                .name(Text.translatable("configurablestatisticunits.config.option.timeUnit"))
                                .binding(TimeUnit.NO_CHANGE, () -> Config.timeUnit, newVal -> Config.timeUnit = newVal)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(TimeUnit.class))
                                .build()
                        )
                        .option(Option.<DistanceUnit>createBuilder()
                                .name(Text.translatable("configurablestatisticunits.config.option.distanceUnit"))
                                .binding(DistanceUnit.NO_CHANGE, () -> Config.distanceUnit, newVal -> Config.distanceUnit = newVal)
                                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(DistanceUnit.class))
                                .build()
                        )
                        .build()
                )
                .save(HANDLER::save)
                .build()
                .generateScreen(parentScreen);
    }
}
