package neoengineers.tml.events;

import neoengineers.tml.Tags;
import neoengineers.tml.api.events.PartRegistrationEvent;
import neoengineers.tml.api.material.Part;
import neoengineers.tml.api.material.Registery;

public class PartReg implements PartRegistrationEvent {
	public enum Parts {
		ingot(new Part()),
		dust(new Part()),
		plate(new Part()),
		gear(new Part()),
		gem(new Part()),
		lens(new Part()),
		stick(new Part());

		public final Part part;

		Parts(Part part) {
			this.part = part.setName(this.toString());
		}
	}

	@Override
	public void event() {
		for (Parts part : Parts.values()) {
			Registery.registerPart(part.part);
		}

		for (Integer i = 0; i < 3500; i++) {
			Registery.registerPart(new Part(Tags.MODID).setName(i.toString()));
		}
	}
}
