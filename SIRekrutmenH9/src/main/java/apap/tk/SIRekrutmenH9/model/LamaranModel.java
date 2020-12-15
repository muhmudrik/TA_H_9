package apap.tk.SIRekrutmenH9.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "lamaran")
public class LamaranModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_lowongan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LowonganModel lowonganModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pelamar", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PelamarModel pelamarModel;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_diterima")
    Date tanggal_diterima;

    @NotNull
    @Column(name = "status")
    Integer status;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LowonganModel getLowonganModel() {
        return this.lowonganModel;
    }

    public void setLowonganModel(LowonganModel lowonganModel) {
        this.lowonganModel = lowonganModel;
    }

    public PelamarModel getPelamarModel() {
        return this.pelamarModel;
    }

    public void setPelamarModel(PelamarModel pelamarModel) {
        this.pelamarModel = pelamarModel;
    }

    public Date getTanggal_diterima() {
        return this.tanggal_diterima;
    }

    public void setTanggal_diterima(Date tanggal_diterima) {
        this.tanggal_diterima = tanggal_diterima;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
