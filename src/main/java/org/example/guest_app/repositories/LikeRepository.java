package org.example.guest_app.repositories;

import org.example.guest_app.entities.LikeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeTable,Long> {
}
