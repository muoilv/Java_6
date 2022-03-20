package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@NamedQuery(name="BillDetail.findAll", query="SELECT b FROM BillDetail b")
public class BillDetail implements Serializable {
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

	@Column(name="status_bill")
	Boolean statusBill;

	//bi-directional many-to-one association to Bill

	@ManyToOne 
	@JoinColumn(name="id_bill")
	Bill bill;

	//bi-directional many-to-one association to Product

	@ManyToOne 
	@JoinColumn(name="id_product")
	Product product;

	@Override
	public String toString() {
		return "BillDetail [id=" + id + ", intoMoney=" + intoMoney + ", nameProduct=" + nameProduct + ", priceProduct="
				+ priceProduct + ", quantity=" + quantity + ", statusBill=" + statusBill + "]";
	}

	

}