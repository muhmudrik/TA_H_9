package apap.tk.SIRekrutmenH9.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LowonganDetail {
    @JsonProperty("id")
    private Integer id;

}
