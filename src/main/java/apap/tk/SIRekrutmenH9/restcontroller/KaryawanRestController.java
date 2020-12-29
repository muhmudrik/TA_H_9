package apap.tk.SIRekrutmenH9.restcontroller;

import apap.tk.SIRekrutmenH9.model.PelamarModel;
import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.service.KaryawanRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class KaryawanRestController {
    @Autowired
    private KaryawanRestService karyawanRestService;

//    @GetMapping("/karyawanBaru")
//    private List<PelamarModel> getListKaryawanBaru() {
//        return karyawanRestService.getListKaryawanBaru();
//    }

    @GetMapping(value = "/karyawanBaru")
    private BaseResponse<List<PelamarModel>> getListKaryawanBaru() {
        BaseResponse<List<PelamarModel>> response = new BaseResponse<>();
        response.setStatus(200);
        response.setMessage("Success");
        response.setResult(karyawanRestService.getListKaryawanBaru());
        return response;
    }
}