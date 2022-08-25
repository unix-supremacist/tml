package neoengineers.tml.api.item.meta;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import neoengineers.tml.api.item.base.SeedBase;
import neoengineers.tml.api.item.instance.SeedInstance;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class MetaSeedItem extends SeedBase implements IMetaItem {
	protected static final String TYPE_NAME = "seed";
	protected final HashMap<Integer, SeedInstance> items = new HashMap<>();
	protected int lastID = 0;
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;

	public MetaSeedItem(String modid, Block farmland) {
		super(modid, farmland);
		this.setHasSubtypes(true);
	}

	public MetaSeedItem(String modid) {
		this(modid, Blocks.farmland);
	}

	public ItemStack addItem(String name, Block crop) {
		int meta = this.lastID++;
		this.items.put(meta, new SeedInstance(name, crop));
		return new ItemStack(this, 1, meta);
	}

	protected SeedInstance getItem(ItemStack item) {
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

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int u, float u1, float u2, float u3) {
		return super.onItemUse(item, player, world, x, y, z, u, u1, u2, u3, this.getItem(item).getCrop());
	}
}
