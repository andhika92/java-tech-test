package com.techtest.consumerbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techtest.consumerbackend.model.Wilayah;

public interface WilayahRepository extends JpaRepository<Wilayah, String> {

	@Query(value = "SELECT * FROM tbm_wilayah WHERE LENGTH(kode) = 2 ORDER BY nama", nativeQuery = true)
	List<Wilayah> findProvinces();

	@Query(value = "SELECT * FROM tbm_wilayah WHERE LENGTH(kode) = 5 AND kode ILIKE :province% ORDER BY nama", nativeQuery = true)
	List<Wilayah> findRegenciesByProvince(@Param("province") String province);

}