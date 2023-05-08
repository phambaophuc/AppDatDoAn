package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.Loaisanpham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoaiSanPhamReponsitory extends JpaRepository<Loaisanpham, Long> {

}
