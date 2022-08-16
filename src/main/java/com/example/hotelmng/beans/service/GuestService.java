package com.example.hotelmng.beans.service;

import com.example.hotelmng.beans.model.Guest;
import com.example.hotelmng.beans.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public Guest saveGuest (Guest guest){
        return guestRepository.save(guest);
    }
    public void removeGuest (Guest guest){
        guestRepository.delete(guest);
    }
    public List<Guest> getAllGuests (){
        return guestRepository.findAll();
    }
    public Guest findById (Integer guestId){
        return guestRepository.getReferenceById(guestId);
    }
}
