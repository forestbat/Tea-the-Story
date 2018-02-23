package cateam.teastory.tileentity;

import cateam.teastory.TeaStory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader
{
	public TileEntityLoader(FMLPreInitializationEvent event)
	{
		registerTileEntity(TileEntityTeaStove.class, "TeaStove");
		registerTileEntity(TileEntityTeaDrink.class, "TeaDrink");
		registerTileEntity(TileEntityTeaDryingPan.class, "TeaDryingPan");
		registerTileEntity(TileEntityCookingPan.class, "CookingPan");
		registerTileEntity(TileEntityTeaTable.class, "TeaTable");
	}

	public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
	{
		GameRegistry.registerTileEntity(tileEntityClass, TeaStory.MODID + ":" + id);
	}
}
