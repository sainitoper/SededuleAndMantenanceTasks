package com.example.Sehedule.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Sehedule.Entity.Area;
@Repository
public interface AreaRepo extends JpaRepository<Area ,Long> {
Page<Area> findByFloorAndWing(String floor,String wing,Pageable pageable);
Page<Area> findByFloor(String floor,Pageable pageable);
Page<Area> findByWing(String wing,    Pageable pageable);

}
