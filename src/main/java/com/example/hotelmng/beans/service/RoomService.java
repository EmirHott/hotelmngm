package com.example.hotelmng.beans.service;

import com.example.hotelmng.beans.model.Room;
import com.example.hotelmng.beans.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {


    @Autowired
    private RoomRepository roomRepository;


    public Room saveRoom(Room room){
        return roomRepository.save(room);
    }
    public void removeRoom(Room room){
        roomRepository.delete(room);
    }
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
    public Room findById (Integer roomId){
        return roomRepository.getReferenceById(roomId);

    }
}
