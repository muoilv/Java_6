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
@NamedQuery(name="Gender.findAll", query="SELECT g FROM Gender g")
public class Gender implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_gender")
	String idGender;

	@Column(name="name_gender")
	String nameGender;

	@Column(name="note_gender")
	String noteGender;

	@Column(name="status_gender")
	Boolean statusGender;

	//bi-directional many-to-one association to Product
	@JsonIgnore
	@OneToMany(mappedBy="gender")
	List<Product> products;

	//bi-directional many-to-one association to User
	@JsonIgnore
	@OneToMany(mappedBy="gender")
	List<User> users;


	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setGender(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setGender(null);

		return product;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setGender(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setGender(null);

		return user;
	}

	@Override
	public String toString() {
		return "Gender [idGender=" + idGender + ", nameGender=" + nameGender + ", noteGender=" + noteGender
				+ ", statusGender=" + statusGender + "]";
	}
	
	

}