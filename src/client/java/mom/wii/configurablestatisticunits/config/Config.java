package mom.wii.configurablestatisticunits.config;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.DisplayNameConvention;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.SerializedNameConvention;
import folk.sisby.kaleido.lib.quiltconfig.api.metadata.NamingSchemes;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ComplexConfigValue;
import folk.sisby.kaleido.lib.quiltconfig.api.values.ConfigSerializableObject;

@DisplayNameConvention(NamingSchemes.SPACE_SEPARATED_LOWER_CASE)
@SerializedNameConvention(NamingSchemes.SNAKE_CASE)
public class Config extends WrappedConfig {

    public Boolean enabled = true;

    public TimeUnit timeUnit = TimeUnit.VANILLA_BEHAVIOR;

    public DistanceUnit distanceUnit = DistanceUnit.VANILLA_BEHAVIOR;

    public enum TimeUnit implements ConfigSerializableObject<String> {
        VANILLA_BEHAVIOR,
        MINUTES_AND_SECONDS,
        HOURS,
        DAYS,
        YEARS;

        @Override
        public ConfigSerializableObject<String> convertFrom(String representation) {
            return valueOf(representation);
        }

        @Override
        public String getRepresentation() {
            return this.name();
        }

        @Override
        public ComplexConfigValue copy() {
            return this;
        }
    }

    public enum DistanceUnit implements ConfigSerializableObject<String> {
        VANILLA_BEHAVIOR,
        CENTIMETERS,
        METERS,
        KILOMETERS;

        @Override
        public ConfigSerializableObject<String> convertFrom(String representation) {
            return valueOf(representation);
        }

        @Override
        public String getRepresentation() {
            return this.name();
        }

        @Override
        public ComplexConfigValue copy() {
            return this;
        }
    }
}
