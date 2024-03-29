package com.example.ABC.Laboratories.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ABC.Laboratories.Model.appoinment;

public interface appoinmentRepo extends JpaRepository<appoinment, Long>{
    @Query(value = "SELECT * FROM appoinment WHERE c_name = :CName", nativeQuery = true)
    List<appoinment> findByCounsultName(@Param("CName") String CName);
}
