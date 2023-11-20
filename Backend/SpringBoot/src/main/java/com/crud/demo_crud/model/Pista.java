package com.crud.demo_crud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pista")
public class Pista {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "pista_id")
	private String pista_id;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String image;

	@Column(name = "type")
	private String type;

	@Column(name = "is_reserved")
	private Boolean is_reserved;

	public Pista() {

	}

	public Pista(String pista_id, String description, String image, String type, Boolean is_reserved) {
		this.pista_id = pista_id;
		this.description = description;
		this.image = image;
		this.type = type;
		this.is_reserved = is_reserved;
	}

	public long getId() {
		return id;
	}

	public String getPista_id() {
		return pista_id;
	}

	public void setPista_id(String pista_id) {
		this.pista_id = pista_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

		public Boolean getIs_reserved() {
		return is_reserved;
	}

	public void setIs_reserved(Boolean is_reserved) {
		this.is_reserved = is_reserved;
	}

	// @Override
	// public String toString() {
	// 	return "Pista [id=" + id + ", pista_id=" + pista_id + ", desc=" + description + ", image=" + image + ",type=" + type + "]";
	// }
}