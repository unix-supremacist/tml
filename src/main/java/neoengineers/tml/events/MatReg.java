package neoengineers.tml.events;

import neoengineers.tml.api.events.MaterialRegistrationEvent;
import neoengineers.tml.api.material.Material;
import neoengineers.tml.api.material.Registery;

public class MatReg implements MaterialRegistrationEvent {
	public enum Materials {
		copper(new Material(0xB4713D).addPart(PartReg.Parts.dust, PartReg.Parts.ingot)),
		iron(new Material(0xB8B8B8).addPart(PartReg.Parts.dust)),
		gold(new Material(0xFFFF1E).addPart(PartReg.Parts.dust, PartReg.Parts.plate)),
		electrum(new Material(0xFFFF64).addPart(PartReg.Parts.dust, PartReg.Parts.plate, PartReg.Parts.ingot)),
		aluminium(new Material(0x80C8F0).addPart(PartReg.Parts.dust, PartReg.Parts.plate, PartReg.Parts.ingot)),
		chrome(new Material(0xF5CEE3).addPart(PartReg.Parts.dust, PartReg.Parts.plate, PartReg.Parts.ingot)),
		ruby(new Material(0xFF6464).addPart(PartReg.Parts.dust, PartReg.Parts.gem, PartReg.Parts.plate, PartReg.Parts.lens)),
		redGarnet(new Material(0xC85050).addPart(PartReg.Parts.dust, PartReg.Parts.gem, PartReg.Parts.plate, PartReg.Parts.lens)),
		platinum(new Material(0xFFFFC8).addPart(PartReg.Parts.dust, PartReg.Parts.ingot)),
		enderium(new Material(0x5A9187).addPart(PartReg.Parts.dust, PartReg.Parts.plate, PartReg.Parts.ingot)),
		tungsten(new Material(0x323232).addPart(PartReg.Parts.dust, PartReg.Parts.plate, PartReg.Parts.ingot)),
		bronze(new Material(0xFFB400).addPart(PartReg.Parts.dust, PartReg.Parts.gear, PartReg.Parts.stick, PartReg.Parts.plate, PartReg.Parts.ingot)),
		mixedMetal(new Material("mixedMetal").addPart(PartReg.Parts.ingot)),
		iridiumAlloy(new Material("iridiumAlloy").addPart(PartReg.Parts.ingot)),
		advancedAlloy(new Material(0x87876E).addPart(PartReg.Parts.plate)),
		nikolite(new Material(0x32C8FF).addPart(PartReg.Parts.dust, PartReg.Parts.gem)),
		redstone(new Material(0xC80000).addPart(PartReg.Parts.gem)),
		tin(new Material(0xDCDCDC).addPart(PartReg.Parts.dust, PartReg.Parts.ingot)),
		wroughtIron(new Material(0xDCEBEB).addPart(PartReg.Parts.dust, PartReg.Parts.plate, PartReg.Parts.ingot, PartReg.Parts.gear, PartReg.Parts.stick)),
		titanium(new Material(0xAA8FDE).addPart(PartReg.Parts.dust, PartReg.Parts.gear, PartReg.Parts.stick, PartReg.Parts.plate, PartReg.Parts.ingot)),
		tungstensteel(new Material(0x6464A0).addPart(PartReg.Parts.dust, PartReg.Parts.gear, PartReg.Parts.stick, PartReg.Parts.plate, PartReg.Parts.ingot)),
		steel(new Material(0x808080).addPart(PartReg.Parts.dust, PartReg.Parts.gear, PartReg.Parts.stick, PartReg.Parts.plate, PartReg.Parts.ingot));

		public final Material material;

		Materials(Material material) {
			this.material = material.setName(this.toString());
		}
	}

	@Override
	public void event() {
		for (Materials material : Materials.values()) {
			Registery.registerMaterial(material.material);
		}

		for (int i = 0; i < 256; i++) {
			Material test = new Material(i*5592).setName(i+"");
			for (int i2 = 0; i2 < 3500; i2++) {
				test.addPart(i2+"");
			}
			//test.setPartSet(i/12+"");
			Registery.registerMaterial(test);
		}
	}
}
