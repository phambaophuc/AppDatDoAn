package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "di_kem")
public class DiKem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maspdk;

    @Column(name = "tenspdk", length = 144)
    private String tenspdk;

    @Column(name = "giatien")
    private Double giatien;

    @ManyToOne
    @JoinColumn(name = "sanpham_id", nullable = false)
    private SanPham sanpham;
}
