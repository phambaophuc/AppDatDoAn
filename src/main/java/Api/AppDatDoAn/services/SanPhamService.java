package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.SanPham;
import Api.AppDatDoAn.reponsitory.ISanPhamReponsitory;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {
    @Autowired
    private ISanPhamReponsitory sanPhamReponsitory;

    public List<SanPham> getAllSanPham() {
        return sanPhamReponsitory.findAll();
    }

    public SanPham getSanPhamById(String id) {
        Optional<SanPham> optionalSanPham = sanPhamReponsitory.findById(id);
        return optionalSanPham.orElse(null);
    }

    public List<SanPham> timKiemTheoTen(String name) {
        return sanPhamReponsitory.searchByName(name);
    }

    public List<SanPham> timKiemTheoTheLoai(Long idTheLoai) {
        return sanPhamReponsitory.searchByTheLoai(idTheLoai);
    }

    public List<SanPham> timKiemTheoTenVaTheLoai(String name, Long theLoai) {
        return sanPhamReponsitory.searchByNameAndTheLoai(name, theLoai);
    }

    public SanPham saveSanPham(SanPham sanPham) {
        String masanpham = RandomStringUtils.randomAlphanumeric(10);
        sanPham.setLuotmua(0L);
        sanPham.setMasanpham(masanpham);
        return sanPhamReponsitory.save(sanPham);
    }

    public SanPham updateSanPham(SanPham sanPham) {
        return sanPhamReponsitory.save(sanPham);
    }

    public void removeSanPham(String id) {
        sanPhamReponsitory.deleteById(id);
    }
}
