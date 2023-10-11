package com.dalencon.DesafioPicPay.services;

import com.dalencon.DesafioPicPay.dtos.NotificationDTO;
import com.dalencon.DesafioPicPay.usuario.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://y79go.wiremockapi.cloud/notify", notificationRequest, String.class);

        Boolean notification = HttpStatus.CREATED.equals(notificationResponse.getStatusCode());

        if(!notification){
            System.out.println("Erro ao enviar notificação");
            throw new Exception("Serviço de notificação está fora do ar");
        }
    }
}
