package com.example.case_study.service.booking_ticket;

import com.example.case_study.dto.ReceiveBookingDto;
import com.example.case_study.model.AccountUser;
import com.example.case_study.model.BookingTicket;
import com.example.case_study.model.Passengers;
import com.example.case_study.repository.IAccountRepository;
import com.example.case_study.repository.IBookingTicketRepository;
import com.example.case_study.repository.IPassengerRepository;
import com.example.case_study.repository.IReceiveBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@Component
public class BookingTicketService implements IBookingTicketService{
    @Autowired
    private IBookingTicketRepository iBookingTicketRepository;

    @Autowired
    private IReceiveBookingRepo receiveBookingRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void createAuto(BookingTicket bookingTicket) {
        iBookingTicketRepository.save(bookingTicket);
    }

    @Override
    public void save(BookingTicket bookingTicket) {
        iBookingTicketRepository.save(bookingTicket);
    }

    @Override
    public BookingTicket findByPassenger_Id(int id) {
        return iBookingTicketRepository.findById(id).orElse(null);
    }

    @Override
    public BookingTicket findById(int id) {
        return iBookingTicketRepository.findById(id).orElse(null);
    }

    @Override
    public void sendEmail(Passengers passengers, String siteURL) throws MessagingException, UnsupportedEncodingException {
        BookingTicket bookingTicket=iBookingTicketRepository.findByPassenger_Id(passengers.getId());
        ReceiveBookingDto receiveBookingDto=receiveBookingRepo.getBookingTicket(bookingTicket.getIdBookingTicket());
        String toAddress = passengers.getAccountUser().getEmail();
        String fromAddress = "lsyh31@gmail.com";
        String senderName = "Tian flight";
        String subject = "Ticket Details: " +bookingTicket.getIdBookingTicket();
        String content = "<body  style=\"margin: 0; padding: 0\">\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\">\n" +
                "  <tr>\n" +
                "    <td  style=\" background: #5cb1e7; \">\n" +
                "      <img src=\"static/home/images/image (1).png\" style=\"height: 100px;width: 120px\" />\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;background: url('https://cafebiz.cafebizcdn.vn/thumb_w/600/162123310254002176/2022/6/7/photo1654573349126-16545733492221043829261.jpg') no-repeat center center;background-size: cover;\">\n" +
                "\n" +
                "      <p>Dear<span style=\"color: #0db9e0;font-size: 14px;font-weight: bold;\"> \""+passengers.getName() +"\" </span></p>\n" +
                "      <p >\n" +
                "        I am writing to provide you with the detailed information about the ticket" +bookingTicket.getIdBookingTicket()+ "that you have requested. Please find below the relevant details:<br>\n" +
                "      </p>";
        content+="<div class=\"cardWrap\" style=\"width: 30em;\n" +
                "            margin: 1em auto;\n" +
                "            color: #fff;\n" +
                "            font-family: sans-serif;\">\n" +
                "        <div class=\" card cardLeft\" style=\"  background: linear-gradient(to bottom, #3d6de8 0%, #3da6e8 26%, #597070 26%, #597070 100%);\n" +
                "            height: 14em;\n" +
                "            float: left;\n" +
                "            position: relative;\n" +
                "            padding: 1em; border-top-left-radius: 8px;\n" +
                "            border-bottom-left-radius: 8px;\n" +
                "            width: 16em; \">\n" +
                "          <h1 style=\"font-size: 1em;\n" +
                "            margin-top: 7px;\">TianFlight</h1>\n" +
                "          <div class=\"title\" style=\" text-transform: uppercase;\n" +
                "            font-weight: normal; margin: 2em 0 0 0; font-size: .7em;\n" +
                "            color: #ffffff;\">\n" +
                "            <h3 style=\" margin: 5px 0 0 0;\">Ticket number</h3>\n" +
                "            <p style=\"margin: 1px\">" +bookingTicket.getIdBookingTicket()+ "</p>\n" +
                "          </div>\n" +
                "          <div class=\"name\" style=\" text-transform: uppercase;\n" +
                "            font-weight: normal;font-size: .7em;\n" +
                "            color: #ffffff;margin: .7em 0 0 0;\">\n" +
                "            <h3 style=\" margin: 5px 0 0 0;\">Passenger</h3>\n" +
                "            <span>"+ passengers.getName()+"</span>\n" +
                "          </div>\n" +
                "          <div class=\"seat\" style=\" text-transform: uppercase;\n" +
                "            font-weight: normal;font-size: .7em;\n" +
                "            color: #ffffff;margin: .7em 0 0 0;float: left;\">\n" +
                "            <h3 style=\" margin: 5px 0 0 0;\">Flight number</h3>\n" +
                "            <span>"+ receiveBookingDto.getNumberAirCraft()+"</span>\n" +
                "          </div>\n" +
                "          <div class=\"time\" style=\" text-transform: uppercase;\n" +
                "            font-weight: normal;margin: -0.5em 0 0 3em; float: left\">\n" +
                "            <h2 style=\"color: #62bcf5; font-size: 20px; margin-top:10px;margin-bottom: 1px\">"+receiveBookingDto.getDeparture()+"</h2>\n" +
                "            <span style=\"font-size: .7em;margin-top: 2px;\n" +
                "            color: #ffffff;\">Departure</span>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "        <div class=\"card cardRight\" style=\"  background: linear-gradient(to bottom, #3d87e8 0%, #3d79e8 26%, #597070 26%, #597070 100%);\n" +
                "            height: 14em;\n" +
                "            float: left;\n" +
                "            position: relative;\n" +
                "            padding: 1em;  width: 6.5em;\n" +
                "            border-left: .18em dashed #fff;\n" +
                "            border-top-right-radius: 8px;\n" +
                "            border-bottom-right-radius: 8px;\">\n" +
                "          <div class=\"eye\" style=\"text-align: center; position: relative;width: 2em;height: 1.5em;\n" +
                "            margin: 0 auto; font-size: 30px;\n" +
                "            border-radius: 1em/0.6em;\n" +
                "            z-index: 1;\"><i class=\"fa-solid fa-plane\"></i></div>\n" +
                "          <div class=\"number\" style=\"text-align: center;\n" +
                "           \">\n" +
                "                        <span style=\" margin-top: 10px;\n" +
                "            font-size: 13px;\n" +
                "            font-weight: bold;\n" +
                "            display: block;\n" +
                "            color: #ffffff;\">From</span>\n" +
                "            <p style=\"color: #ffffff;\n" +
                "            margin: 10px 0 0 0;\n" +
                "            font-size: 1em;\">"+receiveBookingDto.getAirport()+"</p>\n" +
                "          </div>\n" +
                "          <div class=\"number\" style=\"text-align: center;\n" +
                "           \">\n" +
                "                        <span style=\" margin-top: 10px;\n" +
                "            font-size: 13px;\n" +
                "            font-weight: bold;\n" +
                "            display: block;\n" +
                "            color: #ffffff;\">To</span>\n" +
                "            <p style=\"color: #ffffff;\n" +
                "            margin: 10px 0 0 0;\n" +
                "            font-size: 1em;\">"+receiveBookingDto.getDestination()+"</p>\n" +
                "          </div>\n" +
                "          <div class=\"barcode\"></div>\n" +
                "        </div>\n" +
                "      </div>\n" +
                "    </td>\n" +
                "  </tr>";
        content+=" <tr>\n" +
                "    <td style=\"padding: 10px 20px; color: #FFFFFF;background: #5cb1e7\">\n" +
                "      <p>\n" +
                "        If you have any further questions or concerns, please do not hesitate to contact me. Thank you for choosing\n" +
                "        TianLight and we look forward to serving you.<br> Best regards!</p>\n" +
                "    </td>\n" +
                "  </tr>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
