package com.vkopendoh.orgapp.repository;

import com.vkopendoh.orgapp.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CrudPositionRepository extends JpaRepository<Position, Integer> {
}
