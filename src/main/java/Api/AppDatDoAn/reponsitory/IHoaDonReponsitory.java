package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IHoaDonReponsitory extends JpaRepository<HoaDon, UUID> {
    @Query("SELECT hd FROM HoaDon hd WHERE hd.mahoadon = ?1")
    HoaDon findByMaHoaDon(String id);
}
