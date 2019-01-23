package com.mercury.SpringBootRESTDemo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "msi_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_PRODUCT_SEQ_GEN")
	@SequenceGenerator(name = "MSI_PRODUCT_SEQ_GEN", sequenceName = "MSI_PRODUCT_SEQ", allocationSize = 1)
	private int id;
	@Column
	@NotEmpty
	private String name;
	@Column
	@NotEmpty
	private String brand;
	@Column
	@NotNull
	@Positive
	private int price;
	@Column
	@NotNull
	@PositiveOrZero
	private int stock;
	@Column
	private String image;

	public Product() {
		super();
	}

	public Product(String name, String brand, int price, int stock, String image) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + ", stock=" + stock
				+ ", image=" + image + "]";
	}

}
