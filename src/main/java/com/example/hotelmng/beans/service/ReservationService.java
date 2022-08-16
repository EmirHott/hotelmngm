package com.example.hotelmng.beans.service;

import com.example.hotelmng.beans.model.Reservation;
import com.example.hotelmng.beans.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation saveReservation (Reservation reservation){
        return reservationRepository.save(reservation);
    }
    public void removeReservation (Reservation reservation){
        reservationRepository.delete(reservation);
    }
    public List<Reservation> getAllReservations (){
        return reservationRepository.findAll();
    }
    public Reservation findById (Integer reservationId){
        return reservationRepository.getReferenceById(reservationId);
    }
}
