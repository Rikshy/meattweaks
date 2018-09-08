package de.nerdycraft.meattweaks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minecraftforge.common.config.Config.Comment;

public class Config {

	@net.minecraftforge.common.config.Config(modid = Constants.MOD_ID)
	public static class MTConfig {

		@Comment({
				"The weight that plantfiber have when breaking tall grass. 15 by default, set to 0 to disable drops" })
		public static int fiberWeight = 15;
		@Comment({
				"The weight that sticks have when breaking leaves. 15 by default, set to 0 to disable drops" })
		public static int sticksFromLeaves = 15;
		@Comment({
				"Disable tree punching for Wood. true is default" })
		public static boolean noPunching = true;
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface SubConfig {
	}
}
