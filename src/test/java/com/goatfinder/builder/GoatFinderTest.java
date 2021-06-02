package com.goatfinder.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class GoatFinderTest {

    private String fileName;
    private Map<String, Opinion> opinion;
    private IParser parser;
    private GoatAnalyzer analyseNBA;
    private IGoatDisplayer displayResults;

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

        fileName = "data/nba2021test2.txt";
        IGoatMaker goatMakerSpy = Mockito.spy(GoatMaker.class);
        parser = new BasketballParser(fileName,opinion,goatMakerSpy);
        analyseNBA = new BasketballAnalyzer(parser);

        displayResults = new GoatFinder(analyseNBA);
    }

    @Test
    @DisplayName("Goat results")
    public void testGoatResults(){


        Assertions.assertEquals(99, displayResults.getResults().size());
        Assertions.assertNotNull( displayResults.getResults().get(0));
        Assertions.assertTrue(displayResults.getResults().get(0).toString().contains("Jimmy Butler"));
        Assertions.assertTrue( displayResults.getResults().get(0).getGoatStats().getGoatScore()>100);
    }

    @Test
    @DisplayName("Goat Displayer")
    public void testGoatDisplayer(){

        displayResults.displayGoats(0);
        Assertions.assertTrue( displayResults.getResults().get(0).getGoatStats().getGoatScore()==100);
        Assertions.assertTrue( displayResults.getResults().get(1).getGoatStats().getGoatScore()<100);
    }

}
