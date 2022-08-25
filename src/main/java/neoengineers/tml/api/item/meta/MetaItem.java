package neoengineers.tml.api.item.meta;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import neoengineers.tml.api.item.base.ItemBase;
import neoengineers.tml.api.item.instance.ItemInstance;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.HashMap;
import java.util.List;

public class MetaItem extends ItemBase implements IMetaItem {
	protected static final String TYPE_NAME = "meta";
	protected final HashMap<Integer, ItemInstance> items = new HashMap<>();
	protected int lastID = 0;
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;

	public MetaItem(String modid) {
		super(modid);
		this.setHasSubtypes(true);
	}

	public ItemStack addItem(String name) {
		int meta = this.lastID++;
		this.items.put(meta, new ItemInstance(name));
		return new ItemStack(this, 1, meta);
	}

	protected ItemInstance getItem(ItemStack item) {
		return this.items.get(item.getItemDamage());
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		return this.getUnlocalizedName(this.modid, TYPE_NAME, this.getItem(item).getName());
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
		this.getSubItems(this, this.items, itemList);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		this.registerIcons(this.modid, this.items, TYPE_NAME, register);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return this.getIconFromDamage(this.items, meta);
	}
}
