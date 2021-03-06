package roito.teastory.compat.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import roito.teastory.item.ItemRegister;
import roito.teastory.recipe.ITeaTableRecipe;
import roito.teastory.recipe.RecipeRegister;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeTeaTable implements IRecipeWrapper
{

    public static List<RecipeTeaTable> getWrappedRecipeList()
    {
        List<RecipeTeaTable> recipesToReturn = new ArrayList<>();
        for (ITeaTableRecipe recipe : RecipeRegister.managerTeaTable.getRecipes())
        {
            recipesToReturn.add(new RecipeTeaTable(recipe));
        }
        return recipesToReturn;
    }

    private final ITeaTableRecipe recipe;

    public RecipeTeaTable(ITeaTableRecipe recipe)
    {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutput(ItemStack.class, recipe.getOutput());
    }

    public List<ItemStack> getSugarInputs()
    {
        if (recipe.getSugarInput() != null)
        {
            List<ItemStack> list = new ArrayList<>();
            list.addAll(recipe.getSugarInput());
            return list;
        }
        return Collections.emptyList();
    }

    public List<ItemStack> getCupInputs()
    {
        return Collections.singletonList(recipe.getCupInput());
    }

    public List<ItemStack> getTeaLeafInputs()
    {
        return Collections.singletonList(recipe.getTeaLeafInput());
    }

    public List<ItemStack> getToolInputs()
    {
        if (recipe.getToolInput() != null)
        {
            List<ItemStack> list = new ArrayList<>();
            list.addAll(recipe.getToolInput());
            return list;
        }
        return Collections.emptyList();
    }

    public static List<ItemStack> getPotInputs()
    {
        List<ItemStack> potList = new ArrayList<>();
        potList.add(new ItemStack(ItemRegister.hw_pot_stone));
        potList.add(new ItemStack(ItemRegister.hw_pot_iron));
        potList.add(new ItemStack(ItemRegister.hw_pot_porcelain));
        potList.add(new ItemStack(ItemRegister.hw_pot_zisha));
        return potList;
    }

    public List<ItemStack> getInputs()
    {
        List<ItemStack> list = new ArrayList<>();
        list.add(recipe.getTeaLeafInput());
        list.add(recipe.getCupInput());
        if (recipe.getSugarInput() != null)
        {
            list.addAll(recipe.getSugarInput());
        }
        if (recipe.getToolInput() != null)
        {
            list.addAll(recipe.getToolInput());
        }
        list.addAll(getPotInputs());
        return list;
    }

    public List<ItemStack> getOutputs()
    {
        return Collections.singletonList(recipe.getOutput());
    }
}
