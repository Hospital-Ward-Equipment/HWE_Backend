package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Hwe_equipments;
import com.youtube.jwt.entity.Hwe_ward_equipments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardEquipmentRepository extends JpaRepository<Hwe_ward_equipments,Integer> {
}
