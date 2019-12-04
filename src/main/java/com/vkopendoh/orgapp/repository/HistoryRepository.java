package com.vkopendoh.orgapp.repository;

import com.vkopendoh.orgapp.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Integer> {
}
