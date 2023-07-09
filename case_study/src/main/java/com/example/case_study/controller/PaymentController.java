package com.example.case_study.controller;

import com.example.case_study.config.PaymentConfig;

import com.example.case_study.model.BookingTicket;
import com.example.case_study.model.ChairFlight;
import com.example.case_study.model.Passengers;
import com.example.case_study.service.booking_ticket.IBookingTicketService;
import com.example.case_study.service.chairflight_service.IChairFlightService;
import com.example.case_study.service.passengers_service.IPassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private IPassengersService passengersService;
    @Autowired
    private IChairFlightService chairFlightService;
    @Autowired
    private IBookingTicketService bookingTicketService;

    @PostMapping("/create")
    public ModelAndView create(@RequestParam int quantity, @RequestParam int total, @RequestParam int idPassenger) throws UnsupportedEncodingException {
        BookingTicket bookingTicket = new BookingTicket();
        Passengers passengers = passengersService.findByIdPassengers(idPassenger);
        bookingTicket.setPassenger(passengers);
        bookingTicket.setQuantity(quantity);
        bookingTicket.setBookingDate(String.valueOf(LocalDate.now()));
        bookingTicketService.save(bookingTicket);
        String orderType = "170000";
//        long amount = Integer.parseInt(req.getParameter("amount"))*100;
//        String bankCode = req.getParameter("bankCode");


        String amount = String.valueOf(total * 100);
        String vnp_TxnRef = PaymentConfig.getRandomNumber(8);
//        String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = PaymentConfig.vnp_TmnCode;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.vnp_Version);
        vnp_Params.put("vnp_Command", PaymentConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
//        String locate = req.getParameter("language");
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
        vnp_Params.put("vnp_ReturnUrl", PaymentConfig.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", "0:0:0:0:0:0:0:1");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PaymentConfig.hmacSHA512(PaymentConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig.vnp_PayUrl + "?" + queryUrl;
//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(job));
//        PaymentResDto paymentResDto = new PaymentResDto();
//        paymentResDto.setStatus("OK");
//        paymentResDto.setMessage("SUCCESSFULLY");
//        paymentResDto.setURL(paymentUrl);
        return new ModelAndView("redirect:" + paymentUrl);
    }

    @GetMapping("/return")
    public String showReturn(@RequestParam String vnp_Amount,
                             @RequestParam String vnp_TmnCode, @RequestParam String vnp_ResponseCode
            , Model model, @SessionAttribute List<ChairFlight> listChair) {
        if(vnp_ResponseCode.equals("00")){
            for (ChairFlight c : listChair) {
                c.setStatusChair(true);
                this.chairFlightService.update(c);
                model.addAttribute("message","Thanh toán thành công");
            }
        }else {
            model.addAttribute("message","Thanh toán thất bại");
        }
        listChair.clear();
//        model.addAttribute("amount", vnp_Amount);
        return "return";
    }
}
