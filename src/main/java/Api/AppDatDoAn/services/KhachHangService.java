package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.Khachhang;
import Api.AppDatDoAn.reponsitory.IKhachHangReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhachHangService {
    @Autowired
    private IKhachHangReponsitory khachHangReponsitory;

    public Khachhang getKhachHangById(UUID id) {
        return khachHangReponsitory.findByMaKhachHang(id);
    }

    public List<Khachhang> getAllKhachHang() {
        return khachHangReponsitory.findAll();
    }

    public Khachhang saveKhachHang(Khachhang khachhang) {
        return khachHangReponsitory.save(khachhang);
    }

    public void updateKhachHang(UUID id, Khachhang khachhang) {
        Khachhang eKhachhang = khachHangReponsitory.findByMaKhachHang(id);
        eKhachhang.setTenkhachhang(khachhang.getTenkhachhang());
        eKhachhang.setSodienthoai(khachhang.getSodienthoai());
        eKhachhang.setDiachi(khachhang.getDiachi());
        khachHangReponsitory.save(eKhachhang);
    }

    public void removeKhachHang(UUID id) {
        khachHangReponsitory.deleteById(id);
    }
}
