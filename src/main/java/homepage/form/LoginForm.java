package homepage.form;

import lombok.Data;

@Data
public class LoginForm {

    private String id;
    
    private String password;
    
    private String verificationCode;
    
    private String verificationCodeSend;
}
