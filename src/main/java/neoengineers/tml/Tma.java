package neoengineers.tml;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import neoengineers.tml.api.block.CropBase;
import neoengineers.tml.api.block.Machine;
import neoengineers.tml.api.block.Tile;
import neoengineers.tml.api.events.Event;
import neoengineers.tml.api.gui.handler;
import neoengineers.tml.api.item.meta.MetaSeedFoodItem;
import neoengineers.tml.api.material.Registery;
import neoengineers.tml.events.EventListens;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

@Mod(modid = Tags.MODID, name = Tags.MODNAME, version = Tags.VERSION)
public class Tma {
	@Mod.Instance(Tags.MODID)
	public static Tma tmaInstance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		EventListens.listen();
		Event.runEvents();

		//this.loadCrops(); //uncomment to play with crops ;p
		GameRegistry.registerTileEntity(Tile.class, "tmatile");
		GameRegistry.registerBlock(new Machine(), "gay");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Registery.registerOreDict();
		NetworkRegistry.INSTANCE.registerGuiHandler(tmaInstance, new handler());
	}

	public MetaSeedFoodItem metaSeedFoodItem;
	public CropBase specialCarrotCrop;
	public ItemStack specialCarrotItemStack;

	private void loadCrops() {
		//Create seed food meta item
		this.metaSeedFoodItem = new MetaSeedFoodItem(Tags.MODID, Blocks.farmland);
		//Registers it to the forge registry
		GameRegistry.registerItem(this.metaSeedFoodItem, "metaSeedFoodItem");
		//Creates a carrot crop
		this.specialCarrotCrop = new CropBase(Tags.MODID, "CarrotCrop", this.metaSeedFoodItem, 0);
		//Registers it to the forge registry
		GameRegistry.registerBlock(this.specialCarrotCrop, "CarrotCrop");
		//Creates the carrot food and seed, bound to the carrot crop
		this.specialCarrotItemStack = this.metaSeedFoodItem.addItem("Carrot", 0, this.specialCarrotCrop, 50, 50);
	}
}
