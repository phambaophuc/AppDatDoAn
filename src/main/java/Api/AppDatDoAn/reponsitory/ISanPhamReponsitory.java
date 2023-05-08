package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.Sanpham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISanPhamReponsitory extends JpaRepository<Sanpham, Long> {
    @Query("SELECT sp FROM Sanpham sp WHERE " +
            "sp.tensanpham LIKE CONCAT('%',:query, '%')")
    List<Sanpham> searchByName(String query);

    @Query("SELECT sp FROM Sanpham sp WHERE sp.loaisanpham.maloai = :query")
    List<Sanpham> searchByTheLoai(Long query);

    @Query("SELECT sp FROM Sanpham sp WHERE LOWER(sp.tensanpham) LIKE %:name% AND sp.loaisanpham.maloai = :theloaiId")
    List<Sanpham> searchByNameAndTheLoai(@Param("name") String name, @Param("theloaiId") Long theloaiId);

}
