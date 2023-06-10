package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.services.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Map;

@Controller
@PreAuthorize("isAuthenticated()")
public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/thongke-thang")
    public String thongKeThang(Model model) {
        int year = LocalDate.now().getYear();
        Map<Integer, Double> revenueByMonth = thongKeService.thongKeTongTienTheoThang(year);
        model.addAttribute("revenueByMonth", revenueByMonth);
        return "hoadon/thongke-thang";
    }

    @GetMapping("/thongkethang-data")
    @ResponseBody
    public Map<Integer, Double> thongKeThangData(@RequestParam("year") int year) {
        return thongKeService.thongKeTongTienTheoThang(year);
    }

    @GetMapping("/thongke-ngay")
    public String thongKeNgay(Model model) {
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        Map<Integer, Double> revenueByDay = thongKeService.thongKeTongTienTheoNgay(month, year);
        model.addAttribute("revenueByDay", revenueByDay);
        return "hoadon/thongke-ngay";
    }

    @GetMapping("/thongkengay-data")
    @ResponseBody
    public Map<Integer, Double> thongKeNgayData(@RequestParam("month") int month,
                                                @RequestParam("year") int year) {
        return thongKeService.thongKeTongTienTheoNgay(month, year);
    }
}
