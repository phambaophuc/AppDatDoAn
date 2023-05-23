package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.ChiTietDonDatHang;
import Api.AppDatDoAn.entity.compositeKey.ChiTietDonDatHangKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IChiTietDonDatHangReponsitory extends JpaRepository<ChiTietDonDatHang, ChiTietDonDatHangKey> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ct_ddh (masanpham, madondathang, soluong) " +
            "VALUES (?1, ?2, ?3)", nativeQuery = true)
    void createChiTietDonDatHang(String masanpham, String madondathang, Long soluong);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ct_ddh WHERE masanpham = ?1 AND madondathang = ?2", nativeQuery = true)
    void deleteChiTietDonDatHang(String masanpham, String madondathang);
}
