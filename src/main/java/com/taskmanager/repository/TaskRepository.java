package com.taskmanager.repository;

import com.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.endTime >= :now ORDER BY t.isUrgent DESC, t.endTime ASC, t.progress ASC")
    List<Task> findUpcomingTasks(LocalDateTime now);

    @Query("SELECT t FROM Task t WHERE t.endTime < :now OR t.progress >= 100 ORDER BY t.endTime DESC")
    List<Task> findCompletedTasks(LocalDateTime now);

    List<Task> findAllByOrderByEndTimeAsc();
}