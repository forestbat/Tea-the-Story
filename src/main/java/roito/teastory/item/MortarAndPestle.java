package roito.teastory.item;

import net.minecraft.item.ItemStack;
import roito.teastory.common.CreativeTabsRegister;

public class MortarAndPestle extends TSItem
{
    public MortarAndPestle(String name, int maxDamage)
    {
        super(name, 1, CreativeTabsRegister.tabTeaStory);
        this.setContainerItem(this);
        this.setMaxDamage(maxDamage);
        this.setNoRepair();
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        ItemStack stack = itemStack.copy();
        stack.setItemDamage(stack.getItemDamage() + 1);
        stack.setCount(1);
        return stack;
    }
}
