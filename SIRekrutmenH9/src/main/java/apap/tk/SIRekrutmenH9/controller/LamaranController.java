package apap.tk.SIRekrutmenH9.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @PostMapping(value = "/daftar/{idLowongan}")
    public String addLamaran(@PathVariable(name = "idLowongan") Long idLowongan, Principal principal, Model model) {
        System.out.println(principal.getName());

        PelamarModel pelamar = userService.getUserByUsername(principal.getName()).getPelamar();
        LowonganModel lowongan = lowonganService.getLowonganById(idLowongan);
        // System.out.println("ID PELAMAR: " + pelamar.getId());
        LamaranModel lamaran = new LamaranModel();
        lamaran.setLowonganModel(lowongan);
        lamaran.setPelamarModel(pelamar);
        lamaran.setStatus(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateWithoutTime = new Date();
        try {
            dateWithoutTime = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lamaran.setTanggal_diterima(dateWithoutTime);
        lamaranService.addLamaran(lamaran);
        model.addAttribute("lowongan", lamaran.getLowonganModel().getKodeLowongan());
        return "add-lamaran";
    }
    
}
