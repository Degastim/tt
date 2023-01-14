package tt.hashtranslator.mapper;

import org.springframework.stereotype.Component;
import tt.hashtranslator.dto.DecodeHashResult;
import tt.hashtranslator.dto.HashResultResponse;
import tt.hashtranslator.entity.Application;

import java.util.ArrayList;
import java.util.List;

@Component
public class HashResultResponseMapper {
    public HashResultResponse toDTO(Application entity) {
        List<DecodeHashResult> hashes = new ArrayList<>();
        for (String result : entity.getResults()) {
            hashes.add(new DecodeHashResult(result));
        }
        return new HashResultResponse(hashes);
    }
}
