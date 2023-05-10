package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long masanpham;

    @Column(name = "tensanpham", length = 255)
    private String tensanpham;

    @Column(name = "mota", length = 255)
    private String mota;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "luotmua")
    private Long luotmua;

    @Column(name = "tinhtrang", length = 255)
    private String tinhtrang;

    @Column(name = "ghichu", length = 255)
    private String ghichu;

    @ManyToOne
    @JoinColumn(name = "loaisanpham_id", nullable = false)
    private LoaiSanPham loaisanpham;

    @ManyToOne
    @JoinColumn(name = "cuahang_id", nullable = false)
    private CuaHang cuahang;

    @OneToMany(mappedBy = "sanpham")
    private Set<ChiTietDonDatHang> chiTietDonDatHangs;
}
