package core;


public class EventRoom extends CaveRoom {

	private Event event;
	private boolean eventOccured;

	public EventRoom(String description, Event event) {
		super(description);
		eventOccured = false;
		this.event = event;
	}
	
	//this enter method happens instead of the other on
	public void enter(){
		//calls the superclass' method enter()
		super.enter();
		if(!eventOccured){
			eventOccured = true;
			event.play();
		}
	}

}
