package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.reponsitory.IDonDatHangReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DonDatHangService {

    @Autowired
    private IDonDatHangReponsitory donDatHangReponsitory;

    public DonDatHang getDDHById(UUID id) {
        return donDatHangReponsitory.findByDDHById(id);
    }

    public List<Object[]> LayThongTinDatHangTheoKhachHang(UUID id) {
        return donDatHangReponsitory.LayThongTinDatHangTheoKhachHang(id);
    }

    public List<DonDatHang> getAllDDH() {
        return donDatHangReponsitory.findAll();
    }

    public DonDatHang saveDDH(DonDatHang donDatHang) {
        return donDatHangReponsitory.save(donDatHang);
    }
}
