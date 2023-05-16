package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IHoaDonReponsitory extends JpaRepository<HoaDon, String> {
    @Query("SELECT hd FROM HoaDon hd WHERE hd.mahoadon = ?1")
    HoaDon findByMaHoaDon(String id);

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
}
