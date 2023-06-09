package Api.AppDatDoAn.services;

import Api.AppDatDoAn.reponsitory.IChiTietDonDatHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChiTietDonDatHangService {
    @Autowired
    private IChiTietDonDatHangRepository chiTietDonDatHangReponsitory;

    public void createChiTietDDH(String masanpham, String madondathang, Long soluong) {
        chiTietDonDatHangReponsitory.createChiTietDonDatHang(masanpham, madondathang, soluong);
    }

    public void deleteChiTietDDH(String masanpham, String madondathang) {
        chiTietDonDatHangReponsitory.deleteChiTietDonDatHang(masanpham, madondathang);
    }
}
