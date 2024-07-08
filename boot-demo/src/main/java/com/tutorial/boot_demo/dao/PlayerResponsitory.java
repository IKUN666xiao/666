package com.tutorial.boot_demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlayerResponsitory extends JpaRepository<Player,Long>
 {
 // List<Player>  findByEmail(String email);
}
