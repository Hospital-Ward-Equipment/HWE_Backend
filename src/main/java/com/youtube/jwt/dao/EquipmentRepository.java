package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Hwe_equipments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Hwe_equipments,Integer> {
}
