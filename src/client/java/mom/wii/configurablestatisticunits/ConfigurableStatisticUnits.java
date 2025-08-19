package mom.wii.configurablestatisticunits;

import mom.wii.configurablestatisticunits.config.Config;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigurableStatisticUnits implements ClientModInitializer {
	public static final String MOD_ID = "configurable-statistic-units";
	public static final Config CONFIG = Config.createToml(FabricLoader.getInstance().getConfigDir(), "", MOD_ID, Config.class);

	@Override
	public void onInitializeClient() {}
}