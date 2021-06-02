package com.goatfinder.builder ;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GoatAnalyzerTest {

    private String fileName;
    private Map<String, Opinion> opinion;
    private IParser parser;
    private GoatAnalyzer analyse;
    private List<Double> list;



    @BeforeEach
    @Test
    public void testDatasetUp() {

        opinion = new HashMap<>();
        opinion.put("G", Opinion.MEDIUM_POSITIVE);
        opinion.put("TOV", Opinion.MEDIUM_NEGATIVE);
        opinion.put("PTS", Opinion.LOW_NEGATIVE);
        opinion.put("DUMMY", Opinion.STRONG_POSITIVE);
        opinion.put("FAKE", Opinion.STRONG_POSITIVE);

        fileName = "data/nba2021test.txt";
        IGoatMaker goatMakerSpy = Mockito.spy(GoatMaker.class);

        parser = new BasketballParser(fileName, opinion, goatMakerSpy);
        analyse = new BasketballAnalyzer(parser);


        list = new ArrayList<>();
        list.add(2.0);list.add(3.0); list.add(88.0);list.add(14.0);

    }

    @Test
    @DisplayName("Test Mean")
    public void testMean(){

        Assertions.assertEquals( 4.067188319090693,analyse.getDataMeans().get("FAKE"));

    }

    @Test
    @DisplayName("Test Standard Deviation")
    public void testStandardDeviation(){
        Assertions.assertEquals( 3.689259939708274,analyse.getDataStandardDeviations().get("FAKE"));

    }

    @Test
    @DisplayName("Test Median Data")
    public void testDataMedian(){

        analyse.getDataMedian();
        Assertions.assertEquals(2.7368540971714093,analyse.getDataMedian().get("FAKE") );
    }

    @Test
    @DisplayName("Test Median")
    public void testMedian(){
        Assertions.assertEquals(8.5, GoatAnalyzer.GoatMath.median(list));
    }

    @Test
    @DisplayName("Test Z normaliser")
    public void testNormaliser(){
        Assertions.assertEquals(4, GoatAnalyzer.normalize(15, 3, 3));
    }

    @Test
    @DisplayName("Check Period Range")
    public void testPeriodRange(){
        Assertions.assertAll(() -> Assertions.assertEquals(1,analyse.periodMultiplier(analyse.dataSet.getDataRows().get(0))));


    }

    @Test
    @DisplayName("Check Skew Test accuracy")
    public void testDataSkew(){
        Assertions.assertAll(() -> Assertions.assertEquals(0.9496752023249946, analyse.getDataSkew().get("FAKE")),
                () -> Assertions.assertEquals(-0.8906732563233373, analyse.getDataSkew().get("DUMMY")),
                () -> Assertions.assertEquals(-0.3751799020863713, analyse.getDataSkew().get("PTS")));
    }

    @Test
    @DisplayName("Test reflection mechanism")
    public void testReflection(){

        List<Double> copy = new ArrayList<>(list);
        GoatAnalyzer.reflectData(list);
        Assertions.assertNotEquals(copy ,list);

    }

    @Test
    @DisplayName("Period multiplier")
    public void testPeriodMultiplier(){

        Assertions.assertEquals(17,analyse.getPeriodRange());

    }

    @Test
    @DisplayName("Check Skew logic")
    public void skewTest(){
        Assertions.assertAll(() -> Assertions.assertTrue(analyse.getSkewTestResults().get("FAKE")),
                () -> Assertions.assertTrue(analyse.getSkewTestResults().get("DUMMY")),
                () -> Assertions.assertFalse(analyse.getSkewTestResults().get("PTS")));
    }


    @Test
    @DisplayName("Check if Column data has been updated when skew test condition is met")
    public void updatedColumnData(){
        list = new ArrayList<>();
        list.add(25.8);list.add(29.0); list.add(29.7);list.add(22.5);

        Assertions.assertAll(() -> Assertions.assertNotEquals(list, analyse.getDataSet().getDataCols().get("FAKE")),
                () -> Assertions.assertEquals(list, analyse.getDataSet().getDataCols().get("PTS")));
    }

    @Test
    @DisplayName("Check if Row data has been updated")
    public void updatedRowData(){
        analyse.skewDataUpdateRows();
        Assertions.assertNotEquals(2, analyse.getDataSet().getDataRows().get(0).getGoatStats().getStatHolder().get("FAKE") );

    }




}
