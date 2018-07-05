package com.target.casestudy.client

import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class RestClientSpecification extends Specification {
    RestClient restClient
    RestTemplate restTemplate

    def setup() {
        restTemplate = new RestTemplate()
        restClient = new RestClient(restTemplate: restTemplate)
    }

    def "[TestCase] Method to test RestClient"() {
        given:
        Map serviceResponse = ["product": ["items": ["1", "2"]]]
        URI uri = new URI("https://redsky.target.com/v2/pdp/tcin/13860248")
        Exception exception
        when:
        try {
            Map response = restClient.get(uri)
        } catch (Exception ex) {
            exception = ex
        }
        then:
        exception != null
        assert exception instanceof HttpClientErrorException
    }
}
