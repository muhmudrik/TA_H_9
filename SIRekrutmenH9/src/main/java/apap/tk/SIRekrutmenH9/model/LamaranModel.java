package apap.tk.SIRekrutmenH9.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "lamaran")
public class LamaranModel {
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_lowongan", referencedColumnName = "id_lowongan")
    LowonganModel lowongan;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pelamar", referencedColumnName = "id")
    PelamarModel pelamar;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_diterima")
    Date tanggal_diterima;

    @NotNull
    @Column(name = "status")
    Integer status;
}
