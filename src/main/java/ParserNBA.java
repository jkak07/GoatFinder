
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserNBA implements IParser {


    final private String fileName;
    final private Map<String, List<Double>> dataCols;
    final private List<BasketBallPlayerNBA> dataRows;
    final private Map<String,Opinion> goatFields;

    public ParserNBA(final String filename,  Map<String,Opinion> userOpinion){
        this.fileName = filename;
        goatFields = userOpinion;
        dataCols = new HashMap<>();
        dataRows = new ArrayList<>();
        read();

    }

    /*remove unnecessary string in player names*/
    private String parseName(String name){
        String parsedName = null;
        if(name.contains("\\")){
            parsedName = name.substring(0,name.indexOf("\\"));}
        return parsedName;
    }

    /*read and parse nba file*/
    public void read(){

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String[] colNames = reader.readLine().split(",");
            String line;
            while ((line = reader.readLine()) != null) {

                String[] playerInfo= line.split(",");

                String name = "";
                String position = "";
                int age = 0;

                Stats playerStats = new Stats();

                for(int i = 0; i < playerInfo.length; i++){


                    String colName = colNames[i];

                    switch(colName){
                        case "Player":
                            name = parseName(playerInfo[i]);
                            break;
                        case "Pos":
                            position = playerInfo[i];
                            break;
                        case "Age":
                            age = Integer.parseInt(playerInfo[i]);
                            break;
                        case "G":
                            playerStats.setPeriod(Double.parseDouble(playerInfo[i]));
                            dataCols.computeIfAbsent(colName, k -> new ArrayList<>()).add(playerStats.getPeriod());
                            break;
                    }

                    if (goatFields.containsKey(colNames[i])) {
                        double statValue = ((!playerInfo[i].isEmpty())) ? Double.parseDouble(playerInfo[i]): 0;
                        playerStats.getStatHolder().put(colNames[i], statValue);
                        playerStats.getWeightingHolder().put(colNames[i], goatFields.get(colNames[i]));

                        dataCols.computeIfAbsent(colNames[i], k -> new ArrayList<>()).add(statValue);

                    }


                }

                final BasketBallPlayerNBA nbaPlayer = new BasketBallPlayerNBA(name, position, age, playerStats);

                if(!dataRows.contains(nbaPlayer)){

                    dataRows.add(nbaPlayer);
                }


            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Map<String,List<Double>> getDataCols(){
        return this.dataCols;
    }

    public List<BasketBallPlayerNBA> getDataRows(){
        return this.dataRows;
    }



    public static void main(String[] args) {
        HashMap<String, Opinion> opinion = new HashMap<>();

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





        String fileName = "C:\\Users\\jason\\IdeaProjects\\projectA\\Data\\nba2021test1.txt";
        IParser nbaData = new ParserNBA(fileName, opinion);

        for(int i =0; i < 2; i++) {
            System.out.println((nbaData.getDataRows().get(i)));


        }

        for(String x: nbaData.getDataCols().keySet()) {


            System.out.println(x +" " + (nbaData.getDataCols().get(x)));
        }
    }


}

