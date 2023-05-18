package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Tên cửa hàng không được phép null")
    @Size(max = 64, message = "Chuỗi không được quá 64 ký tự")
    @Column(name = "tencuahang", length = 64)
    private String tencuahang;

    @Column(name = "hinhanh")
    private String hinhanh;

    @NotNull(message = "Địa chỉ không được phép null")
    @Column(name = "diachi", length = 255)
    private String diachi;

    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại không hợp lệ")
    @Column(name = "sodienthoai", length = 10)
    private String sodienthoai;

    @Column(name = "luotdanhgia")
    private Long luotdanhgia;

    @Column(name = "chatluong")
    private double chatluong;

    @Column(name = "giomocua")
    private Time giomocua;

    @Column(name = "giodongcua")
    private Time giodongcua;

    @Column(name = "tinhtrang", length = 255)
    private String tinhtrang;

    @OneToMany(mappedBy = "cuahang", cascade = CascadeType.ALL)
    private List<SanPham> sanphams;

    public void setChatluong(double chatluong) {
        if (chatluong > 5) {
            this.chatluong = 5;
        } else {
            this.chatluong = chatluong;
        }
    }
}
