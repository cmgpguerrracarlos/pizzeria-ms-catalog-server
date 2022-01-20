import java.util.List;

public interface CatalogGenerator {
    public List<String> generateCatalog();
    String buildUid(String...elements);
    float calculatePrice(String uid);
}
