package latmod.ftbu.item;
import java.util.List;

import cpw.mods.fml.relauncher.*;
import latmod.ftbu.api.item.IItemLM;
import latmod.ftbu.util.LMMod;
import latmod.lib.FastList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.IIcon;

public abstract class ItemLM extends Item implements IItemLM
{
	public final String itemName;
	public final FastList<ItemStack> itemsAdded;
	public final LMMod mod;
	
	public boolean requiresMultipleRenderPasses = false;
	
	public ItemLM(String s)
	{
		super();
		mod = getMod();
		itemName = s;
		setUnlocalizedName(mod.getItemName(s));
		itemsAdded = new FastList<ItemStack>();
	}
	
	public abstract LMMod getMod();
	
	@SuppressWarnings("unchecked")
	public final <E> E register() { mod.addItem(this); return (E)this; }
	
	public final Item getItem()
	{ return this; }

	public final String getItemID()
	{ return itemName; }
	
	@SideOnly(Side.CLIENT)
	public abstract CreativeTabs getCreativeTab();
	
	public void onPostLoaded()
	{ addAllDamages(1); }
	
	public void loadRecipes()
	{
	}
	
	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item j, CreativeTabs c, List l)
	{
		for(ItemStack is : itemsAdded)
		if(isVisible(is)) l.add(is);
	}
	
	public String getUnlocalizedName(ItemStack is)
	{ return mod.getItemName(itemName); }
	
	public void addAllDamages(int until)
	{
		for(int i = 0; i < until; i++)
		itemsAdded.add(new ItemStack(this, 1, i));
	}
	
	public void addAllDamages(int[] dmg)
	{
		for(int i = 0; i < dmg.length; i++)
		itemsAdded.add(new ItemStack(this, 1, dmg[i]));
	}
	
	public final boolean requiresMultipleRenderPasses()
	{ return requiresMultipleRenderPasses; }
	
	public int getRenderPasses(int m)
	{ return 1; }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{ itemIcon = ir.registerIcon(mod.assets + itemName); }
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int i, int r)
	{ return itemIcon; }
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack is, int r)
	{ return getIconFromDamageForRenderPass(is.getItemDamage(), r); }
	
	@SideOnly(Side.CLIENT)
	public final IIcon getIconFromDamage(int i)
	{ return getIconFromDamageForRenderPass(i, 0); }
	
	@SideOnly(Side.CLIENT)
	public final IIcon getIconIndex(ItemStack is)
	{ return getIcon(is, 0); }
	
	private static final FastList<String> infoList = new FastList<String>();
	
	@SuppressWarnings("all") @SideOnly(Side.CLIENT)
    public final void addInformation(ItemStack is, EntityPlayer ep, List l, boolean b)
	{
		infoList.clear();
		addInfo(is, ep, infoList);
		l.addAll(infoList);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInfo(ItemStack is, EntityPlayer ep, FastList<String> l)
	{
	}
	
	public boolean isVisible(ItemStack is)
	{ return true; }
}