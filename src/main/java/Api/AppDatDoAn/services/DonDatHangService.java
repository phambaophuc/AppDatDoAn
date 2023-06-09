package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.reponsitory.IDonDatHangRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DonDatHangService {

    @Autowired
    private IDonDatHangRepository donDatHangReponsitory;

    public DonDatHang getDDHById(String id) {
        return donDatHangReponsitory.findByDDHById(id);
    }

    public List<Object[]> LayThongTinDatHangTheoKhachHang(UUID id) {
        return donDatHangReponsitory.LayThongTinDatHangTheoKhachHang(id);
    }

    public List<DonDatHang> getAllDDH() {
        return donDatHangReponsitory.findAll();
    }

    public DonDatHang saveDDH(DonDatHang donDatHang) {

        String maDDH = RandomStringUtils.randomAlphanumeric(10);
        donDatHang.setMadondathang(maDDH);

        return donDatHangReponsitory.save(donDatHang);
    }
}
