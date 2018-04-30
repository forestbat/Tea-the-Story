package roito.teastory.block;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import roito.teastory.TeaStory;
import roito.teastory.common.AchievementLoader;
import roito.teastory.common.CreativeTabsLoader;
import roito.teastory.item.ItemCup;

public class FullKettle extends Kettle
{
	private String drink;
	private String kettleKind;
	private String nextKettle;
	public boolean full;
	public FullKettle(String kettleKind, String drink, String nextKettle, boolean tabs, boolean a2)
	{
		super(a2 ? drink + "_" + kettleKind + "2" : drink + "_" + kettleKind, Material.ROCK);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CAPACITY, 0));
		if (tabs)
		{
			this.setCreativeTab(CreativeTabsLoader.tabDrink);
		}
		this.drink = drink;
		this.full = tabs;
		this.kettleKind = kettleKind;
		this.nextKettle = nextKettle;
	}
	
	public int getMaxCapacity()
	{
		return ((EmptyKettle) Block.getBlockFromName(TeaStory.MODID + ":" + "empty_" + kettleKind)).getMaxCapacity();
	}
	
	public Block getNextKettle()
	{
		return Block.getBlockFromName(TeaStory.MODID + ":" + nextKettle);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		if (worldIn.isRemote)
		{	
			return true;
		}
		else
		{
			if (heldItem != null)
			{
				if (heldItem.getItem() instanceof ItemCup)
				{
					playerIn.addStat(AchievementLoader.getDrink);
					if (!playerIn.capabilities.isCreativeMode)
					{
						heldItem.stackSize--;
					}
					int meta = heldItem.getItemDamage();
					ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(this.getDrink(this), 1, meta));
					int meta2 = getMetaFromState(worldIn.getBlockState(pos));
					if ((meta2 >> 2) == 3)
					{
						worldIn.setBlockState(pos, getNextKettle().getDefaultState().withProperty(FACING, worldIn.getBlockState(pos).getValue(FACING)));
					}
					else
					{
						worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, worldIn.getBlockState(pos).getValue(FACING)).withProperty(CAPACITY, worldIn.getBlockState(pos).getValue(CAPACITY).intValue() + 1));
					}
					return true;
				}
				else return false;
			}
			else return false;
		}
	}

	public Item getDrink(FullKettle kettle)
	{
		return Item.getByNameOrId(TeaStory.MODID + ":" + drink);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING, CAPACITY);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing facing = EnumFacing.getHorizontal(meta & 3);
		int cc = Integer.valueOf(meta >> 2);
		return this.getDefaultState().withProperty(FACING, facing).withProperty(CAPACITY, cc);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int facing = state.getValue(FACING).getHorizontalIndex();
		int cc = state.getValue(CAPACITY).intValue() << 2;
		return cc | facing;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		IBlockState origin = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
		return origin.withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(itemIn, 1, 0));
	}

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyInteger CAPACITY = PropertyInteger.create("capacity", 0, 3);
}
