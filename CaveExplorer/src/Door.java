

public class Door {
	
	private boolean locked;
	private boolean open;
	private String description;
	private String details;
	
	public Door(){
		//makes a default door
		locked = false;
		open = true;
		description = "passage";
		details = "";
	}
	
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		//needs to use this. b/c the local variable is same name as global variable
		this.open = open;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setLocked(boolean b){
		//sets the lock value of a door
		locked = b;
	}
	
	public boolean isLocked(){
		return locked;
	}

}
