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
import java.util.Calendar;

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
                "        We have received a request to reset the password associated with your account. In order to proceed with\n" +
                "        the password reset process, we need to confirm that the email address associated with your account is valid.<br>\n" +
                "        Please click on the following link to confirm your email address:<br>\n" +
                "      </p>";
        String verifyURL= siteURL+ "/verify?code="+passengers.getVerificationCode();
        content+= "      <button style=\"background-color: #2093c7; \n" +
                "   border: none;\n" +
                "  color: #ffffff;\n" +
                "  padding: 16px 32px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "  font-size: 16px;\n" +
                "  margin: 4px 2px;\n" +
                "  justify-content: center;\n" +
                "  transition-duration: 0.4s;\n" +
                "  cursor: pointer; border-radius: 20px\"><a href=\"" + verifyURL + "\">Confirm Your Email Address</a></button>";
        content+=  "  <tr>\n" +
                "    <td style=\"padding: 10px 20px; color: #FFFFFF;background: #5cb1e7\">\n" +
                "      <p>If you did not create an account with us, please ignore this email.\n" +
                "        <br>\n" +
                "        Thank you for choosing TianFlight. We look forward to serving you!<br>\n" +
                "        Best regards!<br></div></p>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "</body>";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    public Passengers findByCode(String code) {
        return iPassengerRepository.findByVerificationCode(code);
    }

    @Override
    public boolean verify(String verificationCode) {
        Passengers passengers = iPassengerRepository.findByVerificationCode(verificationCode);
        Calendar cal = Calendar.getInstance();
        if (passengers == null || passengers.isEnabled()) {
            return false;
        }else if((passengers.getExpiryDate().getTime()- cal.getTime().getTime()) <= 0){
            iPassengerRepository.delete(passengers);
            return false;
        }else {
            passengers.setVerificationCode(null);
            passengers.setEnabled(true);
            iPassengerRepository.save(passengers);
            return true;
        }
    }

    @Override
    public void reset(Passengers passengers) {
        String randomCode = RandomString.make(64);
        passengers.setVerificationCode(randomCode);
        iPassengerRepository.save(passengers);
    }

    @Override
    public void sendVerificationReset(Passengers passengers, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = passengers.getAccountUser().getEmail();
        String fromAddress = "lsyh31@gmail.com";
        String senderName = "Tian flight";
        String subject = "Confirm your email address";
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
                "        We have received a request to reset the password associated with your account. In order to proceed with\n" +
                "        the password reset process, we need to confirm that the email address associated with your account is valid.<br>\n" +
                "        Please click on the following link to confirm your email address:<br>\n" +
                "      </p>";
        String verifyURL= siteURL+ "/verify_reset?code="+passengers.getVerificationCode();
        content+= "      <button style=\"background-color: #2093c7; \n" +
                "   border: none;\n" +
                "  color: #ffffff;\n" +
                "  padding: 16px 32px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "  font-size: 16px;\n" +
                "  margin: 4px 2px;\n" +
                "  justify-content: center;\n" +
                "  transition-duration: 0.4s;\n" +
                "  cursor: pointer; border-radius: 20px\"><a href=\"" + verifyURL + "\">Confirm Your Email Address</a></button>";
        content+= "  <tr>\n" +
                "    <td style=\"padding: 10px 20px; color: #FFFFFF;background: #5cb1e7\">\n" +
                "      <p>If you did not request a password reset, please disregard this message.\n" +
                "        <br>\n" +
                "        If you believe that your account has been compromised, please contact our customer support team immediately.<br>\n" +
                "        Thank you,<br></div></p>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "</body>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    public boolean verifyReset(String verificationCodeReset) {
        Passengers passengers = iPassengerRepository.findByVerificationCode(verificationCodeReset);
        Calendar cal = Calendar.getInstance();
        if(passengers==null||(passengers.getExpiryDate().getTime()- cal.getTime().getTime()) <= 0){
            return false;
        }else {
            passengers.setVerificationCode(null);
            iPassengerRepository.save(passengers);
            return true;
        }
    }

    @Override
    public void reset_pw(Passengers passengers, String new_pw) {
        String encodedPassword = passwordEncoder.encode(new_pw);
        Passengers passenger=iPassengerRepository.findByAccountUser_Email(passengers.getAccountUser().getEmail());
        passenger.getAccountUser().setPasswords(encodedPassword);
        iPassengerRepository.save(passenger);
    }

    @Override
    public Passengers findById(int id) {
        return iPassengerRepository.findById(id);
    }

}
