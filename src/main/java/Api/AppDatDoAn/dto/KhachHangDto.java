package Api.AppDatDoAn.dto;

import Api.AppDatDoAn.entity.Dondathang;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class KhachHangDto {
    private UUID makhachhang;

    private String tenkhachhang;

    private String diachi;

    private Long sodienthoai;
}
