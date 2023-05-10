package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.List;

@Data
@Entity
@Table(name = "cuahang")
public class CuaHang {
    @Id
    @Column(name = "macuahang", columnDefinition = "VARCHAR(144)", unique = true)
    private String macuahang;

    @Column(name = "tencuahang", length = 144)
    private String tencuahang;

    @Column(name = "hinhanh")
    private String hinhanh;

    @Column(name = "diachi", length = 255)
    private String diachi;

    @Column(name = "sodienthoai")
    private Long sodienthoai;

    @Column(name = "luotdanhgia")
    private Long luotdanhgia;

    @Column(name = "chatluong")
    private int chatluong;

    @Column(name = "giomocua")
    private Time giomocua;

    @Column(name = "giodongcua")
    private Time giodongcua;

    @Column(name = "tinhtrang", length = 255)
    private String tinhtrang;

    @OneToMany(mappedBy = "cuahang", cascade = CascadeType.ALL)
    private List<SanPham> sanphams;
}
