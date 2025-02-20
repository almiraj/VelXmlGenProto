package velXml.beans;

import javax.xml.bind.annotation.XmlElement;

public class Item {

	private String title = "";

	private ItemType type;

	@XmlElement
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement
	public ItemType getType() {
		return this.type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Item [title=" + this.title + ", type=" + this.type + "]";
	}

}
