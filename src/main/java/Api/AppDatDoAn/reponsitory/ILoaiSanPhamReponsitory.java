package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoaiSanPhamReponsitory extends JpaRepository<LoaiSanPham, Long> {

}
