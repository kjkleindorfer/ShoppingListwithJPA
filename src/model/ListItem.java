package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class ListItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "STORE")
	private String store;
	
	@Column(name="ITEM")
	private String item;
	
	//default no arg constructor
	public ListItem() {
		
	}
	//helper constructor
	public ListItem(String store, String item){
		this.store = store;
		this.item = item;
	}
	public int getId() {
		return id;
	}
	public String getItem() {
		return item;
	}
	public String getStore() {
		return store;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public void setStore(String store) {
		this.store = store;
	}
	//helper method
	@Override
	public String toString(){
		return this.store + " --- " + this.item;
	}
}