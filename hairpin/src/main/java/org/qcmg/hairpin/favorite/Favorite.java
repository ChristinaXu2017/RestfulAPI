package org.qcmg.hairpin.favorite;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Favorite {
	
	@Id	 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order;
	
	//camel case the column name for findby method in repository
	//ok: "La360 findByPureNumber(Integer pureNumber);"  
	//error:  "La360 findByPure_number(Integer pure_number);"  
	@Column(name="pure_number")
	private int pureNumber;
	
	private int mirnaOrder;
	
	private String username;
	
	private String image;

	public Favorite(int order,  int pureNumber, int mirnaOrder, String username, String image) {
		super();
		this.order = order;
		this.pureNumber = pureNumber;
		this.mirnaOrder = mirnaOrder;
		this.username = username;
		this.image = image;
	}

	public int getPureNumber() {
		return pureNumber;
	}

	public void setPureNumber(int pureNumber) {
		this.pureNumber = pureNumber;
	}

	public int getMirnaOrder() {
		return mirnaOrder;
	}

  

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getOrder() {
		return order;
	}
	
	
	

}
