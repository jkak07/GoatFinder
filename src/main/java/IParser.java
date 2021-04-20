import java.util.List;
import java.util.Map;

public interface IParser {

    void read();
    Map<String, List<Double>> getDataCols();
    List<? extends IGoat > getDataRows();

}
