package ftc.shift.sample;

import java.security.SecureRandom;

public class Utils {

    public static final String BASE_URL = "localhost:8081/groups/";

    public static String getLinkById(long id){
        return BASE_URL + id;
    }

}
