package apap.tk.SIRekrutmenH9.restcontroller;

import apap.tk.SIRekrutmenH9.model.LowonganModel;
import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.rest.LowonganDetail;
import apap.tk.SIRekrutmenH9.service.JenisLowonganService;
import apap.tk.SIRekrutmenH9.service.LowonganRestService;
import apap.tk.SIRekrutmenH9.service.LowonganService;
import apap.tk.SIRekrutmenH9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
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

    @Autowired
    private LowonganService lowonganService;

    @PostMapping(value = "/lowongan/add")
    private BaseResponse<LowonganModel> createLowongan(@Valid @RequestBody LowonganDetail calonLowongan, BindingResult bindingResult){
        BaseResponse<LowonganModel> response = new BaseResponse<LowonganModel>();
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
            List<LowonganModel> listLowongan = lowonganService.getLowonganList();

            for(LowonganModel lowonganList : listLowongan){
                if(kodeLowongan != "" && kodeLowongan != lowonganList.getKodeLowongan()){
                    lowongan.setKodeLowongan(kodeLowongan);
                }
                else{
                    String angka = "0123456789";
                    Random r = new Random();

                    for(int i = 0; i < 2; i++){
                        angkaAcak += angka.charAt(r.nextInt(10));
                    }

                    kodeLowongan = divisi + "-" + posisi + "-" + jenisLowonganID + "-" + angkaAcak;
                }
            }

            lowongan.setDivisi(calonLowongan.getDivisi());
            lowongan.setPosisi(calonLowongan.getPosisi());
            lowongan.setJumlah(calonLowongan.getJumlah());
            lowongan.setJenisLowongan(jenisLowonganService.getById(calonLowongan.getJenisLowongan()));

            if(userService.getUserByUsername(calonLowongan.getUsername()) == null){
                lowongan.setUser(userService.addUser2(calonLowongan.getUsername()));
            }
            else{
                lowongan.setUser(userService.getUserByUsername(calonLowongan.getUsername()));
            }

            response.setStatus(200);
            response.setMessage("success");
            response.setResult(lowongan);
            lowonganRestService.createLowongan(lowongan);

            return response;

        }
    }

}
