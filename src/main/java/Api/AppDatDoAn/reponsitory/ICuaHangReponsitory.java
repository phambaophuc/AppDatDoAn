package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.Cuahang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuaHangReponsitory extends JpaRepository<Cuahang, String> {
    @Query("SELECT c FROM Cuahang c WHERE c.macuahang = ?1")
    Cuahang findByMaCuaHang(String macuahang);

}
