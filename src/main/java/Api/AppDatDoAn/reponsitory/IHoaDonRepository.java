package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface IHoaDonRepository extends JpaRepository<HoaDon, String> {
    @Query("SELECT hd FROM HoaDon hd WHERE hd.mahoadon = ?1")
    HoaDon findByMaHoaDon(String id);

    @Query(value = "SELECT * FROM hoadon hd " +
            "JOIN dondathang ddh ON ddh.madondathang = hd.dondathang_id " +
            "JOIN ct_ddh ctdh ON ctdh.madondathang = ddh.madondathang " +
            "JOIN sanpham sp ON sp.masanpham = ctdh.masanpham " +
            "JOIN cuahang ch ON sp.cuahang_id = ch.macuahang " +
            "WHERE ch.macuahang = ?1", nativeQuery = true)
    List<HoaDon> findAllHoaDonByMaCH(String macuahang);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hoadon " +
            "JOIN (" +
            "SELECT ctdh.madondathang, SUM(sp.gia * ctdh.soluong) as tongtien " +
            "FROM ct_ddh ctdh " +
            "JOIN sanpham sp ON ctdh.masanpham = sp.masanpham " +
            "GROUP BY ctdh.madondathang " +
            ") t " +
            "ON hoadon.dondathang_id = t.madondathang " +
            "SET hoadon.tongtien = t.tongtien " +
            "WHERE hoadon.mahoadon = :mahoadon", nativeQuery = true)
    void tinhTongTien(@Param("mahoadon") String mahoadon);

    @Query("SELECT MONTH(h.ngaylap) AS month, SUM(h.tongtien) AS total "
            + "FROM HoaDon h "
            + "WHERE YEAR(h.ngaylap) = :year "
            + "GROUP BY MONTH(h.ngaylap)")
    List<Object[]> getMonthlyRevenue(@Param("year") int year);

    @Query("SELECT DAY(h.ngaylap) AS day, SUM(h.tongtien) AS total " +
            "FROM HoaDon h " +
            "WHERE MONTH(h.ngaylap) = :month AND YEAR(h.ngaylap) = :year " +
            "GROUP BY DAY(h.ngaylap)")
    List<Object[]> getDailyRevenue(@Param("month") int month, @Param("year") int year);
}
