package Api.AppDatDoAn.services;

import Api.AppDatDoAn.reponsitory.IHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThongKeService {
    @Autowired
    private IHoaDonRepository hoaDonRepository;

    public Map<Integer, Double> thongKeTongTienTheoThang(int year) {
        List<Object[]> results = hoaDonRepository.getMonthlyRevenue(year);

        Map<Integer, Double> revenueByMonth = new HashMap<>();
        for (Object[] result : results) {
            int month = (int) result[0];
            double total = (double) result[1];
            revenueByMonth.put(month, total);
        }

        return revenueByMonth;
    }
}
