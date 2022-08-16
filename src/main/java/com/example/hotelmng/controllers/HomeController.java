package com.example.hotelmng.controllers;

import com.example.hotelmng.beans.model.Role;
import com.example.hotelmng.beans.model.Sector;
import com.example.hotelmng.beans.model.User;
import com.example.hotelmng.beans.service.RoleService;
import com.example.hotelmng.beans.service.SectorService;
import com.example.hotelmng.beans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SectorService sectorService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String home(){
        return "redirect:/tohome";
    }

    @GetMapping("/tohome")
    public String homePage(Authentication authentication){
        String userName = authentication.getName();
        User user = userService.findByUsername(userName);

        if(user.getRoleId().getName().equalsIgnoreCase("Admin")){
            return "redirect:/adminpanel";
        }else if (user.getRoleId().getName().equalsIgnoreCase("Manager")){
            return "redirect:/managerpanel";
        }else if (user.getRoleId().getName().equalsIgnoreCase("Employee")){
            return "redirect:/employeepanel";
        }
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginPage(){
        User user = userService.findByUsername("Admin");
        Role role = roleService.findById(3);
        Sector sector = sectorService.findById(4);
        if(user == null){
            User newAdmin = new User();
            newAdmin.setName("Admin");
            newAdmin.setUsername("Admin");
            newAdmin.setSurname("Admin");
            newAdmin.setPassword(bCryptPasswordEncoder.encode("Admin"));
            newAdmin.setRoleId(role);
            newAdmin.setSectorId(sector);
            userService.saveUser(newAdmin);
        }
        return "login";
    }
}
