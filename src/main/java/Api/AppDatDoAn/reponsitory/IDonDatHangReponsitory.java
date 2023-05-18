package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.DonDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDonDatHangReponsitory extends JpaRepository<DonDatHang, String> {
    @Query("SELECT ddh FROM DonDatHang ddh WHERE ddh.madondathang = ?1")
    DonDatHang findByDDHById(String id);

    @Query("SELECT kh.tenkhachhang, sp.tensanpham, ctdh.soluong, ddh.ngaydat, ddh.giohen, ddh.tinhtrang " +
            "FROM SanPham sp " +
            "JOIN ChiTietDonDatHang ctdh ON sp.masanpham = ctdh.id.masanpham " +
            "JOIN DonDatHang ddh ON ctdh.id.madondathang = ddh.madondathang " +
            "JOIN KhachHang kh ON kh.makhachhang = ddh.khachhang.makhachhang " +
            "WHERE kh.makhachhang = :id")
    List<Object[]> LayThongTinDatHangTheoKhachHang(UUID id);

}
