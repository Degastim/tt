package tt.hashtranslator.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import tt.hashtranslator.entity.Application;
import tt.hashtranslator.service.SequenceGeneratorService;

@Component
@AllArgsConstructor
public class ApplicationModelListener extends AbstractMongoEventListener<Application> {
    private SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Application> event) {
        Application application = event.getSource();
        if (application.getId() < 1) {
            application.setId(sequenceGenerator.generateSequence(Application.SEQUENCE_NAME));
        }
    }
}
