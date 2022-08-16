package com.example.hotelmng.controllers;

import com.example.hotelmng.beans.model.*;
import com.example.hotelmng.beans.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ManagerController {


    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private SectorTypeService sectorTypeService;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping("/managerpanel")
    public String managerPanle(Model model){

        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList",userList);

        return "managerpanel";
    }
    @PostMapping("/managerusersearch")
    public String searchUsersAdminForm (@RequestParam(name = "finduser") String findUser, Model model){
        User user = userService.findByUsername(findUser);
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList",userList);
        model.addAttribute("findeduser",user);
        return "/managerpanel";

    }
    @PostMapping("/manageredituser")
    public String editUser (@RequestParam(name = "userid") String userid,@RequestParam(name="username") String username
            ,@RequestParam(name = "name") String name,@RequestParam(name="privilegeradio") String rolename
            ,@RequestParam(name = "surname")String surname,@RequestParam(name = "password")String password
            ,@RequestParam(name = "sector")String sector){



        Role role = roleService.findByName(rolename);
        Integer userId = Integer.parseInt(userid);
        Integer sectorToFind = Integer.parseInt(sector);
        Sector sectorId = sectorService.findById(sectorToFind);


        User userToEdit = userService.findById(userId);
        userToEdit.setUsername(username);
        userToEdit.setName(name);
        userToEdit.setSurname(surname);
        userToEdit.setPassword(password);
        userToEdit.setRoleId(role);
        userToEdit.setSectorId(sectorId);
        userService.saveUser(userToEdit);

        return "redirect:/managerpanel";
    }

    @GetMapping("/sectors")
    public String sectors(Model model){

        List<Sector> sectorList = sectorService.getAllSectors();
        model.addAttribute("sectorList", sectorList);

        return "sectors";
    }
    @PostMapping("/sectorsearch")
    public String searchSector(@RequestParam(name = "findsector") String findSector, Model model){
        Integer sectorToGet = Integer.parseInt(findSector);
        Sector sector = sectorService.findById(sectorToGet);

        List<Sector> sectorList = sectorService.getAllSectors();
        model.addAttribute("sectorList", sectorList);
        model.addAttribute("findedsector",sector);

        return "/sectors";
    }
    @PostMapping("/addsector")
    public String addSector(@RequestParam(name = "sector")String sector, @RequestParam(name = "hotel")String hotel){

        SectorType sectorType = sectorTypeService.findByName(sector);
        Hotel hotelToAdd = hotelService.findByName(hotel);

        Sector sectorToAdd = new Sector();
        sectorToAdd.setTypeId(sectorType);
        sectorToAdd.setHotelId(hotelToAdd);
        sectorService.saveSector(sectorToAdd);
        return "/sectors";
    }

    @PostMapping("/removesector")
    public String removeSector (@RequestParam(name = "sectorid")String sectorid){
        Integer sectorId = Integer.parseInt(sectorid);
        Sector sector = sectorService.findById(sectorId);
        sectorService.removeSector(sector);


        return "/sectors";
    }
    @PostMapping("/editsector")
    public String editSector(@RequestParam(name = "sectorid")String sectorid,
                             @RequestParam(name = "sector")String sector, @RequestParam(name = "hotel")String hotel){
        SectorType sectorType = sectorTypeService.findByName(sector);
        Hotel hotelToEdit = hotelService.findByName(hotel);
        Integer sectorId = Integer.parseInt(sectorid);
        Sector sectorToEdit = sectorService.findById(sectorId);

        sectorToEdit.setHotelId(hotelToEdit);
        sectorToEdit.setTypeId(sectorType);
        sectorService.saveSector(sectorToEdit);

        return "/sectors";
    }

    @GetMapping("/rooms")
    public String rooms (Model model){

        List<Room> roomList = roomService.getAllRooms();
        model.addAttribute("roomList",roomList);
        return "rooms";
    }
    @PostMapping("/roomsearch")
    public String searchRoom(@RequestParam(name = "findroom")String findRoom,Model model){

        List<Room> roomList = roomService.getAllRooms();
        model.addAttribute("roomList",roomList);
        Integer roomToGet = Integer.parseInt(findRoom);
        Room room = roomService.findById(roomToGet);
        model.addAttribute("findedroom", room);

        return "/rooms";
    }
    @PostMapping("/removeroom")
    public String removeRoom (@RequestParam(name = "roomid")String roomId){
        Integer roomToGet = Integer.parseInt(roomId);
        Room room = roomService.findById(roomToGet);
        roomService.removeRoom(room);

        return "redirect:/rooms";
    }
    @PostMapping("/addroom")
    public String addRoom (@RequestParam(name = "number")String number,
                           @RequestParam(name = "type")String type,@RequestParam(name = "sector")String sector){

        Integer roomNumber =Integer.parseInt(number);
        Integer sectorId = Integer.parseInt(sector);
        Sector sectorToAdd = sectorService.findById(sectorId);
        Integer roomType = Integer.parseInt(type);
        RoomType roomTypeToAdd = roomTypeService.findById(roomType);

        Room roomToAdd = new Room();
        roomToAdd.setNumber(roomNumber);
        roomToAdd.setSectorId(sectorToAdd);
        roomToAdd.setTypeId(roomTypeToAdd);
        roomService.saveRoom(roomToAdd);

        return "redirect:/rooms";
    }
    @PostMapping("/editroom")
    public String editRoom (@RequestParam(name = "roomid")String roomid,@RequestParam(name = "number")String number,
                            @RequestParam(name = "type")String type,@RequestParam(name = "sector")String sector){

        Integer roomNumber =Integer.parseInt(number);
        Integer sectorId = Integer.parseInt(sector);
        Sector sectorToEdit = sectorService.findById(sectorId);
        Integer roomType = Integer.parseInt(type);
        RoomType roomTypeToEdit = roomTypeService.findById(roomType);


        Integer roomId = Integer.parseInt(roomid);
        Room roomToEdit = roomService.findById(roomId);
        roomToEdit.setNumber(roomNumber);
        roomToEdit.setSectorId(sectorToEdit);
        roomToEdit.setTypeId(roomTypeToEdit);
        roomService.saveRoom(roomToEdit);

        return "redirect:/rooms";
    }

    @GetMapping("/sectortype")
    public String sectorType(Model model){

        List<SectorType> sectorTypeList =sectorTypeService.getAllSectorTypes();
        model.addAttribute("sectorTypeList",sectorTypeList);

        return "sectortype";
    }
    @GetMapping("/sectortypesearch")
    public String sectorTypeSearch (@RequestParam(name = "findsectortype")String findSectorType,Model model){

        Integer sectorFind = Integer.parseInt(findSectorType);
        SectorType sectorType = sectorTypeService.findById(sectorFind);
        List<SectorType> sectorTypeList =sectorTypeService.getAllSectorTypes();
        model.addAttribute("sectorTypeList",sectorTypeList);
        model.addAttribute("findedsectortype",sectorType);
        return "sectortype";
    }
    @PostMapping("/addsectortype")
    public String addSectorType (@RequestParam(name = "name")String name){

        SectorType sectorTypeToAdd = new SectorType();
        sectorTypeToAdd.setName(name);
        sectorTypeService.saveSectorType(sectorTypeToAdd);

        return "redirect:/sectortype";
    }
    @PostMapping("/removesectortype")
    public String removeSectorType (@RequestParam(name = "sectortypeid")String id){
        Integer sectorTypeId = Integer.parseInt(id);
        SectorType sectorTypeToRemove = sectorTypeService.findById(sectorTypeId);
        sectorTypeService.removeSectorType(sectorTypeToRemove);

        return "redirect:/sectortype";
    }
    @PostMapping("/editsectortype")
    public String editSectorType(@RequestParam(name = "sectortypeid")String id,@RequestParam(name = "name")String name){

        Integer sectorTypeId = Integer.parseInt(id);
        SectorType sectorTypeToEdit = sectorTypeService.findById(sectorTypeId);
        sectorTypeToEdit.setName(name);
        sectorTypeService.saveSectorType(sectorTypeToEdit);


        return "redirect:/sectortype";
    }

    @GetMapping("/roomtype")
    public String roomType(Model model){

        List<RoomType> roomTypeList = roomTypeService.getAllRoomTypes();
        model.addAttribute("roomTypeList",roomTypeList);
        return "roomtype";
    }
    @PostMapping("/roomtypesearch")
    public String roomTypeSearch (@RequestParam(name = "findroomtype")String findRoomType, Model model){
        Integer roomTypeFind = Integer.parseInt(findRoomType);
        RoomType roomType = roomTypeService.findById(roomTypeFind);
        List<RoomType> roomTypeList = roomTypeService.getAllRoomTypes();
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("findedRoomType",roomType);
        return "roomtype";
    }
    @PostMapping("/removeroomtype")
    public String removeRoomType (@RequestParam(name = "id")String id){

        Integer roomTypeId =Integer.parseInt(id);
        RoomType roomType = roomTypeService.findById(roomTypeId);
        roomTypeService.removeRoomType(roomType);

        return "redirect:/roomtype";
    }
    @PostMapping("/addroomtype")
    public String addRoomType (@RequestParam(name = "name")String name){

        RoomType roomType =  new RoomType();
        roomType.setType(name);
        roomTypeService.saveRoomType(roomType);

        return "redirect:/roomtype";
    }
    @PostMapping("/editroomtype")
    public String editRoomType(@RequestParam(name = "id")String id,@RequestParam(name = "name")String name){

        Integer roomTypeId = Integer.parseInt(id);
        RoomType roomType = roomTypeService.findById(roomTypeId);
        roomType.setType(name);
        roomTypeService.saveRoomType(roomType);

        return "redirect:/roomtype";
    }
}
