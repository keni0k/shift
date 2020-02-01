package ftc.shift.sample

import org.springframework.web.client.RestTemplate

object Utils {

    private const val BASE_URL = "https://secretsanta2020.herokuapp.com/subscribe/"

    fun getLinkById(id: Long): String? {
        val longLink = BASE_URL + id
        val template = RestTemplate()
        return template.getForObject("https://clck.ru/--?url=$longLink",  String::class.java)
    }

}