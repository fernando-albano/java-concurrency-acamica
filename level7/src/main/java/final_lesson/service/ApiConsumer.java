package final_lesson.service;

import final_lesson.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class ApiConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ApiConsumer.class);

    private RestTemplate restTemplate = new RestTemplate();

    public Country getCountry(String code) {
        String url = "https://restcountries.eu/rest/v2/alpha/" + code;
        try {
            return restTemplate.getForObject(url, Country.class);
        } catch(Exception e) {
            logger.error("Error when consuming from: " + url, e);
            throw new RuntimeException(e);
        }
    }
}
