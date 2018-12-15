package main.presentation;


import main.application.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;



    @GetMapping("/admin")
    public String adminPage(ChangeRateForm changeRateForm, Model model){
        Integer counterValue = adminService.getCounter();
        model.addAttribute(counterValue);
        return "adminPage";
    }

    @PostMapping("/update")
    public String updateRate(ChangeRateForm changeRateForm){
        adminService.updateRate(changeRateForm.getFrom(),changeRateForm.getTo(),changeRateForm.getNewRate());
        return "redirect:admin";
    }
}
