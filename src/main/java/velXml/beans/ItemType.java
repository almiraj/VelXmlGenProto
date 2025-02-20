package velXml.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class ItemType {

	private String attr = "";

	private String value = "";

	@XmlAttribute
	public String getAttr() {
		return this.attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	@XmlValue
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ItemType [attr=" + this.attr + ", value=" + this.value + "]";
	}

}
