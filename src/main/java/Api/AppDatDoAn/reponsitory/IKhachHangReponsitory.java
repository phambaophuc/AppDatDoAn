package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IKhachHangReponsitory extends JpaRepository<KhachHang, UUID> {
    @Query("SELECT k FROM KhachHang k WHERE k.makhachhang = ?1")
    KhachHang findByMaKhachHang(UUID id);
}
