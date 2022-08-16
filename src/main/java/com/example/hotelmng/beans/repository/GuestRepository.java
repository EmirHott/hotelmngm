package com.example.hotelmng.beans.repository;

import com.example.hotelmng.beans.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Integer> {
}
