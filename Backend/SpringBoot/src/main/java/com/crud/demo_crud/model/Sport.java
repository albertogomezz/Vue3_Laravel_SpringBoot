package com.crud.demo_crud.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "sport")
public class Sport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "sport_id")
	private String sport_id;

	@Column(name = "sport_name")
	private String sport_name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private String price;

	@Column(name = "image")
	private String image;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {
		CascadeType.PERSIST,
		CascadeType.MERGE
	}, mappedBy = "sports")
	@JsonIgnore
	private Set<Pista> pista = new HashSet<>();


	public Sport() {

	}

	public Sport(String sport_id, String sport_name, String description, String price, String image) {
		this.sport_id = sport_id;
		this.sport_name = sport_name;
		this.description = description;
		this.price = price;
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public String getSport_id() {
		return sport_id;
	}

	public void setSport_id(String sport_id) {
		this.sport_id = sport_id;
	}

	public String getSport_name() {
		return sport_name;
	}

	public void setSport_name(String sport_name) {
		this.sport_name = sport_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Pista [id=" + id + ", sport_id=" + sport_id + ",name_sport=" + sport_name + ", desc=" + description + ", price=" + price + ",image=" + image + "]";
	}
}