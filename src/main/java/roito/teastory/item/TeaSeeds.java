package roito.teastory.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import roito.teastory.TeaStory;
import roito.teastory.block.BlockRegister;
import roito.teastory.common.CreativeTabsRegister;

import javax.annotation.Nullable;
import java.util.List;

public class TeaSeeds extends ItemSeeds
{
    public TeaSeeds()
    {
        super(BlockRegister.teaplant, Blocks.FARMLAND);
        this.setTranslationKey("tea_seeds");
        this.setRegistryName(new ResourceLocation(TeaStory.MODID, "tea_seeds"));
        this.setCreativeTab(CreativeTabsRegister.tabTeaStory);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.tea_seeds.height"));
            tooltip.add(TextFormatting.WHITE + I18n.format("teastory.tooltip.tea_seeds.temperature"));
        } else
            tooltip.add(TextFormatting.ITALIC + I18n.format("teastory.tooltip.shiftfordetail"));
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ) == EnumActionResult.SUCCESS)
        {
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
