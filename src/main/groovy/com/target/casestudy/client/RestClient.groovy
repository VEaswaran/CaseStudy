package com.target.casestudy.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.client.RestTemplate

import javax.inject.Named

@Named
class RestClient {

    @Autowired
    RestTemplate restTemplate

    Map get(URI uri) {
        try {
            Map map = restTemplate.getForObject(uri, Map)
        } catch (Exception ex) {
            throw ex
        }
    }
}
