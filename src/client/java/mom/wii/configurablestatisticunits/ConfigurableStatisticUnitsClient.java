package mom.wii.configurablestatisticunits;

import mom.wii.configurablestatisticunits.config.Config;
import net.fabricmc.api.ClientModInitializer;

public class ConfigurableStatisticUnitsClient implements ClientModInitializer {
	public static final String MOD_ID = "configurable-statistic-units";

	@Override
	public void onInitializeClient() {
		Config.HANDLER.load();
	}
}