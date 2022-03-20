package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Bill;
import com.poly.entity.StatusObject;
import com.poly.entity.User;

public interface BillDAO extends JpaRepository<Bill, Integer> {
	List<Bill> findByUser(User user);
	List<Bill> findAllByUserNameUserLikeAndStatusObjectIdStatusLike(String name,String stt);
	List<Bill> findByStatusObject(StatusObject statusObject);
//	@Query(value = "SELECT * FROM Bill WHERE id_user = ?1  AND id_status  =?2", nativeQuery = true)
//	Page<Bill> findStt(String idus ,String itst ,Pageable pageable);
//					
	@Query(value = "SELECT COUNT(id_bill) AS SLPro FROM Bill", nativeQuery = true)
	Integer selectQuantity();
	
	List<Bill> findByOrderByTimeAddAsc();
	List<Bill> findByOrderByTimeAddDesc();
	
	List<Bill> findByStatusObjectOrderByTimeAddAsc(StatusObject stt);
	List<Bill> findByStatusObjectOrderByTimeAddDesc(StatusObject stt);
	
	List<Bill> findByUserAndStatusObjectOrderByTimeAddAsc(User user, StatusObject stt);
	List<Bill> findByUserAndStatusObjectOrderByTimeAddDesc(User user,StatusObject stt);
	
	List<Bill> findByUserOrderByTimeAddAsc(User user);
	List<Bill> findByUserOrderByTimeAddDesc(User user);
	
}
