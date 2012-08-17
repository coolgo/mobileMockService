package models;

import javax.persistence.Entity;

@Entity
public class Parent extends Member {

	public Parent() {
	}

	@Override
	public String getNameTileOfMember() {
		return "家长";
	}
}
