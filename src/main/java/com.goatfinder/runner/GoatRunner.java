/*GOAT = Greatest Of All Time - swimmer, teacher, dev, dancer,
This application consumes data pertinent to a subject matter person and establishes who is the best in that field
The methodology depends on input data and a series of stats which normalises it*/
package com.goatfinder.runner ;

import com.goatfinder.builder.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;


public class GoatRunner {

    public static Map<String, Opinion> setOpinionsBasketball(){
        Map<String, Opinion> opinion = new HashMap<>();

        opinion.put("3P%", Opinion.LOW_POSITIVE);
        opinion.put("FTA", Opinion.LOW_POSITIVE);
        opinion.put("TRB", Opinion.STRONG_POSITIVE);
        opinion.put("AST", Opinion.STRONG_POSITIVE);
        opinion.put("STL", Opinion.STRONG_POSITIVE);
        opinion.put("BLK", Opinion.LOW_POSITIVE);
        opinion.put("TOV", Opinion.MEDIUM_NEGATIVE);
        opinion.put("PF", Opinion.LOW_NEGATIVE);
        opinion.put("PTS", Opinion.STRONG_POSITIVE);

        return opinion;
}


    public static void main(String[] args) {

        //App Config
        /*ApplicationContext appContext = new AnnotationConfigApplicationContext(Config.class);

        IParser parser = appContext.getBean("Parser", IParser.class);
        System.out.println(parser.getDataRows().get(0)); //config runs the beans without me having to declare it;
        GoatAnalyzer analyser = appContext.getBean("Analyser", GoatAnalyzer.class);

        IGoatDisplayer results1 = appContext.getBean("Finder",IGoatDisplayer.class);
        results1.displayGoats(30);
        System.out.println(results1);

        IGoatDisplayer results2 = appContext.getBean("Finder",IGoatDisplayer.class);
        System.out.println(results2);*/

        //Normal Instantiation
        /*String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";
        IGoatFactory players = new GoatMaker();
        IParser nba = new BasketballParser(fileName, setOpinionsBasketball(), players);
        GoatAnalyzer analyse = new BasketballAnalyzer(nba);
        IGoatDisplayer nbaResults = new GoatFinder(analyse);
        nbaResults.displayGoats(20);/*

        //GoatFactories
        /*String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";
        IGoatFactory players = new GoatMaker();
        IParser nbaData = GoatMaker.parseBasketBallData(fileName, setOpinionsBasketball(),players);
        GoatAnalyzer analysed = GoatAnalyzer.BasketBallAnalyzer(nbaData);
        IGoatDisplayer nbaResults = GoatMaker.goatResults(analysed);
        nbaResults.displayGoats(20);*/


        /*ApplicationContext appContext = new AnnotationConfigApplicationContext(AutoWireConfig.class);
        IGoatDisplayer results1 = appContext.getBean("Finder",IGoatDisplayer.class);
        results1.displayGoats(20);
        */



    }


}
