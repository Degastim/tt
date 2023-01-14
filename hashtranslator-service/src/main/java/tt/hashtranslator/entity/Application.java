package tt.hashtranslator.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("applications")
public class Application {
    @Id
    private long id;
    private List<String> hashes;
    private List<String> results;
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
}
