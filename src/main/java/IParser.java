import java.time.Period;
import java.util.List;
import java.util.Map;

public interface IParser {

    String PERIOD =  "period";
    void read();
    Map<String, List<Double>> getDataCols();
    List<? extends IGoat > getDataRows();
    Map<String,Opinion> getGoatOpinions();

}
