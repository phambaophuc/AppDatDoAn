package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ISanPhamReponsitory extends JpaRepository<SanPham, String> {
    @Query("SELECT sp FROM SanPham sp WHERE " +
            "sp.tensanpham LIKE CONCAT('%',:query, '%')")
    List<SanPham> searchByName(String query);

    @Query("SELECT sp FROM SanPham sp WHERE sp.loaisanpham.maloai = :query")
    List<SanPham> searchByTheLoai(Long query);

    @Query("SELECT sp FROM SanPham sp WHERE LOWER(sp.tensanpham) LIKE %:name% AND sp.loaisanpham.maloai = :theloaiId")
    List<SanPham> searchByNameAndTheLoai(@Param("name") String name, @Param("theloaiId") Long theloaiId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SanPham sp " +
            "SET sp.luotmua = (" +
            "SELECT SUM(ctddh.soluong) " +
            "FROM ChiTietDonDatHang ctddh " +
            "WHERE ctddh.id.masanpham = sp.masanpham) " +
            "WHERE EXISTS (" +
            "SELECT 1 " +
            "FROM ChiTietDonDatHang ctddh " +
            "WHERE ctddh.id.masanpham = sp.masanpham)")
    void updateLuotMua();
}
