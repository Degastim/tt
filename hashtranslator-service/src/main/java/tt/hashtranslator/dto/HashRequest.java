package tt.hashtranslator.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HashRequest {
    private List<String> hashes;
}
