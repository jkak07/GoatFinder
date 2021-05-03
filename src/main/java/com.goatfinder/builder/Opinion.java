package com.goatfinder.builder ;
public enum Opinion {
    LOW_POSITIVE(3),
    MEDIUM_POSITIVE(5),
    STRONG_POSITIVE(7),
    NEUTRAL(1),
    LOW_NEGATIVE(-3),
    MEDIUM_NEGATIVE(-5),
    STRONG_NEGATIVE(-7);

    private final int rating;

    Opinion(int rating){this.rating = rating;}

    int getRating(){return rating;}

    Opinion reflect(){
        switch(this){
            default: return NEUTRAL;
            case LOW_POSITIVE: return LOW_NEGATIVE;
            case MEDIUM_NEGATIVE: return MEDIUM_POSITIVE;
            case STRONG_POSITIVE: return STRONG_NEGATIVE;
            case LOW_NEGATIVE: return LOW_POSITIVE;
            case MEDIUM_POSITIVE: return MEDIUM_NEGATIVE;
            case STRONG_NEGATIVE: return STRONG_POSITIVE;


        }

    }

}
