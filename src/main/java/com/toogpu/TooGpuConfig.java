/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2021, 117 <https://twitter.com/117scape>
 * Copyright (c) 2024, Toocanzs <https://github.com/Toocanzs>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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

	String KEY_UNLOCK_FPS = "unlockFps";
	@ConfigItem(
			keyName = KEY_UNLOCK_FPS,
			name = "Unlock FPS",
			description = "Removes the 50 FPS cap for some game content, such as camera movement and dynamic lighting.",
			position = 2,
			section = generalSettings
	)
	default boolean unlockFps()
	{
		return false;
	}

	String KEY_FPS_TARGET = "fpsTarget";
	@ConfigItem(
			keyName = KEY_FPS_TARGET,
			name = "FPS Target",
			description =
					"Controls the maximum number of frames per second.<br>" +
							"This setting only applies if Unlock FPS is enabled, and VSync Mode is set to 'off'.",
			position = 3,
			section = generalSettings
	)
	@Range(
			min = 0,
			max = 999
	)
	default int fpsTarget()
	{
		return 60;
	}
}
