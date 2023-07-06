package com.example.case_study.service.passengers_service.impl;

import com.example.case_study.model.Passengers;
import com.example.case_study.repository.IPassengerRepository;
import com.example.case_study.service.passengers_service.IPassengersService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@Component
public class PassengersServiceImpl implements IPassengersService {
    @Autowired
    private IPassengerRepository iPassengerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Page<Passengers> findByPassengers(String name, Pageable pageable) {
        return iPassengerRepository.findByPassengers(name, pageable);
    }

    @Override
    public Passengers findByIdPassengers(Integer id) {
        return iPassengerRepository.findById(id).get();
    }

    @Override
    public void create(Passengers passengers) {
        String encodedPassword = passwordEncoder.encode(passengers.getAccountUser().getPasswords());
        passengers.getAccountUser().setPasswords(encodedPassword);
        String randomCode = RandomString.make(64);
        passengers.setVerificationCode(randomCode);
        passengers.setEnabled(false);
        iPassengerRepository.save(passengers);
    }

    @Override
    public void update(Passengers passengers) {
        iPassengerRepository.save(passengers);
    }

    @Override
    public void delete(Integer id) {
        iPassengerRepository.deleteById(id);
    }

    @Override
    public Passengers findByIdAccount(Integer id) {
        if (iPassengerRepository.findPassengersByAccountUser_Id(id) != null){
            return iPassengerRepository.findPassengersByAccountUser_Id(id);
        }else {
            return null;
        }

    }

    @Override
    public Passengers findByEmail(String email) {
        return iPassengerRepository.findByAccountUser_Email(email);
    }

    @Override
    public void sendVerificationEmail(Passengers passengers, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = passengers.getAccountUser().getEmail();
        String fromAddress = "lsyh31@gmail.com";
        String senderName = "Tian flight";
        String subject = "Confirm your email address";
        String content = "<p>Dear "+passengers.getName() + ",</p><br>"
                + "Thank you for creating an account with TianFlight. To complete the registration process, " +
                "we need you to confirm your email address by clicking on the link below:<br>";
        String verifyURL= siteURL+ "/user/verify?code="+passengers.getVerificationCode();
        content+= "<button style='background-color: #a0a7a0; \n" +
                "  border: none; \n" +
                "  color: black; \n" +
                "  padding: 12px 24px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "  font-size: 16px; \n" +
                "  margin: 4px 2px; \n" +
                "  cursor: pointer; \n" +
                "  border-radius: 8px;'><a href=\"" + verifyURL + "\">Confirm Your Email Address</a></button>";
        content+= "<br><br>If you did not create an account with us, please ignore this email.\n" +
                "\n" +
                "Thank you for choosing TianFlight. We look forward to serving you! <br><br>\n" +
                "\n" +
                "Best regards!<br>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
