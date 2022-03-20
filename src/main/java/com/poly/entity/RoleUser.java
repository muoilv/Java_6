package com.poly.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name="RoleUser.findAll", query="SELECT r FROM RoleUser r")
public class RoleUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_role")
	Integer idRole;

	@Column(name="name_role")
	String nameRole;


	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roleUsers" , fetch = FetchType.EAGER)
	Set<User> users;


	@Override
	public String toString() {
		return "RoleUser [idRole=" + idRole + ", nameRole=" + nameRole + "]";
	}

	
}