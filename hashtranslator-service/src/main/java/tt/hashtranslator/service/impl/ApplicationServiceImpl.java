package tt.hashtranslator.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tt.hashtranslator.dao.ApplicationDao;
import tt.hashtranslator.decoder.RestDecoder;
import tt.hashtranslator.dto.ApplicationIdResponse;
import tt.hashtranslator.dto.HashRequest;
import tt.hashtranslator.dto.HashResultResponse;
import tt.hashtranslator.entity.Application;
import tt.hashtranslator.exception.InvalidFieldValueException;
import tt.hashtranslator.exception.ResourceNotFoundedException;
import tt.hashtranslator.mapper.ApplicationIdResponseMapper;
import tt.hashtranslator.mapper.HashResultResponseMapper;
import tt.hashtranslator.service.ApplicationService;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationDao applicationDao;
    private ApplicationIdResponseMapper applicationIdResponseMapper;
    private RestDecoder restDecoder;
    private HashResultResponseMapper hashResultResponseMapper;

    @Override
    public ApplicationIdResponse create(HashRequest hashes) {
        if(hashes.getHashes()==null){
            throw new InvalidFieldValueException("Hashes hasn't value");
        }
        Application application = new Application();
        application.setHashes(hashes.getHashes());
        Application daoApplication = applicationDao.save(application);
        restDecoder.sendHashes(daoApplication);
        return applicationIdResponseMapper.toDTO(daoApplication);
    }

    @Override
    public HashResultResponse findById(long id) {
        Application application = applicationDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundedException("No such application was found."));
        return hashResultResponseMapper.toDTO(application);
    }
}
