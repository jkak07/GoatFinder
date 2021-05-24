/*
package com.goatfinder.runner;

import com.goatfinder.builder.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.goatfinder.runner.GoatRunner.setOpinionsBasketball;

 //why does "unmarking" this ruin my application
@Configuration
public class Config {
     //beans are only defined at method level

     @Bean(name = "Goat")
     public IGoatFactory getGoat() {
         return new GoatMaker();
     }


    @Bean(name = "Parser")
    public IParser getParser(){

        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";
        return new BasketballParser(fileName, setOpinionsBasketball(), getGoat());
    }


    @Bean(name = "Analyser")
    public GoatAnalyzer getGoatAnalyzer(){
        return new BasketballAnalyzer(getParser());
    }


    @Scope(value = BeanDefinition.SCOPE_SINGLETON)// singleton single instance per bean config vs prototype(unique instance per request, guaranteed to be unique)
    @Bean(name = "Finder")
    public IGoatDisplayer getGoatDisplayer(){
        return new GoatFinder(getGoatAnalyzer());
    }
}
*/