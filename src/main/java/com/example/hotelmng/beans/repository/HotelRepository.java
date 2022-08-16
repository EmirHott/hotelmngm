package com.example.hotelmng.beans.repository;

import com.example.hotelmng.beans.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {

    Hotel findByName (String hotelName);
}
