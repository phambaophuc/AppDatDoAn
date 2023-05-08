package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "sanpham")
public class Sanpham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long masanpham;

    @Column(name = "tensanpham", length = 255)
    private String tensanpham;

    @Column(name = "mota", length = 255)
    private String mota;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "tinhtrang", length = 255)
    private String tinhtrang;

    @Column(name = "ghichu", length = 255)
    private String ghichu;

    @ManyToOne
    @JoinColumn(name = "loaisanpham_id", nullable = false)
    private Loaisanpham loaisanpham;

    @ManyToOne
    @JoinColumn(name = "cuahang_id", nullable = false)
    private Cuahang cuahang;
}
