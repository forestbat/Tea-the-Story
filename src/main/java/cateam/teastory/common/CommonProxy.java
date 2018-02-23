package cateam.teastory.common;

import cateam.teastory.block.BlockLoader;
import cateam.teastory.config.ConfigMain;
import cateam.teastory.entity.EntityLoader;
import cateam.teastory.inventory.GuiElementLoader;
import cateam.teastory.item.ItemLoader;
import cateam.teastory.potion.PotionLoader;
import cateam.teastory.recipe.RecipeLoader;
import cateam.teastory.tileentity.TileEntityLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		new ConfigMain(event);
		new CreativeTabsLoader(event);
		new ItemLoader(event);
		new BlockLoader(event);
		new OreDictionaryLoader(event);
		new PotionLoader(event);
		new EntityLoader();
		new TileEntityLoader(event);
	}

	public void init(FMLInitializationEvent event)
	{
		new RecipeLoader();
		new CraftingLoader();
		new AchievementLoader();
		new EventLoader();
		new GuiElementLoader();
		new SeedDrops();
	}

	public void postInit(FMLPostInitializationEvent event)
	{
	}
}