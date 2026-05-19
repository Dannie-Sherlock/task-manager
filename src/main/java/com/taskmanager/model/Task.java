package com.taskmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "progress")
    private Integer progress = 0;

    @Column(name = "is_urgent")
    private Boolean isUrgent = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Boolean getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(Boolean urgent) {
        isUrgent = urgent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDueInOneWeek() {
        LocalDateTime now = LocalDateTime.now();
        return endTime.isAfter(now) && endTime.isBefore(now.plusWeeks(1));
    }

    public boolean isDueInThreeDays() {
        LocalDateTime now = LocalDateTime.now();
        return endTime.isAfter(now) && endTime.isBefore(now.plusDays(3));
    }

    public boolean isDueInOneDay() {
        LocalDateTime now = LocalDateTime.now();
        return endTime.isAfter(now) && endTime.isBefore(now.plusDays(1));
    }

    public String getDueStatus() {
        if (isDueInOneDay()) {
            return "due-in-one-day";
        } else if (isDueInThreeDays()) {
            return "due-in-three-days";
        } else if (isDueInOneWeek()) {
            return "due-in-one-week";
        }
        return "";
    }
}