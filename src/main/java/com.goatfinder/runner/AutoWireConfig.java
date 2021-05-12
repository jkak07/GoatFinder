package com.goatfinder.runner;

import com.goatfinder.builder.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.goatfinder.runner.GoatRunner.setOpinionsBasketball;

@Configuration
@ComponentScan({"com.goatfinder.Builder"})
public class AutoWireConfig {

    @Bean(name = "Parser")
    public IParser getParser(){
        IGoatFactory factory = new GoatMaker();
        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2020.txt";
        return new BasketballParser(fileName, setOpinionsBasketball(),factory);
    }




}
