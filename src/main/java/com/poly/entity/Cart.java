package com.poly.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name="Cart.findAll", query="SELECT c FROM Cart c")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cart")
	Integer idCart;

	@Column(name="time_add")
	@Temporal(TemporalType.DATE)
	private Date timeAdd;

	//bi-directional many-to-one association to StatusObject
	@ManyToOne 
	@JoinColumn(name="id_status")
	StatusObject statusObject;

	//bi-directional many-to-one association to User
	@ManyToOne 
	@JoinColumn(name="id_user")
	User user;

	//bi-directional many-to-one association to CartDetail
	@JsonIgnore
	@OneToMany(mappedBy="cart")
	List<CartDetail> cartDetails;


	public CartDetail addCartDetail(CartDetail cartDetail) {
		getCartDetails().add(cartDetail);
		cartDetail.setCart(this);

		return cartDetail;
	}

	public CartDetail removeCartDetail(CartDetail cartDetail) {
		getCartDetails().remove(cartDetail);
		cartDetail.setCart(null);

		return cartDetail;
	}

	@Override
	public String toString() {
		return "Cart [idCart=" + idCart + ", timeAdd=" + timeAdd + "]";
	}

}