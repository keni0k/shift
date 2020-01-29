package ftc.shift.sample;

import java.security.SecureRandom;

public class Utils {

    public static final String BASE_URL = "https://secretsanta2020.herokuapp.com/subscribe/";

    public static String getLinkById(long id){
        return BASE_URL + id;
    }

}
