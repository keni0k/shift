package ftc.shift.sample

object Utils {

    const val BASE_URL = "https://secretsanta2020.herokuapp.com/subscribe/"

    fun getLinkById(id: Long): String {
        return BASE_URL + id
    }

}