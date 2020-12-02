package apap.tk.SIRekrutmenH9.model;

import javax.persistence.*;
        import javax.validation.constraints.NotNull;
        import javax.validation.constraints.Size;
        import java.io.Serializable;
        import java.util.List;

@Entity
@Table(name = "jenisLowongan")
public class JenisLowonganModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public Integer getId() {
        return id;
    }
}