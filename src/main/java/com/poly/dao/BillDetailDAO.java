package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Bill;
import com.poly.entity.BillDetail;
import com.poly.entity.Category;
import com.poly.entity.Report;

public interface BillDetailDAO extends JpaRepository<BillDetail, Integer> {

	List<BillDetail> findByBill(Bill bill);
	@Query(value = "SELECT SUM(into_money) AS SLPro FROM BillDetail", nativeQuery = true)
	Double sumMoney();
	
	List<BillDetail> findAllByProductCategory(Category a);
	
	@Query(value = "Select new Report(d.product.category, sum(d.intoMoney) , sum(d.quantity)) FROM BillDetail d GROUP BY d.product.category")
	List<Report> getInventoryByCategory();
}
