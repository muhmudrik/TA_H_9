package apap.tk.SIRekrutmenH9.controller;

import apap.tk.SIRekrutmenH9.model.*;

import apap.tk.SIRekrutmenH9.repository.JenisLowonganDB;
import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.rest.PelatihanDetail;
import apap.tk.SIRekrutmenH9.service.JenisLowonganService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import apap.tk.SIRekrutmenH9.service.LowonganService;
import apap.tk.SIRekrutmenH9.service.PelatihanRestService;
import apap.tk.SIRekrutmenH9.service.LamaranService;
import apap.tk.SIRekrutmenH9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @Autowired
    private JenisLowonganService jenisLowonganService;

    @Autowired
    private UserService userService;

    @Autowired
    private JenisLowonganDB jenisLowonganDb;

    @Autowired
    private LamaranService lamaranService;

    @Autowired
    private PelatihanRestService pelatihanRestService;

    @GetMapping("/lowongan/add")
    public String addLowonganFormPage(Model model){
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getAll();

        model.addAttribute("lowongan", new LowonganModel());
        model.addAttribute("listJenisLowongan", listJenisLowongan);

        return "form-add-lowongan";
    }

    @PostMapping("/lowongan/add")
    public String addLowonganSubmit(
            @ModelAttribute LowonganModel lowongan,
            Model model
    ){

        String kodeLowongan = "";
        String posisi = lowongan.getPosisi().substring(0,2).toUpperCase();
        String divisi = lowongan.getDivisi().substring(0,2).toUpperCase();
        String angkaAcak = "";
        String jenisLowonganID = lowongan.getJenisLowongan().getId().toString();
        UserModel userLowongan = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        String angka = "0123456789";
        Random r = new Random();

        for(int i = 0; i < 2; i++){
            angkaAcak += angka.charAt(r.nextInt(10));
        }

        kodeLowongan = divisi + "-" + posisi + "-" + jenisLowonganID + "-" + angkaAcak;

        lowongan.setKodeLowongan(kodeLowongan);
        lowongan.setUser(userLowongan);


        lowonganService.addLowongan(lowongan);
        model.addAttribute("lowongan", lowongan);
        model.addAttribute("idLowongan", lowongan.getId_lowongan());

        return "add-lowongan";
    }

    @RequestMapping("/lowongan/daftarLowongan")
    public String daftarLowongan(Model model){
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();
        UserModel userLogin = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        Long roleUser = userLogin.getRole().getId();
        String uuidStaffRekrutmen = "";
        if (roleUser == 5){
            uuidStaffRekrutmen = userLogin.getId();
        }
        boolean stafRekrut = false;

        if (roleUser == 5){
            stafRekrut = true;
        }

        model.addAttribute("listLowongan", listLowongan);
        model.addAttribute("roleUser", roleUser);
        // Karna gabisa gw komen dulu
         model.addAttribute("uuidStaffRekrutmen", uuidStaffRekrutmen);
        model.addAttribute("stafRekrut", stafRekrut);



        return "daftar-lowongan";
    }
  
    @RequestMapping(value = "/lowongan/ubahLowongan/{id}", method = RequestMethod.GET)
    public String ubahLowonganForm(Model model, @PathVariable(value = "id") Long id) {
        LowonganModel lowongan = lowonganService.getLowonganById(id);
        List<LamaranModel> listLamaran = lowongan.getListLamaran();
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganDb.findAll();

        model.addAttribute("lowongan", lowongan);
        model.addAttribute("listLamaran", listLamaran);
        model.addAttribute("listJenisLowongan", listJenisLowongan);
        return "form-ubah-lowongan";
    }

    @RequestMapping(value = "/lowongan/ubahLowongan", method = RequestMethod.POST)
    public String ubahLowonganSubmit(@ModelAttribute LowonganModel lowongan, Model model) {
        String userId = lowongan.getUser().getId();
        UserModel userModel = userService.getUserById(userId);

        lowongan.setUser(userModel);

        LowonganModel targetLowongan = lowonganService.ubahLowongan(lowongan);
        model.addAttribute("lowongan", targetLowongan);
        return "ubah-lowongan";
    }

    @RequestMapping(value = "/lowongan/detail/{id_lowongan}", method = RequestMethod.GET)
    public String getDetailLowongan(
        @PathVariable(name = "id_lowongan") Long id_lowongan,
        Model model
    ){
        UserModel userLogin = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long roleUser = userLogin.getRole().getId();

        // List<PelamarModel> listPelamar = lamaranService.getPelamarFromLamaranList(id_lowongan);
        // System.out.println(listPelamar.size());
        // model.addAttribute("listPelamar", listPelamar);
        model.addAttribute("listLamaran", lamaranService.getLamaranByLowongan(id_lowongan));
        model.addAttribute("roleUser", roleUser);
        return "detail-lowongan";
    }

    @RequestMapping(value = "/lowongan/detail/{id_lowongan}", method = RequestMethod.POST)
    public String updateStatusLamaran(
        @PathVariable(name = "id_lowongan") Long id_lowongan,
        @RequestParam("id") Long id,
        // @RequestParam("lowonganModel") LowonganModel lowonganModel,
        // @RequestParam("pelamarModel") PelamarModel pelamarModel,
        // @RequestParam("tanggal_diterima") Date tanggal_diterima,
        @RequestParam("status") Integer status,
        Model model
    ){
        UserModel userLogin = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long roleUser = userLogin.getRole().getId();

        boolean stafRekrut = false;

        if (roleUser == 5){
            stafRekrut = true;
        }

        // List<PelamarModel> listPelamar = lamaranService.getPelamarFromLamaranList(id_lowongan);
        // System.out.println(listPelamar.size());
        // model.addAttribute("listPelamar", listPelamar);
        // lamaranService.saveLamaran(lamaran);
        // System.out.println(id);
        LamaranModel lamaran = lamaranService.getLamaranById(id);
        lamaran.setStatus(status);
        if(status == 2){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateWithoutTime = new Date();
            try {
                dateWithoutTime = sdf.parse(sdf.format(new Date()));
            } 
            catch (ParseException e) {
                e.printStackTrace();
            }
            lamaran.setTanggal_diterima(dateWithoutTime);
        }
        lamaranService.saveLamaran(lamaran);

        LowonganModel lowongan = lowonganService.getLowonganById(id_lowongan);
        Integer pelamarDiterima = lamaranService.countLamaranDiterima(id_lowongan);
        System.out.println(pelamarDiterima);
        if (lowongan.getJumlah() == pelamarDiterima) {
            System.out.println("Masuk");

            // Create objek Pelatihan
            PelatihanDetail latian = new PelatihanDetail();
            // latian.setNama_pelatihan("Pelatihan "+lowongan.getKodeLowongan());
            // latian.setDeskripsi(
            //     "Pelatihan Onboarding " + lowongan.getDivisi() + 
            //     " " + lowongan.getJenisLowongan() + " " + lowongan.getPosisi());
            latian.setNama_pelatihan("Test Mockup");
            latian.setDeskripsi("a");
            latian.setJenis_pelatihan(1);
            latian.setKapasitas(lowongan.getJumlah());
            BaseResponse resp = pelatihanRestService.addPelatihanBaru(latian);
            System.out.println(resp.getStatus());
        }


        model.addAttribute("listLamaran", lamaranService.getLamaranByLowongan(id_lowongan));
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("stafRekrut", stafRekrut);
        return "detail-lowongan";
    }
}
