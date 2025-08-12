package com.example.Sehedule.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Sehedule.Dtos.TaskDto;
import com.example.Sehedule.Entity.Area;
import com.example.Sehedule.Entity.Task;
@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{
	@Query("SELECT t FROM Task t WHERE t.sehduleDate BETWEEN :start AND :end")

     List<Task> findWeeklytask(@Param("start") LocalDate start,@Param("end")  LocalDate end);
     
	@Query("SELECT t.status, COUNT(t) FROM Task t WHERE t.sehduleDate BETWEEN :start AND :end GROUP BY t.status")

     List<Object[]> getWeeklytasltask(@Param("start") LocalDate start,@Param("end")  LocalDate end);
     
     Page<Task> findBySehduleDate(LocalDate sehduleDate,    Pageable pageable);
}
