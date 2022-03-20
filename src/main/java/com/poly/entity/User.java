package com.poly.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_user")
	Integer idUser;

	@Column(name="address_user")
	@NotBlank(message = "Không để trống địa chỉ")
	String addressUser;

	@Column(name="avatar_user")
	String avatarUser;

	@Column(name="email_user")
	@NotBlank(message = "Không để trống email")
	@Email(message = "Không đúng định dạng email")
	String emailUser;

	@Column(name="name_user")
	@NotBlank(message = "Không để trống name")
	String nameUser;

	@Column(name="note_user")
	String noteUser;

	@Column(name="password_user")
	@NotBlank(message = "Không để trống password")
	String passwordUser;

	@Column(name="phone_user")
	@Length(min = 10,max = 14, message = "Số điện thoại từ 10 - 14 số")
	@PositiveOrZero(message = "Số điện thoại không âm")
	String phoneUser;

	@Column(name="status_user")
	Boolean statusUser;

	//bi-directional many-to-many association to RoleUser
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="Authorities"
		, joinColumns={
			@JoinColumn(name="id_user")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_role" )
			}
		)
	Set<RoleUser> roleUsers;

	
	
	//bi-directional many-to-one association to Bill
	@JsonIgnore
	@OneToMany(mappedBy="user")
	List<Bill> bills;

	//bi-directional many-to-one association to Cart
	@JsonIgnore
	@OneToMany(mappedBy="user")
	List<Cart> carts;

	//bi-directional many-to-one association to Gender
	@ManyToOne
	@JoinColumn(name="gender_id")
	Gender gender;


	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setUser(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setUser(null);

		return bill;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setUser(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setUser(null);

		return cart;
	}
	
	public Set<RoleUser> getRoleUsers() {
		return this.roleUsers;
	}

	public void setRoleUsers(Set<RoleUser> roleUsers) {
		this.roleUsers = roleUsers;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", addressUser=" + addressUser + ", avatarUser=" + avatarUser + ", emailUser="
				+ emailUser + ", nameUser=" + nameUser + ", noteUser=" + noteUser + ", passwordUser=" + passwordUser
				+ ", phoneUser=" + phoneUser + ", statusUser=" + statusUser  +
				 "]";
	}

	
	

}