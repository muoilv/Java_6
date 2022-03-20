package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name="CartDetail.findAll", query="SELECT c FROM CartDetail c")
public class CartDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name="into_money")
	Double intoMoney;

	@Column(name="name_product")
	String nameProduct;

	@Column(name="price_product")
	Double priceProduct;

	Integer quantity;

	@Column(name="status_cart")
	Boolean statusCart;

	//bi-directional many-to-one association to Cart
	@ManyToOne 
	@JoinColumn(name="id_cart")
	Cart cart;

	//bi-directional many-to-one association to Product
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="id_product")
	Product product;

	@Override
	public String toString() {
		return "CartDetail [id=" + id + ", intoMoney=" + intoMoney + ", nameProduct=" + nameProduct + ", priceProduct="
				+ priceProduct + ", quantity=" + quantity + ", statusCart=" + statusCart + "]";
	}

	

}