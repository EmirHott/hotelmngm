package com.example.hotelmng.beans.service;

import com.example.hotelmng.beans.model.SectorType;
import com.example.hotelmng.beans.repository.SectorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorTypeService {

    @Autowired
    private SectorTypeRepository sectorTypeRepository;


    public SectorType saveSectorType(SectorType sectorType){
        return sectorTypeRepository.save(sectorType);
    }
    public void removeSectorType (SectorType sectorType){
        sectorTypeRepository.delete(sectorType);
    }
    public List<SectorType> getAllSectorTypes (){
        return sectorTypeRepository.findAll();
    }
    public SectorType findById (Integer sectorTypeId){
        return sectorTypeRepository.getReferenceById(sectorTypeId);
    }
    public SectorType findByName (String sectorTypeName){
        return sectorTypeRepository.findByName(sectorTypeName);
    }
}
