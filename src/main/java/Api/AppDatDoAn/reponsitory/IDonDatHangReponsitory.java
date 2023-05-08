package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.Dondathang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDonDatHangReponsitory extends JpaRepository<Dondathang, UUID> {
}
