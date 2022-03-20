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
@NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_bill")
	Integer idBill;

	@Column(name="address_bill")
	String addressBill;

	@Column(name="time_add")
	@Temporal(TemporalType.DATE)
	Date timeAdd;

	@Column(name="total_money")
	Double totalMoney;

	//bi-directional many-to-one association to StatusObject
	
	@ManyToOne 
	@JoinColumn(name="id_status")
	StatusObject statusObject;

	//bi-directional many-to-one association to User
	@ManyToOne 
	@JoinColumn(name="id_user")
	User user;

	//bi-directional many-to-one association to BillDetail
	@JsonIgnore
	@OneToMany(mappedBy="bill") 
	List<BillDetail> billDetails;

	
	public BillDetail addBillDetail(BillDetail billDetail) {
		getBillDetails().add(billDetail);
		billDetail.setBill(this);

		return billDetail;
	}

	public BillDetail removeBillDetail(BillDetail billDetail) {
		getBillDetails().remove(billDetail);
		billDetail.setBill(null);

		return billDetail;
	}

	@Override
	public String toString() {
		return "Bill [idBill=" + idBill + ", addressBill=" + addressBill + ", timeAdd=" + timeAdd + ", totalMoney="
				+ totalMoney + "]";
	}



}