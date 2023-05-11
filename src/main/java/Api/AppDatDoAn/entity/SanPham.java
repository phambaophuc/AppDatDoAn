package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long masanpham;

    @NotNull(message = "Tên sản phẩm không được phép null")
    @Column(name = "tensanpham", length = 64)
    @Size(max = 64, message = "Chuỗi không được quá 64 ký tự")
    private String tensanpham;

    @Column(name = "mota", length = 144)
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
