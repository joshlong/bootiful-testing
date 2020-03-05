import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

Contract.make {
    description("should return all Reservations")
    request {
        url("/reservations")
        method("GET")
    }
    response {
        status(HttpStatus.OK.value())
        body([[name: "Jane", id: "1"]])
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}
