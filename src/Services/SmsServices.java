package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsServices {
    private static final String   SID="AC073729f320dbc1e61c7d6453752b8d18";
    private static  final String TOKEN = "f671da52671a44f5a583f4aa6f6b9d08"  ;
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
