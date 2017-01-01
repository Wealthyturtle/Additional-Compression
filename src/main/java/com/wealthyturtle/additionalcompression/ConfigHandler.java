package com.wealthyturtle.additionalcompression;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;

    // Categories
    public static String compressedBlocks = "Compressed Blocks";
    public static String compatibility = "Mod Compatibility";

    // Options
    public static Boolean extraUtils = true;
    public static Boolean exCompressum = true;
    public static String[] compressedBlocksWhitelist;
    private static String[] compressedBlocksWhitelistDefaults = {
    		//vanilla
    		"cobblestone:minecraft:cobblestone:0:10",
    		"stone:minecraft:stone:0:10",
    		"gravel:minecraft:gravel:0:10",
    		"sand:minecraft:sand:0:10",
    		"dirt:minecraft:dirt:0:10",
    		"clay:minecraft:clay:0:10",
    		"cobblestoneMossy:minecraft:mossy_cobblestone:0:10",
    		//"flint:minecraft:flint:0:10",
    		"netherrack:minecraft:netherrack:0:10",
    		"soulsand:minecraft:soul_sand:0:10",
    		"endstone:minecraft:end_stone:0:10",
    		//ex nihilo
    		"dust:exnihilo:dust:0:10",
    		"gravelNether:exnihilo:exnihilo.gravel_nether:0:10",
    		"gravelEnd:exnihilo:exnihilo.gravel_ender:0:10",
    		//single compression
    		"cropCarrot:minecraft:carrot:0:1",
    		"itemNetherStar:minecraft:nether_star:0:1",
    		"charcoal:minecraft:coal:1:1",
    		"cropApple:minecraft:apple:0:1",
    		"bone:minecraft:bone:0:1",
    		"bread:minecraft:bread:0:1",
    		"meatPorkchop:minecraft:porkchop:0:1",
    		"meatFish:minecraft:fish:0:1",
    		"cookie:minecraft:cookie:0:1",
    		//"cropMelon:minecraft:melon:0:1",
    		"meatBeef:minecraft:beef:0:1",
    		"meatChicken:minecraft:chicken:0:1",
    		"meatRotten:minecraft:rotten_flesh:0:1",
    		"spidereye:minecraft:spider_eye:0:1",
    		"cropPotato:minecraft:potato:0:1",
    		"string:minecraft:string:0:1",
    		"feather:minecraft:feather:0:1",
    		"dustGunpowder:minecraft:gunpowder:0:1",
    		//"cropWheat:minecraft:wheat:0:1",
    		"leather:minecraft:leather:0:1",
    		"cropSugarcane:minecraft:reeds:0:1",
    		"egg:minecraft:egg:0:1",
    		"dustSugar:minecraft:sugar:0:1",
    		"blaze:minecraft:blaze_rod:0:1",
    		"pearlEnder:minecraft:ender_pearl:0:1",
    		"cropNetherwart:minecraft:nether_wart:0:1"
    };

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

	public static void syncConfig() {
		config.setCategoryComment(compressedBlocks, "This is where all the compressed blocks are registered, you can freely add your own blocks, remove the ones you dislike or modify existing ones.");

		compressedBlocksWhitelist = config.getStringList("compressedBlockWhitelist", compressedBlocks, compressedBlocksWhitelistDefaults,
				"The syntax is: OredictName:ModID:ItemID:ItemMetadata:MaxCompressionLevel"
						+ "\nOredictname: The ore dictionary name of the item/block (if it exists) you want to compress, also becomes the name of the compressed block."
						+ "\nModID: The mod id of the item/block you want to compress."
						+ "\nItemID: The id of the item/block you want to compress."
						+ "\nItemMetadata: The metadata value of the item/block you want to compress, most of the time this is 0."
						+ "\nMaxCompressionLevel: The maximum level you want the item/block to be compressed to, setting this below 1 or above 10 will probably mess things up.");

		config.setCategoryComment(compatibility, "This is where you can disable mod compatibility with specific mods, compatibility will never be loaded if the required mod isn't installed.");

		extraUtils = config.getBoolean("extraUtils", compatibility, extraUtils, "default: true");
		exCompressum = config.getBoolean("exCompressum", compatibility, exCompressum, "default: true");

		config.save();
	}
}
