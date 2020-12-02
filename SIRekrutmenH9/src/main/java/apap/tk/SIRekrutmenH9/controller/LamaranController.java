package apap.tk.SIRekrutmenH9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tk.SIRekrutmenH9.model.LamaranModel;
import apap.tk.SIRekrutmenH9.service.LamaranService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/lamaran")
public class LamaranController {
    @Autowired
    private LamaranService lamaranService;

    @PostMapping(value="/daftar")
    public String addLamaran(@ModelAttribute LamaranModel lamaran,Model model) {
        lamaranService.addLamaran(lamaran);
        model.addAttribute("lamaran", lamaran);
        return "add-lowongan";
    }
    
}
