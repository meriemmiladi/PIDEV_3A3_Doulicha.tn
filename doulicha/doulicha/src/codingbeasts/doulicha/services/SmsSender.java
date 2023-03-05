/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

/**
 *
 * @author marie
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    public static final String ACCOUNT_SID = "AC83b2650feb64ea4ab17ab5239cafa28e";
    public static final String AUTH_TOKEN = "6e2a75354203361c383224e3d6ea25e3";

    public static void sendSms(String toNumber, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber("+12766461164"),
                messageBody)
            .create();

        System.out.println(message.getSid());
    }
}
