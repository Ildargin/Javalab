import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Input {
    private String type;
    private String name;
    private String placeholder;
}
