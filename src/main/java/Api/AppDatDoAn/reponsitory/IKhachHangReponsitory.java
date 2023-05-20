package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IKhachHangReponsitory extends JpaRepository<KhachHang, String> {
    @Query("SELECT k FROM KhachHang k WHERE k.makhachhang = ?1")
    KhachHang findByMaKhachHang(String id);
}
