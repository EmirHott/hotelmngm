package com.example.hotelmng.controllers;

import com.example.hotelmng.beans.model.Role;
import com.example.hotelmng.beans.model.Sector;
import com.example.hotelmng.beans.model.User;
import com.example.hotelmng.beans.service.RoleService;
import com.example.hotelmng.beans.service.SectorService;
import com.example.hotelmng.beans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping("/adminpanel")
    public String adminPanel(Model model){

        List<User> usersList = userService.getAllUsers();
        model.addAttribute("usersList",usersList);


        return "adminpanel";
    }




    @PostMapping("/usersearch")
    public String searchUsersAdminForm (@RequestParam(name = "finduser") String findUser, Model model){
        User user = userService.findByUsername(findUser);
        List<User> users = userService.getAllUsers();
        model.addAttribute("usersList",users);
        model.addAttribute("findeduser",user);
        return "/adminpanel";
    }

    @PostMapping("/removeuser")
    public String removeUser(@RequestParam(name = "username") String username){
        User userToRemove = userService.findByUsername(username);
        userService.removeUser(userToRemove);
        return "redirect:/adminpanel";
    }



    @PostMapping("/edituser")
    public String editUser (@RequestParam(name = "userid") String userid,@RequestParam(name="username") String username
            ,@RequestParam(name = "name") String name,@RequestParam(name="privilegeradio") String privilegeid
            ,@RequestParam(name = "surname")String surname,@RequestParam(name = "password")String password
            ,@RequestParam(name = "sector")String sector){


        Integer privilegeId = Integer.parseInt(privilegeid);
        Role role = roleService.findById(privilegeId);
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

        return "redirect:/adminpanel";
    }

    @PostMapping("/adduser")
    public String addUser (@RequestParam(name = "userid") String userid,@RequestParam(name="username") String username
            ,@RequestParam(name = "name") String name,@RequestParam(name="privilegeradio") String role
            ,@RequestParam(name = "surname")String surname,@RequestParam(name = "password")String password
            ,@RequestParam(name = "sector")String sector){


        Integer roleId = Integer.parseInt(role);
        Role role1 = roleService.findById(roleId);
        Integer sectorId = Integer.parseInt(sector);
        Sector sector1 = sectorService.findById(sectorId);
        String hashedpw = bCryptPasswordEncoder.encode(password);

        User userToAdd = new User();
        userToAdd.setUsername(username);
        userToAdd.setName(name);
        userToAdd.setSurname(surname);
        userToAdd.setPassword(hashedpw);
        userToAdd.setRoleId(role1);
        userToAdd.setSectorId(sector1);
        userService.saveUser(userToAdd);
        return "redirect:/adminpanel";
    }
}
