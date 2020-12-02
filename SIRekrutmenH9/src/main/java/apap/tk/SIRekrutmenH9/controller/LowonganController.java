package apap.tk.SIRekrutmenH9.controller;

import apap.tk.SIRekrutmenH9.model.LowonganModel;
import apap.tk.SIRekrutmenH9.service.LowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @RequestMapping("/lowongan/daftarLowongan")
    public String daftarLowongan(Model model){
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();

        model.addAttribute("listLowongan", listLowongan);

        //return View Template
        return "daftar-lowongan";
    }
}
