package com.example.hotelmng.beans.repository;

import com.example.hotelmng.beans.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector,Integer> {


}
