package com.vkopendoh.orgapp.repository;

import com.vkopendoh.orgapp.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudPositionRepository extends JpaRepository<Position,Integer> {
}
