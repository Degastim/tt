package tt.hashtranslator.service;

import tt.hashtranslator.dto.ApplicationIdResponse;
import tt.hashtranslator.dto.HashRequest;
import tt.hashtranslator.dto.HashResultResponse;

public interface ApplicationService {
    ApplicationIdResponse create(HashRequest hashes);

    HashResultResponse findById(long id);
}
