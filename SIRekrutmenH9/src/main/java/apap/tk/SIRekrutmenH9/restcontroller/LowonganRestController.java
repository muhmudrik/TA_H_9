package apap.tk.SIRekrutmenH9.restcontroller;

import apap.tk.SIRekrutmenH9.model.CalonLowonganModel;
import apap.tk.SIRekrutmenH9.model.LowonganModel;
import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.service.JenisLowonganService;
import apap.tk.SIRekrutmenH9.service.LowonganRestService;
import apap.tk.SIRekrutmenH9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
public class LowonganRestController {

    @Autowired
    private LowonganRestService lowonganRestService;

    @Autowired
    private JenisLowonganService jenisLowonganService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/lowongan")
    private LowonganModel createLowongan(@Valid @RequestBody CalonLowonganModel calonLowongan, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        else {
            LowonganModel lowongan = new LowonganModel();
            String kodeLowongan = "";
            String posisi = calonLowongan.getPosisi().substring(0,2).toUpperCase();
            String divisi = calonLowongan.getDivisi().substring(0,2).toUpperCase();
            String angkaAcak = "";
            String jenisLowonganID = calonLowongan.getJenisLowongan().toString();

            String angka = "0123456789";
            Random r = new Random();

            for(int i = 0; i < 2; i++){
                angkaAcak += angka.charAt(r.nextInt(10));
            }

            kodeLowongan = divisi + "-" + posisi + "-" + jenisLowonganID + "-" + angkaAcak;

            lowongan.setDivisi(calonLowongan.getDivisi());
            lowongan.setPosisi(calonLowongan.getPosisi());
            lowongan.setKodeLowongan(kodeLowongan);
            lowongan.setJumlah(calonLowongan.getJumlah());
            lowongan.setJenisLowongan(jenisLowonganService.getById(calonLowongan.getJenisLowongan()));
            lowongan.setUser(userService.addUser2(calonLowongan.getUsername()));

            return lowonganRestService.createLowongan(lowongan);

        }
    }

}
