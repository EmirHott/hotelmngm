package com.example.hotelmng.controllers;

import com.example.hotelmng.beans.model.Guest;
import com.example.hotelmng.beans.model.Reservation;
import com.example.hotelmng.beans.model.Room;
import com.example.hotelmng.beans.service.GuestService;
import com.example.hotelmng.beans.service.ReservationService;
import com.example.hotelmng.beans.service.RoomService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private GuestService guestService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/employeepanel")
    public String employeePanel(Model model){
        List<Guest> guests = guestService.getAllGuests();
        List<Room> roomList = roomService.getAllRooms();
        List<Reservation> reservationList = reservationService.getAllReservations();
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("guests",guests);
        model.addAttribute("roomList",roomList);

        return "employeepanel";

    }
    @GetMapping("/guests")
    public String guests (Model model){

        List<Guest> guestList = guestService.getAllGuests();
        model.addAttribute("guestList", guestList);



        return "guests";
    }

    @PostMapping("/reservationsearch")
    public String searchReservation (@RequestParam(name = "findreservation") String findReservation, Model model){
        Integer reservationToFind = Integer.parseInt(findReservation);

        Reservation reservation1 = reservationService.findById(reservationToFind);

        List<Guest> guests = guestService.getAllGuests();
        List<Reservation> reservationList = reservationService.getAllReservations();
        model.addAttribute("reservationList",reservationList);
        model.addAttribute("guests",guests);
        model.addAttribute("findedreservation",reservation1);
        return "/employeepanel";
    }

    @PostMapping("/guestsearch")
    public String searchGuest(@RequestParam(name = "findguest")String findGuest, Model model){
        Integer guestToFind = Integer.parseInt(findGuest);

        Guest guest =guestService.findById(guestToFind);

        List<Guest> guestList = guestService.getAllGuests();
        model.addAttribute("guestList", guestList);
        model.addAttribute("findedGuest", guest);

        return "/guests";
    }

    @PostMapping("/addguest")
    public String addGuest(@RequestParam(name = "name")String name,
                           @RequestParam(name = "surname")String surname, @RequestParam(name = "id")String identification){

        Guest guest = new Guest();
        guest.setName(name);
        guest.setSurname(surname);
        guest.setIdentification(identification);
        guestService.saveGuest(guest);
        return "redirect:/guests";
    }
    @PostMapping("/removeguest")
    public String removeGuest(@RequestParam(name = "guestid")String guestId){

        Integer guest = Integer.parseInt(guestId);
        Guest guest1 = guestService.findById(guest);
        guestService.removeGuest(guest1);

        return "redirect:/guests";
    }
    @PostMapping("/editguest")
    public String editGuest(@RequestParam(name = "guestid")String guestId,@RequestParam(name = "name")String name,
                            @RequestParam(name = "surname")String surname, @RequestParam(name = "id")String identification){

        Integer guest = Integer.parseInt(guestId);
        Guest guest1 = guestService.findById(guest);
        guest1.setName(name);
        guest1.setSurname(surname);
        guest1.setIdentification(identification);
        guestService.saveGuest(guest1);



        return "redirect:/guests";
    }



    @PostMapping("/addreservation")
    public String addReservation (@RequestParam(name = "reservationid") String reservationid,@RequestParam(name="guest") String guest
            ,@RequestParam(name = "guestplus") String guestplus,@RequestParam(name="room") String room
            ,@RequestParam(name = "from")String from,@RequestParam(name = "to")String to
            ,@RequestParam(name = "checkin")String checkin,@RequestParam(name = "checkout")String checkout) throws ParseException {

        Integer guestId = Integer.parseInt(guest);
        Guest guest1 = guestService.findById(guestId);
        Boolean checkIn = Boolean.valueOf(checkin);
        Boolean checkOut = Boolean.valueOf(checkout);
        LocalDate dateFrom = LocalDate.parse(from);
        LocalDate dateTo = LocalDate.parse(to);
        Integer guest2 = Integer.parseInt(guestplus);
        Guest guestForList =guestService.findById(guest2);
        List<Guest> guestPlusList = new ArrayList<>();
        guestPlusList.add(guestForList);
        Integer roomId = Integer.parseInt(room);
        Room room1 = roomService.findById(roomId);

        Reservation reservation =new Reservation();
        reservation.setGuestId(guest1);
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);
        reservation.setFromDate(dateFrom);
        reservation.setToDate(dateTo);
        reservation.setGuestsList(guestPlusList);
        reservation.setRoomId(room1);
        reservationService.saveReservation(reservation);

        return "redirect:/employeepanel";
    }
    @PostMapping("/removereservation")
    public String removeReservation(@RequestParam(name = "reservationid")String reservationId){

        Integer reservation = Integer.parseInt(reservationId);
        Reservation reservation1 = reservationService.findById(reservation);
        reservationService.removeReservation(reservation1);


        return "redirect:/employeepanel";
    }
    @PostMapping("/editreservation")
    public String editReservation (@RequestParam(name = "reservationid") String reservationid,@RequestParam(name="guest") String guest
            ,@RequestParam(name = "guestplus") String guestplus,@RequestParam(name="room") String room
            ,@RequestParam(name = "from")String from,@RequestParam(name = "to")String to
            ,@RequestParam(name = "checkin")String checkin,@RequestParam(name = "checkout")String checkout) throws ParseException {

        Integer reservation =Integer.parseInt(reservationid);
        Reservation reservationToEdit = reservationService.findById(reservation);
        Integer guestId = Integer.parseInt(guest);
        Guest guest1 = guestService.findById(guestId);
        Boolean checkIn = Boolean.valueOf(checkin);
        Boolean checkOut = Boolean.valueOf(checkout);
        LocalDate dateFrom = LocalDate.parse(from);
        LocalDate dateTo = LocalDate.parse(to);
        Integer guest2 = Integer.parseInt(guestplus);
        Guest guestForList =guestService.findById(guest2);
        List<Guest> guestPlusList = new ArrayList<>();
        guestPlusList.add(guestForList);
        Integer roomId = Integer.parseInt(room);
        Room room1 = roomService.findById(roomId);

        reservationToEdit.setGuestId(guest1);
        reservationToEdit.setCheckIn(checkIn);
        reservationToEdit.setCheckOut(checkOut);
        reservationToEdit.setFromDate(dateFrom);
        reservationToEdit.setToDate(dateTo);
        reservationToEdit.setGuestsList(guestPlusList);
        reservationToEdit.setRoomId(room1);
        reservationService.saveReservation(reservationToEdit);

        return "redirect:/employeepanel";
    }
}
