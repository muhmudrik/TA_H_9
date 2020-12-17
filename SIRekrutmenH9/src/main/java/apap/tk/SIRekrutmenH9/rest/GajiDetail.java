package apap.tk.SIRekrutmenH9.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GajiDetail {
    @JsonProperty("username")
    private String username;

    @JsonProperty("gajiModel.gajiPokok")
    private Long gajiPokok;

    public String getLamaBerkerja() {
        return lamaBerkerja;
    }

    public void setLamaBerkerja(String lamaBerkerja) {
        this.lamaBerkerja = lamaBerkerja;
    }

    @JsonProperty("lamaBerkerja")
    private String lamaBerkerja;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(Long gajiPokok) {
        this.gajiPokok = gajiPokok;
    }




}
