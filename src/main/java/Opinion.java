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

    public int getRating(){return rating;}

}
