package com.example.Sehedule.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Sehedule.Entity.Area;
@Repository
public interface AreaRepo extends JpaRepository<Area ,Long> {

}
