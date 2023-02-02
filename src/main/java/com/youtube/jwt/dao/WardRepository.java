package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Hwe_equipments;
import com.youtube.jwt.entity.Hwe_wards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Hwe_wards,Integer> {
}
