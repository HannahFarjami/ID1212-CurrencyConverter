package main.presentation;

import main.application.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ConversionController {

    @Autowired
    CurrencyConversionService currencyConversionService;

    @GetMapping("/")
    public String index(ConversionForm conversionForm){
        return "index";
    }


    @PostMapping("/result")
    public String result(@Valid ConversionForm conversionForm, BindingResult res, Model model){
        if(res.hasErrors())
            return "index";
        Double result = currencyConversionService.convert(conversionForm.getFrom(),conversionForm.getTo(),conversionForm.getAmount());
        model.addAttribute("double",result);
        return "result";
    }
}
