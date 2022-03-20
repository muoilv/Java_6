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
@NamedQuery(name="StatusObject.findAll", query="SELECT s FROM StatusObject s")
public class StatusObject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_status")
	String idStatus;

	@Column(name="description_status")
	String descriptionStatus;

	@Column(name="name_status")
	String nameStatus;

	//bi-directional many-to-one association to Bill
	@JsonIgnore
	@OneToMany(mappedBy="statusObject")
	List<Bill> bills;

	//bi-directional many-to-one association to Cart
	@JsonIgnore
	@OneToMany(mappedBy="statusObject")
	List<Cart> carts;

	//bi-directional many-to-one association to Product
	@JsonIgnore
	@OneToMany(mappedBy="statusObject")
	List<Product> products;

	
	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setStatusObject(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setStatusObject(null);

		return bill;
	}

	
	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setStatusObject(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setStatusObject(null);

		return cart;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setStatusObject(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setStatusObject(null);

		return product;
	}

	@Override
	public String toString() {
		return "StatusObject [idStatus=" + idStatus + ", descriptionStatus=" + descriptionStatus + ", nameStatus="
				+ nameStatus + "]";
	}
	
	

}