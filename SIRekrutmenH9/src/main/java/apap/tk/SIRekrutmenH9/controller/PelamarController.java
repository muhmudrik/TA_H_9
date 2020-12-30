package apap.tk.SIRekrutmenH9.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import apap.tk.SIRekrutmenH9.model.RoleModel;
import apap.tk.SIRekrutmenH9.repository.PelamarDb;
import apap.tk.SIRekrutmenH9.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import apap.tk.SIRekrutmenH9.model.PelamarModel;
import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.service.PelamarService;
import apap.tk.SIRekrutmenH9.service.RoleService;
import apap.tk.SIRekrutmenH9.service.UserService;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping(value = "/pelamar")
public class PelamarController {
    @Autowired
    private PelamarService pelamarService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PelamarDb pelamarDb;

    @Autowired
    private UserDb userDb;

    @GetMapping(value = "/buat")
    public String addPelamarForm(Model model) {
        model.addAttribute("msg", "Peringatan: Username yang didaftarkan tidak dapat diganti kembali");
        return "form-pelamar";
    }

    @PostMapping(value = "/buat")
    public String addPelamarSubmit(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("nama") String nama,
        @RequestParam("no_telepon") String noTelepon,
        @RequestParam("tempat_lahir") String tempatLahir,
        @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("tanggal_lahir") Date tanggalLahir,
        @RequestParam("alamat") String alamat,
        Model model
    ) {
        UserModel userBaru = new UserModel();
        PelamarModel pelamar = new PelamarModel();

        userBaru.setUsername(username);
        userBaru.setPassword(password);
        RoleModel pelamarRole = roleService.findAll().get(6);
        userBaru.setRole(pelamarRole);
        userService.addUser(userBaru);

        pelamar.setUuidUser(userBaru);
        pelamar.setNama(nama);
        pelamar.setNoTelepon(noTelepon);
        pelamar.setTempatLahir(tempatLahir);
        pelamar.setTanggalLahir(tanggalLahir);
        pelamar.setAlamat(alamat);
        pelamarService.addPelamar(pelamar);

        //userDb.findByUsername(username).setPelamar(pelamar);
        userBaru.setPelamar(pelamar);
        userService.updateUser(userBaru);

        model.addAttribute("pelamar", pelamar);
        return "add-pelamar";
    }

    @GetMapping(value = "/ubah/{idPelamar}")
    public String updatePelamarForm(
            @PathVariable(required = true) Integer idPelamar,
            HttpServletRequest request,
            Model model) {
        if(pelamarService.getPelamar(idPelamar) != null){
            if(pelamarService.getPelamar(idPelamar).getUuidUser().getUsername() == userService.getUserByUsername(request.getRemoteUser()).getUsername()){
            PelamarModel pelamar = pelamarService.getPelamar(idPelamar);
            model.addAttribute("pelamar", pelamar);
                return "form-update-pelamar";
            }
            else{
                model.addAttribute("msg", "Halaman yang anda cari tidak ditemukan");
                return "error/404";

            }
        } else{
            return "error/404";
        }
    }

    @PostMapping(value = "/ubah")
    public String updatePelamarSubmit(
            HttpServletRequest request,
            Model model
    ) throws ParseException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String username = request.getParameter("username");
        String nama = request.getParameter("nama");
        String noTelepon = request.getParameter("noTelepon");
        String tempatLahir = request.getParameter("tempatLahir");
        String alamat = request.getParameter("alamat");
        String date = request.getParameter("tanggalLahir");
        Date tanggalLahir = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        PelamarModel pelamar = pelamarDb.findById(id).get();
        pelamarService.ubahInformasiPelamar(pelamar, nama, noTelepon, tempatLahir, alamat, tanggalLahir);
        model.addAttribute("pelamar", pelamar);

        return "update-pelamar";
    }



}
