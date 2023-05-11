package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "loaisanpham")
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maloai;

    @NotNull(message = "Tên loại không được phép null")
    @Column(name = "tenloai", length = 144)
    private String tenloai;

    @OneToMany(mappedBy = "loaisanpham", cascade = CascadeType.ALL)
    private List<SanPham> sanphams;
}
