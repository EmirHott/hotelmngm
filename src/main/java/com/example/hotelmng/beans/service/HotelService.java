package com.example.hotelmng.beans.service;

import com.example.hotelmng.beans.model.Hotel;
import com.example.hotelmng.beans.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }
    public void removeHotel(Hotel hotel){
        hotelRepository.delete(hotel);
    }
    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }
    public Hotel findById (Integer hotelId){
        return hotelRepository.getReferenceById(hotelId);

    }
    public Hotel findByName(String hotelName){
        return hotelRepository.findByName(hotelName);
    }
}
