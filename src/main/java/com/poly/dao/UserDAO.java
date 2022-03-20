package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.User;


public interface UserDAO extends JpaRepository<User, Integer> {
	User findByIdUser(Integer idUser);
	
	User findByEmailUser(String emailUser);
	
	List<User> findAllByNameUserLike(String keyword);
	
	Page<User> findAllByNameUserLike(String keywords, Pageable pageable);
	
	Page<User> findAllByEmailUserLike(String keywords, Pageable pageable);
	
	@Query(value = "DELETE FROM Authorities WHERE id_user=?1 and id_role = ?2" ,nativeQuery = true)
	Integer delete(Integer idUser,Integer idRole);
	
	@Query(value = "INSERT INTO Authorities (id_user, id_role) VALUES (?1,?2)" ,nativeQuery = true)
	Integer insert(Integer idUser,Integer idRole);
}
