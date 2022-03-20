package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Total implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	Integer totalPro ;
	Integer totalBill;
	Double totalMoney;
	
}
