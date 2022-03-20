package com.poly.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_category")
	Integer idCategory;

	@Column(name="name_category")
	String nameCategory;

	@Column(name="note_category")
	String noteCategory;

	@Column(name="status_category")
	Boolean statusCategory;

	//bi-directional many-to-one association to Product
	@JsonIgnore
	@OneToMany(mappedBy="category")
	List<Product> products;

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);
		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", nameCategory=" + nameCategory + ", noteCategory="
				+ noteCategory + ", statusCategory=" + statusCategory + "]";
	}
	
	

}