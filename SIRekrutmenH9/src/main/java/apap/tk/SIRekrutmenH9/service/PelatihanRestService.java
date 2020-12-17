package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.rest.PelatihanDetail;

public interface PelatihanRestService {
    BaseResponse addPelatihanBaru(PelatihanDetail pelatihanDetail);
}
