package tt.hashtranslator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HashResultResponse {
    private List<DecodeHashResult> hashes;
}
