package apap.tk.SIRekrutmenH9.restcontroller;

import apap.tk.SIRekrutmenH9.rest.GajiDetail;
import apap.tk.SIRekrutmenH9.rest.setting;
import apap.tk.SIRekrutmenH9.service.GajiRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class GajiRestController {

    @Autowired
    private GajiRestService gajiRestService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/gaji")
    private String getGaji(Model model) {
        String gajiFinderUrl = setting.gajiUrl;
        List listGaji = restTemplate.getForObject(gajiFinderUrl, List.class);
        List<GajiDetail> gaji = new ArrayList<>();
        String jeda = "\n";
        String header = "|Username||Lama Kerja||--Gaji--|"+ jeda;
        String starter = "| ";
        for(int i = 0; i<listGaji.size(); i++){
            GajiDetail gajiDetail = new GajiDetail();
            LinkedHashMap<String, Object> gajiHash = (LinkedHashMap<String, Object>) listGaji.get(i);
            LinkedHashMap<String, Object> secondHash = (LinkedHashMap<String, Object>) gajiHash.get("gajiModel");

            String username = String.valueOf(gajiHash.get("username"));
            String lamaBerkerja = String.valueOf(gajiHash.get("lamaBerkerja")) + " tahun";
            String gp = String.valueOf(secondHash.get("gajiPokok"));
            Long gajiPokok = Long.parseLong(gp);

            gajiDetail.setUsername(username);
            gajiDetail.setGajiPokok(gajiPokok);
            gajiDetail.setLamaBerkerja(lamaBerkerja);

            gaji.add(gajiDetail);

            String newLine = starter + username +" |";
            newLine += starter + lamaBerkerja + " |";
            newLine += starter + gajiPokok + " |" + jeda;
            header += newLine;

        }
        //return restTemplate.getForObject(gajiFinderUrl, String.class);
        model.addAttribute("gaji", gaji);
        return "gaji";
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
