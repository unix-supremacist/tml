package neoengineers.tml.events;

import neoengineers.tml.api.events.Event;

public class EventListens {
	public static void listen() {
		Event.listen(Event.PartRegistration, new PartReg());
		Event.listen(Event.MaterialRegistration, new MatReg());
	}
}
