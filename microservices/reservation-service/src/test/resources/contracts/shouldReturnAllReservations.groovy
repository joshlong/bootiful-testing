import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    description "should return all reservations"
    request {
        url "/reservations"
        method GET()
    }
    response {
        status 200
        body([[id: 1L, reservationName: "Jane"], [id: 2L, reservationName: "Josh"]])
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
        }
    }
}