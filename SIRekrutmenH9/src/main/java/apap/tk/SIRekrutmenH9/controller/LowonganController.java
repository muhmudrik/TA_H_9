package apap.tk.SIRekrutmenH9.controller;

import apap.tk.SIRekrutmenH9.model.LowonganModel;
import apap.tk.SIRekrutmenH9.service.LowonganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @RequestMapping(value = "/lowongan/ubahLowongan/{id}", method = RequestMethod.GET)
    public String ubahLowonganForm(Model model, @PathVariable(value = "id") Integer id) {
        LowonganModel lowongan = lowonganService.getLowonganById(id).get();
        model.addAttribute("lowongan", lowongan);
        return "form-ubah-lowongan";
    }

    @RequestMapping(value = "/lowongan/ubahLowongan/{id}", method = RequestMethod.POST)
    public String ubahLowonganSubmit(
            @PathVariable(value = "id") Integer id,
            @ModelAttribute LowonganModel lowongan,
            Model model
    ) {
        LowonganModel targetLowongan = lowonganService.ubahLowongan(lowongan);
        model.addAttribute("lowongan", targetLowongan);

        return "ubah-lowongan";
    }
}
