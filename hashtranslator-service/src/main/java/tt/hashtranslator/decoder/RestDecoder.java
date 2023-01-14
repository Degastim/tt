package tt.hashtranslator.decoder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tt.hashtranslator.dao.ApplicationDao;
import tt.hashtranslator.entity.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RestDecoder {
    @Value("${external.errorCode}")
    private String errorCode;
    @Value("${external.url}")
    private String externalServiceURL;
    private final ApplicationDao applicationDao;

    public RestDecoder(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Async
    public void sendHashes(Application application) {
        log.info("Sending application {}", application);
        if (application.getHashes().size() == 0) {
            application.setResults(new ArrayList<>());
            applicationDao.save(application);
            return;
        }
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = externalServiceURL;
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("hash", String.join(";", application.getHashes()));
        uriVariables.put("hash_type", "md5");
        uriVariables.put("email", "pesep99962@tingn.com");
        uriVariables.put("code", "a0dba70b9f25eb4d");
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class, uriVariables);
        String responseBody = response.getBody();
        String[] applicationDecryptResults = responseBody.split(";");
        List<String> applicationDecryptResultList = new ArrayList<>();
        for (String applicationDecryptResult : applicationDecryptResults) {
            String result = applicationDecryptResult.contains(errorCode) ? "" : applicationDecryptResult;
            applicationDecryptResultList.add(result);
        }
        application.setResults(applicationDecryptResultList);
        applicationDao.save(application);
    }
}
