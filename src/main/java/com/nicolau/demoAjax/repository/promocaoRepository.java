package com.nicolau.demoAjax.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.nicolau.demoAjax.domain.promocao;

public interface promocaoRepository extends JpaRepository<promocao, Long> {
	
	@Query("select p from promocao p where p.site like :site")
	Page<promocao> findBySite(@Param("site") String site,PageRequest pageRequest);
	
	@Query("select distinct p.site from promocao p where p.site like %:site%")
	List<String> findSiteByTermo(@Param("site") String site);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("update promocao p set p.likes = p.likes + 1 where p.id = :id")
	void updateSomarLikes(@Param("id") Long id);
	
	@Query("select p.likes from promocao p where p.id= :id") // o nome da tabela do geito q se criou a class
	int findLikesById(@Param("id") Long id);
	

}
