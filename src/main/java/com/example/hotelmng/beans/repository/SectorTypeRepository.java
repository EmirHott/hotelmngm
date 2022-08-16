package com.example.hotelmng.beans.repository;

import com.example.hotelmng.beans.model.SectorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorTypeRepository extends JpaRepository<SectorType,Integer> {

    SectorType findByName (String sectorTypeName);
}
