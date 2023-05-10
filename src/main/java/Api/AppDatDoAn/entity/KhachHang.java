package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "khachhang")
public class KhachHang {
    @Id
    @GeneratedValue
    private UUID makhachhang;

    @Column(name = "tenkhachhang", length = 144)
    private String tenkhachhang;

    @Column(name = "diachia", length = 255)
    private String diachi;

    @Column(name = "sodienthoai")
    private Long sodienthoai;

    @OneToMany(mappedBy = "khachhang", cascade = CascadeType.ALL)
    private List<DonDatHang> dondathangs;
}
