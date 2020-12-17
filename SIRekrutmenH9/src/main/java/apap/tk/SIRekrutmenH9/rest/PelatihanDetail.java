package apap.tk.SIRekrutmenH9.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PelatihanDetail {
    @NotNull
    @Size(max = 50)
    private String nama_pelatihan;

    @NotNull
    @Size(max = 200)
    private String deskripsi;

    @NotNull
    private Integer kapasitas;

    @NotNull
    private Integer jenis_pelatihan;


    public String getNama_pelatihan() {
        return this.nama_pelatihan;
    }

    public void setNama_pelatihan(String nama_pelatihan) {
        this.nama_pelatihan = nama_pelatihan;
    }

    public String getDeskripsi() {
        return this.deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getKapasitas() {
        return this.kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }

    public Integer getJenis_pelatihan() {
        return this.jenis_pelatihan;
    }

    public void setJenis_pelatihan(Integer jenis_pelatihan) {
        this.jenis_pelatihan = jenis_pelatihan;
    }
}