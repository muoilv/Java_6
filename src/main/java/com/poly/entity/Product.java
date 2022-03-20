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
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_product")
	Integer idProduct;

	@Column(name="case_diameter")
	Double caseDiameter;

	@Column(name="color_product")
	String colorProduct;

	@Column(name="description_product")
	String descriptionProduct;

	@Column(name="face_glasses")
	String faceGlasses;

	@Column(name="image_product")
	String imageProduct;

	@Column(name="name_product")
	String nameProduct;

	@Column(name="price_product")
	Double priceProduct;

	@Column(name="strap_product")
	String strapProduct;

	@Column(name="type_machine")
	String typeMachine;

	//bi-directional many-to-one association to BillDetail
	@JsonIgnore
	@OneToMany(mappedBy="product")
	List<BillDetail> billDetails;

	//bi-directional many-to-one association to CartDetail
	@JsonIgnore
	@OneToMany(mappedBy="product")
	List<CartDetail> cartDetails;

	//bi-directional many-to-one association to Category
	@ManyToOne 
	@JoinColumn(name="category_id")
	Category category;

	//bi-directional many-to-one association to Gender
	@ManyToOne 
	@JoinColumn(name="gender_id")
	Gender gender;

	//bi-directional many-to-one association to StatusObject
	@ManyToOne 
	@JoinColumn(name="status_id")
	StatusObject statusObject;

	public BillDetail addBillDetail(BillDetail billDetail) {
		getBillDetails().add(billDetail);
		billDetail.setProduct(this);

		return billDetail;
	}

	public BillDetail removeBillDetail(BillDetail billDetail) {
		getBillDetails().remove(billDetail);
		billDetail.setProduct(null);

		return billDetail;
	}

	
	public CartDetail addCartDetail(CartDetail cartDetail) {
		getCartDetails().add(cartDetail);
		cartDetail.setProduct(this);

		return cartDetail;
	}

	public CartDetail removeCartDetail(CartDetail cartDetail) {
		getCartDetails().remove(cartDetail);
		cartDetail.setProduct(null);

		return cartDetail;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", caseDiameter=" + caseDiameter + ", colorProduct=" + colorProduct
				+ ", descriptionProduct=" + descriptionProduct + ", faceGlasses=" + faceGlasses + ", imageProduct="
				+ imageProduct + ", nameProduct=" + nameProduct + ", priceProduct=" + priceProduct + ", strapProduct="
				+ strapProduct + ", typeMachine=" + typeMachine + "]";
	}


}