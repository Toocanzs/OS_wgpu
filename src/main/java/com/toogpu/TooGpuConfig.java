package com.toogpu;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("example")
public interface TooGpuConfig extends Config
{
	String generalSettings = "generalSettings";
	String KEY_EXPANDED_MAP_LOADING_CHUNKS = "expandedMapLoadingChunks";
	@Range(
			max = 5
	)
	@ConfigItem(
			keyName = KEY_EXPANDED_MAP_LOADING_CHUNKS,
			name = "Extended map loading",
			description =
					"How much further the map should be loaded. The maximum is 5 extra chunks.<br>" +
							"Note, extending the map can have a very high impact on performance.",
			position = 1,
			section = generalSettings
	)
	default int expandedMapLoadingChunks() {
		return 3;
	}
}
