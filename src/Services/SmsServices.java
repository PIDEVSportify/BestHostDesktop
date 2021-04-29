package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsServices {
    private static final String   SID="AC073729f320dbc1e61c7d6453752b8d18";
    private static  final String TOKEN = "48a3f762b04248a6c04496a0d807822b"  ;
    private  static final String PHONE_NUMBER = "+12242573875";

       public static void sendSMS(String phone_number)
       {
           Twilio.init(SID,TOKEN);
           Message message= Message.creator(
                   new PhoneNumber("+216"+phone_number),
                   new PhoneNumber(PHONE_NUMBER),"BestHost vous souhaite la bienvenue"
           ).create();
           System.out.println(message.getSid());
       }

}
