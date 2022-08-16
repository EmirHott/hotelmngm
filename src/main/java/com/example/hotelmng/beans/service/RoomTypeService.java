package com.example.hotelmng.beans.service;

import com.example.hotelmng.beans.model.RoomType;
import com.example.hotelmng.beans.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;


    public RoomType saveRoomType (RoomType roomType){
        return roomTypeRepository.save(roomType);
    }
    public void removeRoomType (RoomType roomType){
         roomTypeRepository.delete(roomType);
    }
    public List<RoomType> getAllRoomTypes (){
        return roomTypeRepository.findAll();
    }
    public RoomType findById (Integer roomTypeId){
        return roomTypeRepository.getReferenceById(roomTypeId);
    }
}
