package main.presentation;


import main.application.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute(getCounterValue());
        return "adminPage";
    }

    @PostMapping("/update")
    public String updateRate(@Valid ChangeRateForm changeRateForm, BindingResult res, Model model){
        model.addAttribute(getCounterValue());
        if(res.hasErrors()) {
            return "adminPage";
        }
        adminService.updateRate(changeRateForm.getFrom(),changeRateForm.getTo(),changeRateForm.getNewRate());
        model.addAttribute("Rate for "+changeRateForm.getFrom()+"/"+changeRateForm.getTo()+" has been changed");
        return "adminPage";
    }


    private int getCounterValue(){
        return adminService.getCounter();
    }
}
