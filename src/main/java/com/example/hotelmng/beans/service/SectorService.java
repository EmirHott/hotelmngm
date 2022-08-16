package com.example.hotelmng.beans.service;

import com.example.hotelmng.beans.model.Sector;
import com.example.hotelmng.beans.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public Sector saveSector (Sector sector){
        return sectorRepository.save(sector);
    }
    public void removeSector (Sector sector){
        sectorRepository.delete(sector);
    }
    public List<Sector> getAllSectors (){
        return sectorRepository.findAll();
    }
    public Sector findById(Integer sectorId){
        return sectorRepository.getReferenceById(sectorId);
    }

}
