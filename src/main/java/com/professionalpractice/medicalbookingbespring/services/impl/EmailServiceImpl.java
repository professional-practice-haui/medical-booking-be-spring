package com.professionalpractice.medicalbookingbespring.services.impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.professionalpractice.medicalbookingbespring.dtos.HealthFormDTO;
import com.professionalpractice.medicalbookingbespring.services.EmailService;
import com.professionalpractice.medicalbookingbespring.utils.DateFormat;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMessage(String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setFrom("nodemailer.testing.2003@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
    }

    @Override
    public void sendHealthFormConfirmation(HealthFormDTO healthFormDto) {
        String to = healthFormDto.getEmail();
        String subject = "Xác nhận đơn khám bệnh";

        String content = "<html><body>";
        content += "<h2>Kính gửi " + healthFormDto.getNamePatient() + ",</h2>";
        content += "<p>Đơn khám bệnh của bạn đã được chấp nhận.</p>";
        content += "<h3>Thông tin chi tiết:</h3>";
        content += "<ul>";
        content += "<li>Ngày khám: " + DateFormat.format(healthFormDto.getShift().getDate()) + "</li>";
        content += "<li>Ca khám: " + healthFormDto.getShift().getTime() + "</li>";
        content += "<li>Lý do khám: " + healthFormDto.getReason() + "</li>";
        content += "<li>Chuyên khoa: " + healthFormDto.getShift().getDoctor().getDepartment().getName() + "</li>";
        content += "<li>Bác sĩ: " + healthFormDto.getShift().getDoctor().getName() + "</li>";
        content += "<li>Vị trí phòng khám: " + healthFormDto.getShift().getPlace() + "</li>";
        // content += "<li>Số thứ tự: " + healthFormDto.getStt() + "</li>";
        content += "</ul>";
        content += "<p>Vui lòng đến đúng giờ hẹn khám.</p>";
        content += "<p>Trân trọng,</p>";
        content += "<p>YouMed</p>";
        content += "</body></html>";

        sendMessage(to, subject, content);
    }

    @Override
    public void sendHealthFormRejection(HealthFormDTO healthFormDTO) {
        String to = healthFormDTO.getEmail();
        String subject = "Thông báo về đơn khám bệnh";

        String content = "<html><body>";
        content += "<h2>Kính gửi " + healthFormDTO.getNamePatient() + ",</h2>";
        content += "<p>Chúng tôi rất tiếc phải thông báo rằng đơn khám bệnh của bạn đã bị từ chối.</p>";
        if (healthFormDTO.getDeniedReason() != null) {
            content += "<p>Lý do: " + healthFormDTO.getDeniedReason() + "</p>";
        }
        content += "<p>Vui lòng liên hệ với chúng tôi nếu bạn có bất kỳ thắc mắc nào.</p>";
        content += "<p>Trân trọng,</p>";
        content += "<p>YouMed</p>";
        content += "</body></html>";

        sendMessage(to, subject, content);
    }
}
