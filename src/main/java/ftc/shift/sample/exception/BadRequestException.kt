package ftc.shift.sample.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class BadRequestException(text: String) : RuntimeException(text)