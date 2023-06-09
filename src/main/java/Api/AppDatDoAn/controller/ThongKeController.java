package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.services.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/thong-ke")
    public String thongKe(Model model) {
        int year = LocalDate.now().getYear();
        Map<Integer, Double> revenueByMonth = thongKeService.thongKeTongTienTheoThang(year);
        model.addAttribute("revenueByMonth", revenueByMonth);
        return "hoadon/thong-ke";
    }

    @GetMapping("/thongke-data")
    @ResponseBody
    public Map<Integer, Double> thongKeData(@RequestParam("year") int year) {
        return thongKeService.thongKeTongTienTheoThang(year);
    }
}
