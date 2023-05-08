package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.Sanpham;
import Api.AppDatDoAn.reponsitory.ISanPhamReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {
    @Autowired
    private ISanPhamReponsitory sanPhamReponsitory;

    public List<Sanpham> getAllSanPham() {
        return sanPhamReponsitory.findAll();
    }

    public Sanpham getSanPhamById(Long id) {
        Optional<Sanpham> optional = sanPhamReponsitory.findById(id);
        return optional.orElse(null);
    }

    public List<Sanpham> timKiemTheoTen(String name) {
        return sanPhamReponsitory.searchByName(name);
    }

    public List<Sanpham> timKiemTheoTheLoai(Long idTheLoai) {
        return sanPhamReponsitory.searchByTheLoai(idTheLoai);
    }

    public List<Sanpham> timKiemTheoTenVaTheLoai(String name, Long theLoai) {
        return sanPhamReponsitory.searchByNameAndTheLoai(name, theLoai);
    }

    public Sanpham saveSanPham(Sanpham sanPham) {
        return sanPhamReponsitory.save(sanPham);
    }

    public void removeSanPham(Long id) {
        sanPhamReponsitory.deleteById(id);
    }
}
