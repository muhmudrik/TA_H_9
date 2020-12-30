package apap.tk.SIRekrutmenH9.restcontroller;

import apap.tk.SIRekrutmenH9.rest.GajiDetail;
import apap.tk.SIRekrutmenH9.rest.Setting;
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
        String gajiFinderUrl = Setting.gajiUrl;
        List listGajiRest = restTemplate.getForObject(gajiFinderUrl, List.class);
        //List listGaji = (List) listGajiRest.get("result");
        List<GajiDetail> gaji = new ArrayList<>();
        Long meanGaji = Long.parseLong("0");

        for(int i = 0; i<listGajiRest.size(); i++){
            GajiDetail gajiDetail = new GajiDetail();
            LinkedHashMap<String, Object> firstHash = (LinkedHashMap<String, Object>) listGajiRest.get(i);
            LinkedHashMap<String, Object> secondHash = (LinkedHashMap<String, Object>) firstHash.get("gajiModel");
            //LinkedHashMap<String, Object> secondHash = (LinkedHashMap<String, Object>) gajiHash.get("gajiModel");

            String username = String.valueOf(firstHash.get("username"));
            String lamaBerkerja = firstHash.get("lamaBerkerja") + " tahun";
            String gp = String.valueOf(secondHash.get("gajiPokok"));
            Long gajiPokok = Long.parseLong(gp);

            meanGaji += gajiPokok;

            gajiDetail.setUsername(username);
            gajiDetail.setGajiPokok(gajiPokok);
            gajiDetail.setLamaBerkerja(lamaBerkerja);

            gaji.add(gajiDetail);
        }

        meanGaji = meanGaji/(Long.valueOf(listGajiRest.size()));
        model.addAttribute("meanGaji", meanGaji);
        model.addAttribute("gaji", gaji);
        return "gaji";
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
