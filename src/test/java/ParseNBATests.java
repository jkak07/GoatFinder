import com.goatfinder.builder.IParser;
import com.goatfinder.builder.Opinion;
import com.goatfinder.builder.ParserNBA;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseNBATests {

   private String fileName;
   private Map<String, Opinion> opinion;
   private ParserNBA parser;



    @BeforeEach
    @Test
    public void testDatasetUp(){
        opinion = new HashMap<>();

        opinion.put("MP", Opinion.MEDIUM_POSITIVE);
        opinion.put("3P%", Opinion.LOW_POSITIVE);
        opinion.put("FTA", Opinion.LOW_POSITIVE);
        opinion.put("TRB", Opinion.STRONG_POSITIVE);
        opinion.put("AST", Opinion.STRONG_POSITIVE);
        opinion.put("STL", Opinion.STRONG_POSITIVE);
        opinion.put("BLK", Opinion.LOW_POSITIVE);
        opinion.put("TOV", Opinion.MEDIUM_NEGATIVE);
        opinion.put("PF", Opinion.LOW_NEGATIVE);
        opinion.put("PTS", Opinion.STRONG_POSITIVE);

        fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2021test.txt";
        parser = new ParserNBA(fileName, opinion);


    }
    @Test
    @DisplayName("Parse names correctly")
    public void testParseName(){
        Assertions.assertAll(()->Assertions.assertEquals("",ParserNBA.parseName("\\test1")),
                ()-> Assertions.assertEquals("jason" ,ParserNBA.parseName("jason\\jjj")));
    }

    @DisplayName("read Data Correctly")
    @Test
    void testRead(){

        List<Double> list = new ArrayList<>();
        list.add(25.8);list.add(29.0); list.add(29.7);list.add(22.5);

        Assertions.assertAll(()->Assertions.assertEquals("LeBron James's Goatness is: 0.0",parser.getDataRows().get(0).toString()),
                ()-> Assertions.assertEquals("LeBron James",parser.getDataRows().get(0).getName()),
                ()-> Assertions.assertEquals("PG", parser.getDataRows().get(0).position()),
                ()-> Assertions.assertEquals(36.0,parser.getDataRows().get(0).getGoatStats().getPeriod()),
                ()-> Assertions.assertEquals(34.6,parser.getDataRows().get(0).getGoatStats().getStatHolder().get("MP")),
                ()-> Assertions.assertNull(parser.getDataRows().get(0).getGoatStats().getStatHolder().get("hello") ),
                ()-> Assertions.assertEquals(list,parser.getDataCols().get("PTS")),
                ()-> Assertions.assertEquals(4,parser.getDataCols().get("PF").size()),
                ()-> Assertions.assertEquals(Opinion.STRONG_POSITIVE, parser.getGoatOpinions().get("PTS"))
        );
    }

}
