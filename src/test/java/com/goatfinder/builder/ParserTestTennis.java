package com.goatfinder.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserTestTennis {

    private String fileName;
    private Map<String, Opinion> opinion;
    private TennisParser parser;


    @BeforeEach
    @Test
    public void testDatasetUp(){
        opinion = new HashMap<>();

        opinion.put("grandSlams", Opinion.MEDIUM_POSITIVE);
        opinion.put("tourFinals", Opinion.LOW_POSITIVE);
        opinion.put("altFinals", Opinion.LOW_POSITIVE);
        opinion.put("masters", Opinion.STRONG_POSITIVE);
        opinion.put("olympics", Opinion.STRONG_POSITIVE);
        opinion.put("titles", Opinion.STRONG_POSITIVE);
        opinion.put("weeksAtNo1", Opinion.LOW_POSITIVE);


        fileName = "data/tennisDataDummy.csv";
        parser = new TennisParser(fileName, opinion);

    }

    @Test
    @DisplayName("Parse Ages correctly")
    public void testParseAge(){
       Assertions.assertAll(()->Assertions.assertEquals(30,TennisParser.parseAge("1990-12-12")));

    }

    @Test
    @DisplayName("Parse games won correctly")
    public void testGamesWon(){
        Assertions.assertAll(()->Assertions.assertEquals(999,TennisParser.parseGamesWon("999-1")));
    }

    @Test
    @DisplayName("Parse games lost correctly")
    public void testGamesLost(){
        Assertions.assertAll(()->Assertions.assertEquals(1,TennisParser.parseGamesLost("999-1")));

    }


    @Test
    @DisplayName("Parse Periods correctly")
    public void testParsePeriod(){
        Assertions.assertAll(()->Assertions.assertEquals(1000,parser.parsePeriod("999-1")));

    }


    @DisplayName("read Data Correctly")
    @Test
    void testRead(){

        List<Double> list = new ArrayList<>();
        list.add(20.0);list.add(18.0); list.add(20.0);list.add(8.0);list.add(8.0);list.add(14.0);


        Assertions.assertAll(()->Assertions.assertEquals("Roger Federer's Goatness is: 0.0",parser.getDataRows().get(0).toString()),
                ()-> Assertions.assertEquals(1509.0,parser.getDataRows().get(0).getGoatStats().getPeriod()),
                ()-> Assertions.assertEquals(20,parser.getDataRows().get(0).getGoatStats().getStatHolder().get("grandSlams")),
                ()-> Assertions.assertNull(parser.getDataRows().get(0).getGoatStats().getStatHolder().get("hello") ),
                ()-> Assertions.assertEquals(list,parser.getDataCols().get("grandSlams")),
                ()-> Assertions.assertEquals(6,parser.getDataCols().get("tourFinals").size()),
                ()-> Assertions.assertEquals(Opinion.LOW_POSITIVE, parser.getGoatOpinions().get("tourFinals")));

    }
}
