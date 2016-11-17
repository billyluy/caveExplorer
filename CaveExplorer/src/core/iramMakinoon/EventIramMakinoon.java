package core.iramMakinoon;

import core.Event;

public class EventIramMakinoon implements Event {

	MakGenerate board; 
	MakInput input; 
	
	
	public void play(){
		board = new MakGenerate();
		
		input = new MakInput();
	}

}
//iram and mak