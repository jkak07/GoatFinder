package com.goatfinder.builder;

import java.util.List;

public class TennisAnalyzer extends GoatAnalyzer{

    public TennisAnalyzer(IParser fileData) {
        super(fileData);
    }

    public void goatCalculator() {

    }







    public int partition(int firstValIndex, int lastValIndex, List<Double> list ){

        double pivot = list.get(lastValIndex); // pivot value by which all elements will be compared against
        int i = firstValIndex - 1; //i is index of last element of list of values smaller than pivot

        //iterates values bigger than pivot values, j is 1st element of unordered list remaining
        for(int j = firstValIndex; j < lastValIndex; i++ ){

            //if condition met, will iterate over smaller list and consequently larger list too, swap i and j
            if(list.get(j) <= pivot){
                i++;
                double storeJ = list.get(j);
                list.set(j, list.get(i));
                list.set(i, storeJ);
            }
        }
        //swap pivot element with remaining spot in list
        double storeI = list.get(i+1);
        list.set(i+1,list.get(lastValIndex));
        list.set(lastValIndex,storeI);

        return i+1;
    }

}
