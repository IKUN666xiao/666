package com.tutorial.boot_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRespository extends JpaRepository<Student,Long>
{

    List<Student>  findByEmail(String email);
}
