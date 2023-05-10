package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDonDatHangReponsitory extends JpaRepository<DonDatHang, UUID> {
    @Query("SELECT ddh FROM DonDatHang ddh WHERE ddh.madondathang = ?1")
    DonDatHang findByDDHById(UUID id);
}
