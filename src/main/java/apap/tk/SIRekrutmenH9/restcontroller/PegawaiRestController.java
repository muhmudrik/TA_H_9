package apap.tk.SIRekrutmenH9.restcontroller;

import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.rest.BaseResponse2;
import apap.tk.SIRekrutmenH9.rest.PegawaiData;
import apap.tk.SIRekrutmenH9.service.PegawaiRestService;
import apap.tk.SIRekrutmenH9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PegawaiRestController {
    @Autowired
    PegawaiRestService pegawaiRestService;

    @Autowired
    UserService userService;

    // mengambil data pengguna dari SiPegawai
    @GetMapping(value = "/pegawai/{username}")
    // mengambil menggunakan username pengguna
    private BaseResponse2 getPegawaiData(@PathVariable("username") String username){
        return pegawaiRestService.getPegawaiData(username);
    }

    // menambah pengguna dari SiPegawai
    @PostMapping(value = "/pegawai")
    private BaseResponse2 addPegawaiData(@ModelAttribute PegawaiData pegawai){
        return pegawaiRestService.addPegawaiData(pegawai);
    }


}
