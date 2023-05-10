package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.KhachHang;
import Api.AppDatDoAn.reponsitory.IKhachHangReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhachHangService {
    @Autowired
    private IKhachHangReponsitory khachHangReponsitory;

    public KhachHang getKhachHangById(UUID id) {
        return khachHangReponsitory.findByMaKhachHang(id);
    }

    public List<KhachHang> getAllKhachHang() {
        return khachHangReponsitory.findAll();
    }

    public KhachHang saveKhachHang(KhachHang khachhang) {
        return khachHangReponsitory.save(khachhang);
    }

    public void updateKhachHang(UUID id, KhachHang khachhang) {
        KhachHang eKhachhang = khachHangReponsitory.findByMaKhachHang(id);
        eKhachhang.setTenkhachhang(khachhang.getTenkhachhang());
        eKhachhang.setSodienthoai(khachhang.getSodienthoai());
        eKhachhang.setDiachi(khachhang.getDiachi());
        khachHangReponsitory.save(eKhachhang);
    }

    public void removeKhachHang(UUID id) {
        khachHangReponsitory.deleteById(id);
    }
}
