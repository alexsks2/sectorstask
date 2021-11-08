import lombok.Data;

@Data
public class Sector {
    private int id;
    private String name;
    private Sector parent;
}
