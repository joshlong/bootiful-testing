import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {
    description "should return all Reservations"
    request {
        method GET()
        url "/reservations"
    }
    response {
        headers {
            contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        }
        status(200)
        body([[id: 1, reservationName: "Jane"], [id: 2L, reservationName: "Josh"]])
    }
}