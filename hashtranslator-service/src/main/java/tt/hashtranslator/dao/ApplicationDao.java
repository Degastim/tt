package tt.hashtranslator.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tt.hashtranslator.entity.Application;

@Repository
public interface ApplicationDao extends MongoRepository<Application, Long> {
}
