package tt.hashtranslator.mapper;

import org.springframework.stereotype.Component;
import tt.hashtranslator.dto.ApplicationIdResponse;
import tt.hashtranslator.entity.Application;

@Component
public class ApplicationIdResponseMapper {
    public ApplicationIdResponse toDTO(Application entity) {
        return new ApplicationIdResponse(entity.getId());
    }
}
