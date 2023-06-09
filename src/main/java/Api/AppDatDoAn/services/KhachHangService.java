package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.KhachHang;
import Api.AppDatDoAn.reponsitory.IKhachHangRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService {
    @Autowired
    private IKhachHangRepository khachHangReponsitory;

    public KhachHang getKhachHangById(String id) {
        return khachHangReponsitory.findByMaKhachHang(id);
    }

    public List<KhachHang> getAllKhachHang() {
        return khachHangReponsitory.findAll();
    }

    public KhachHang saveKhachHang(KhachHang khachhang) {
        String makh = RandomStringUtils.randomAlphanumeric(10);
        khachhang.setMakhachhang(makh);
        return khachHangReponsitory.save(khachhang);
    }

    public void updateKhachHang(String id, KhachHang khachhang) {
        KhachHang eKhachhang = khachHangReponsitory.findByMaKhachHang(id);
        eKhachhang.setTenkhachhang(khachhang.getTenkhachhang());
        eKhachhang.setSodienthoai(khachhang.getSodienthoai());
        eKhachhang.setDiachi(khachhang.getDiachi());
        khachHangReponsitory.save(eKhachhang);
    }

    public void removeKhachHang(String id) {
        khachHangReponsitory.deleteById(id);
    }
}
