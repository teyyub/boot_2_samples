package com.boot2.jpacrud.repository;

import com.boot2.jpacrud.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
    Optional<Tutorial> findByDescription(String description);
}