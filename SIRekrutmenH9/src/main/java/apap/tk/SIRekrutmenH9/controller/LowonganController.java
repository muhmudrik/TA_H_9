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
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
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
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();
        List<String> listKodeLowongan = new ArrayList<>();

        if(listLowongan.size() > 0) {

            for (LowonganModel lowonganList : listLowongan) {
                if (kodeLowongan != "" && kodeLowongan != lowonganList.getKodeLowongan()) {
                    lowongan.setKodeLowongan(kodeLowongan);
                } else {
                    String angka = "0123456789";
                    Random r = new Random();

                    for (int i = 0; i < 2; i++) {
                        angkaAcak += angka.charAt(r.nextInt(10));
                    }

                    kodeLowongan = divisi + "-" + posisi + "-" + jenisLowonganID + "-" + angkaAcak;
                    lowongan.setKodeLowongan(kodeLowongan);
                }
            }
        } else {
            String angka = "0123456789";
            Random r = new Random();

            for (int i = 0; i < 2; i++) {
                angkaAcak += angka.charAt(r.nextInt(10));
            }
            kodeLowongan = divisi + "-" + posisi + "-" + jenisLowonganID + "-" + angkaAcak;
            lowongan.setKodeLowongan(kodeLowongan);
        }
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
        Integer jenisLowonganID = lowongan.getJenisLowongan().getId();
        JenisLowonganModel jenisLowongan = jenisLowonganService.getById(jenisLowonganID);

        model.addAttribute("lowongan", lowongan);
        model.addAttribute("listLamaran", listLamaran);
        model.addAttribute("listJenisLowongan", listJenisLowongan);
        model.addAttribute("jenisLowongan", jenisLowongan);
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
        LowonganModel lowongan = lowonganService.getLowonganById(id_lowongan);
        List<PelamarModel> listPelamar = lamaranService.getPelamarFromLamaranList(id_lowongan);
        String userBuatLowongan = userService.getUserById(lowongan.getUser().getId()).getUsername();

        // List<PelamarModel> listPelamar = lamaranService.getPelamarFromLamaranList(id_lowongan);
        // System.out.println(listPelamar.size());
        // model.addAttribute("listPelamar", listPelamar);

        model.addAttribute("listLamaran", lamaranService.getLamaranByLowongan(id_lowongan));
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("listPelamar", listPelamar);
        model.addAttribute("lowongan", lowongan);
        model.addAttribute("userBuatLowongan", userBuatLowongan);
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

        // Update tanggal lamaran jadi tanggal diterima
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

        // WebService
        LowonganModel lowongan = lowonganService.getLowonganById(id_lowongan);
        String userBuatLowongan = userService.getUserById(lowongan.getUser().getId()).getUsername();
        List<PelamarModel> listPelamar = lamaranService.getPelamarFromLamaranList(id_lowongan);
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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateWithoutTime = new Date();
            try {
                dateWithoutTime = sdf.parse(sdf.format(new Date()));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            LocalDate now = LocalDate.now();
            latian.setTanggal_mulai(java.sql.Date.valueOf(now));
            latian.setTanggal_selesai(java.sql.Date.valueOf(now.plusDays(5)));

            Date in = new Date();
            LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
            ldt = ldt.withHour(8);
            ldt = ldt.withMinute(30);
            ldt = ldt.withSecond(0);
            Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            latian.setWaktu_mulai(out);

            ldt = ldt.plusDays(5);
            ldt = ldt.withHour(15);
            ldt = ldt.withMinute(0);
            out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            latian.setWaktu_selesai(out);

            latian.setJenis_pelatihan(1);
            latian.setKapasitas(lowongan.getJumlah());
            BaseResponse<PelatihanDetail> resp = pelatihanRestService.addPelatihanBaru(latian);
            System.out.println(resp.getStatus());
        }

        model.addAttribute("lowongan", lowongan);
        model.addAttribute("userBuatLowongan", userBuatLowongan);
        model.addAttribute("listPelamar", listPelamar);
        model.addAttribute("listLamaran", lamaranService.getLamaranByLowongan(id_lowongan));
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("stafRekrut", stafRekrut);
        return "detail-lowongan";
    }

    @RequestMapping("/lowongan/hapus/{id}")
    public String hapusLowongan(
            @PathVariable(value = "id") Long id,
            Model model)
    {
        LowonganModel lowongan = lowonganService.getLowonganById(id);
        model.addAttribute("lowongan", lowongan);
        List<LamaranModel> listLamaran = lowongan.getListLamaran();

        boolean isDeletable = true;

        for(LamaranModel lamaran : listLamaran){
            Integer tempStatus = lamaran.getStatus();
            if(tempStatus <= 1){
                isDeletable = false;
            }
        }

        if(isDeletable){
            lowonganService.deleteLowongan(id);
            return "delete-lowongan";
        }else{
            return "delete-lowongan-restricted";
        }
    }

    @RequestMapping(value = "/lowongan/detail/{id_lowongan}", method = RequestMethod.POST, params = {"hapus"})
    public String hapusLamaranDitolak(
        @PathVariable(name = "id_lowongan") Long id_lowongan,
        @RequestParam("id") Long id,
        Model model
    ){
        UserModel userLogin = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long roleUser = userLogin.getRole().getId();
        LowonganModel lowongan = lowonganService.getLowonganById(id_lowongan);
        List<PelamarModel> listPelamar = lamaranService.getPelamarFromLamaranList(id_lowongan);
        String userBuatLowongan = userService.getUserById(lowongan.getUser().getId()).getUsername();

        // List<PelamarModel> listPelamar = lamaranService.getPelamarFromLamaranList(id_lowongan);
        // System.out.println(listPelamar.size());
        // model.addAttribute("listPelamar", listPelamar);

        lamaranService.deleteLamaranById(id);
        
        model.addAttribute("listLamaran", lamaranService.getLamaranByLowongan(id_lowongan));
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("listPelamar", listPelamar);
        model.addAttribute("lowongan", lowongan);
        model.addAttribute("userBuatLowongan", userBuatLowongan);
        return "detail-lowongan";
    }
}