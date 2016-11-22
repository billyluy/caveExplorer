package core;

public class Inventory {

	private boolean hasMap;
	private String map;

	public Inventory() {
		hasMap = false;
		updateMap();
	}

	public void updateMap() {
		map = " ";
		for (int i = 0; i < CaveExplorer.caves[0].length - 1; i++) {
			map += "____";// 4 underscores
		}
		map += "___\n";// 3 underscores
		for (CaveRoom[] row : CaveExplorer.caves) {
			// three rows of text per row
			for (int i = 0; i < 3; i++) {
				String text = "";
				// a line of text for each row
				for (CaveRoom cr : row) {
					// text +="|";
					// if door is open
					if (cr.getDoor(CaveRoom.WEST) != null && cr.getDoor(CaveRoom.WEST).isOpen())
						text += " ";
					else
						text += "|";
					if (i == 0)
						text += "   ";
					else if (i == 1) {
						if (!(cr instanceof EventRoom)) {
							text += " " + cr.getContents() + " ";
						} else {
							if (!((EventRoom) cr).occured()) {
								text += " * ";
							} else {
								text += " " + cr.getContents() + " ";
							}
						}
					} else if (i == 2) {
						// if door is open
						if (cr.getDoor(CaveRoom.SOUTH) != null && cr.getDoor(CaveRoom.SOUTH).isOpen())
							text += "   ";
						else
							text += "___";
					}
				} // last cave room in row
				text += "|";
				map += text + "\n";
			} // last of 3 text lines
		} // last row

	}

	public String getDescription() {
		if (hasMap) {
			return map;
		} else {
			return "There is nothing in your inventory";
		}
	}

	public void setHasMap(boolean b) {
		hasMap = b;
	}

}
