package velXml.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Items {

	private String title = "";

	private List<Item> itemList;

	@XmlElement
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name="item")
	public List<Item> getItemList() {
		return this.itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "Items [title=" + this.title + ", itemList=" + this.itemList + "]";
	}

}
