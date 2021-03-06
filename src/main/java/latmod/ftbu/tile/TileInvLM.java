package latmod.ftbu.tile;

import ftb.lib.item.LMInvUtils;
import latmod.lib.MathHelperLM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileInvLM extends TileLM implements IInventory
{
	public final ItemStack[] items;
	public boolean dropItems = true;
	
	public final int[] ALL_SLOTS;
	
	public TileInvLM(int invSize)
	{
		items = new ItemStack[invSize];
		ALL_SLOTS = MathHelperLM.getAllInts(0, invSize);
	}
	
	public void readTileData(NBTTagCompound tag)
	{
		super.readTileData(tag);
		LMInvUtils.readItemsFromNBT(items, tag, "Items");
	}
	
	public void writeTileData(NBTTagCompound tag)
	{
		super.writeTileData(tag);
		LMInvUtils.writeItemsToNBT(items, tag, "Items");
	}
	
	public void onBroken()
	{
		if(isServer() && dropItems && items.length > 0)
			LMInvUtils.dropAllItems(worldObj, xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, items);
		
		markDirty();
		
		super.onBroken();
	}
	
	public String getInventoryName()
	{ return hasCustomInventoryName() ? customName : getBlockType().getLocalizedName(); }
	
	public boolean hasCustomInventoryName()
	{ return customName != null; }
	
	public void openInventory() { }

	public void closeInventory() { }
	
	public ItemStack decrStackSize(int i, int j)
	{ return LMInvUtils.decrStackSize(this, i, j); }
	
	public int getInventoryStackLimit()
	{ return 64; }
	
	public int getSizeInventory()
	{ return items.length; }
	
	public ItemStack getStackInSlot(int i)
	{ return items[i]; }
	
	public ItemStack getStackInSlotOnClosing(int i)
	{ return LMInvUtils.getStackInSlotOnClosing(this, i); }
	
	public void setInventorySlotContents(int i, ItemStack is)
	{ items[i] = is; }
	
	public boolean isUseableByPlayer(EntityPlayer ep)
	{ return security.canInteract(ep); }
	
	public boolean isItemValidForSlot(int i, ItemStack is)
	{ return true; }
}