package Api.AppDatDoAn.dto;

import Api.AppDatDoAn.entity.Sanpham;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class LoaiSanPhamDto {
    private Long maloai;

    private String tenloai;
}
