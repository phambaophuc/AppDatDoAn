package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.Khachhang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IKhachHangReponsitory extends JpaRepository<Khachhang, UUID> {
    @Query("SELECT k FROM Khachhang k WHERE k.makhachhang = ?1")
    Khachhang findByMaKhachHang(UUID id);
}
