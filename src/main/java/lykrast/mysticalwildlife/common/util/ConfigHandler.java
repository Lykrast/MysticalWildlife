package lykrast.mysticalwildlife.common.util;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ConfigHandler {
	//Good amount of stuff borrowed from Mystical World
	//https://github.com/MysticMods/MysticalWorld/blob/1.14/src/main/java/epicsquid/mysticalworld/config/ConfigManager.java
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static ForgeConfigSpec CONFIG_SPEC;
	
	public static IntValue krillForageTime;
	public static IntValue cicapteraLovelyEssenceTime;
	
	public static MobConfig vrontausaurus;
	public static MobConfig yagaHog;
	public static MobConfig duskLurker;
	public static MobConfig cicapteraAzure;
	public static MobConfig cicapteraVerdant;
	public static MobConfig cicapteraCrimson;
	public static MobConfig cicapteraSandy;
	public static MobConfig cicapteraWintry;
	public static MobConfig cicapteraLovely;
	public static MobConfig plumper;
	public static MobConfig krill;

	static {
		registerSpawns();
		registerMiscConfig();
		
		CONFIG_SPEC = BUILDER.build();
	}
	
	private static void registerSpawns() {
		BUILDER.comment("Mob spawns").push("spawns");
		
		vrontausaurus = new MobConfig("Vrontausaurus", "vrontausaurus", 6, 3, 4).make(BUILDER);
		yagaHog = new MobConfig("Yaga Hog", "yaga_hog", 12, 4, 5).make(BUILDER);
		duskLurker = new MobConfig("Dusk Lurker", "dusk_lurker", 8, 2, 5).make(BUILDER);
		cicapteraAzure = new MobConfig("Azure Cicaptera", "cicaptera_azure", 8, 4, 6).make(BUILDER);
		cicapteraVerdant = new MobConfig("Verdant Cicaptera", "cicaptera_verdant", 8, 4, 6).make(BUILDER);
		cicapteraCrimson = new MobConfig("Crimson Cicaptera", "cicaptera_crimson", 2, 3, 4).make(BUILDER);
		cicapteraSandy = new MobConfig("Sandy Cicaptera", "cicaptera_sandy", 4, 4, 6).make(BUILDER);
		cicapteraWintry = new MobConfig("Wintry Cicaptera", "cicaptera_wintry", 8, 4, 6).make(BUILDER);
		cicapteraLovely = new MobConfig("Lovely Cicaptera", "cicaptera_lovely", 4, 2, 3).make(BUILDER);
		plumper = new MobConfig("Plumper", "plumper", 12, 3, 5).make(BUILDER);
		krill = new MobConfig("Krill", "krill", 12, 1, 3).make(BUILDER);
		
		BUILDER.pop();
	}
	
	private static void registerMiscConfig() {
		BUILDER.comment("Misc").push("misc");
		
		krillForageTime = BUILDER
				.translation("config.mysticalwildlife.krill.forage_time")
				.comment("Base time between Krill forages (can be up to 2x that amount in game)")
				.defineInRange("krillForageTime", 1800, 1, Integer.MAX_VALUE / 2);
		
		cicapteraLovelyEssenceTime = BUILDER
				.translation("config.mysticalwildlife.cicaptera_lovely.essence_time")
				.comment("Base time between Aphrodite Essence drops (can be up to 2x that amount in game)")
				.defineInRange("cicapteraLovelyEssenceTime", 12000, 1, Integer.MAX_VALUE / 2);
		
		BUILDER.pop();
	}

	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
		configData.load();
		spec.setConfig(configData);
	}
}
