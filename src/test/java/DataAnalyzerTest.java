import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DataAnalyzerTest {
    //these are all terrible tests
    @Test
    public void testMean(){
        ArrayList<Double> dummyList = new ArrayList<>();

        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";

       // DataAnalyzer solve = new DataAnalyzer(fileName);

        for(int i = 0; i < 10; i++) {
            dummyList.add(i + 1.0);

        }


        //Assertions.assertEquals(5.5, solve.mean(dummyList));
    }

    @Test
    public void testMedian(){
        ArrayList<Double> dummyList = new ArrayList<>();
        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";

        //DataAnalyzer solve = new DataAnalyzer(fileName);

        for(int i = 0; i < 10; i++) {
            dummyList.add(i + 1.0);

        }
        //solve.mean(dummyList);

        //Assertions.assertEquals(5.5, solve.median(dummyList));

    }
    @Test
    public void testStandardDeviation(){
        ArrayList<Double> dummyList = new ArrayList<>();
        //String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";

        //DataAnalyzer solve = new DataAnalyzer(fileName);
        for(int i = 0; i < 10; i++) {
            dummyList.add(i + 1.0);

        }
        //Assertions.assertEquals(2.8722813232690143, solve.standardDeviation(dummyList));
    }

    @Test
    public void testDataMean(){
        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";

        //DataAnalyzer solve = new DataAnalyzer(fileName);

        //Assertions.assertEquals(3.1999999999999997,solve.dataMean().get("PTS") );



    }

    @Test
    public void testDataMedian(){
        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";

        //DataAnalyzer solve = new DataAnalyzer(fileName);

        //Assertions.assertEquals(3.1999999999999997,solve.dataMedian().get("PTS") );



    }
    @Test
    public void testDataStandardDeviation(){
        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";

       // DataAnalyzer solve = new DataAnalyzer(fileName);


        //Assertions.assertEquals(4.1012193308819755,solve.dataStandardDeviation().get("PTS") );


    }

}
