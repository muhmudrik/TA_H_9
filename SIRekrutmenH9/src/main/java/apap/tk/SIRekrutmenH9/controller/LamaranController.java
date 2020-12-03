package apap.tk.SIRekrutmenH9.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tk.SIRekrutmenH9.model.LamaranModel;
import apap.tk.SIRekrutmenH9.model.LowonganModel;
import apap.tk.SIRekrutmenH9.model.PelamarModel;
import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.service.LamaranService;
import apap.tk.SIRekrutmenH9.service.LowonganService;
import apap.tk.SIRekrutmenH9.service.PelamarService;
import apap.tk.SIRekrutmenH9.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/lamaran")
public class LamaranController {
    @Autowired
    private LamaranService lamaranService;

    @Autowired
    private UserService userService;

    @Autowired
    private LowonganService lowonganService;

    @Autowired
    private PelamarService pelamarService;

    @PostMapping(value="/daftar/{idLowongan}")
    public String addLamaran(
        @PathVariable(name = "idLowongan") Integer idLowongan,
        Principal principal,
        Model model
    ) {
        System.out.println(principal.getName());

        String username = userService.getUserByUsername(principal.getName()).getUsername();
        PelamarModel pelamar = pelamarService.getPelamarByUsername(username);
        LowonganModel lowongan = lowonganService.getLowonganById(idLowongan);
        System.out.println("ID PELAMAR: " + pelamar.getId());
        LamaranModel lamaran = new LamaranModel();
        lamaran.setLowonganModel(lowongan);
        return "home";
    }
    
}
