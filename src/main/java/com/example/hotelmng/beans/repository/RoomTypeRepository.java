package com.example.hotelmng.beans.repository;

import com.example.hotelmng.beans.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType,Integer> {
}
