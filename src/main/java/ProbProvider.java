
public class ProbProvider {

    public static ProbCalculator pb = new ProbCalculator(){{get_statistic();}}
    ;

    public static double getInitProb(String h){
        return 0.0;
    }

    public static double getFireProb(String h, String o){
        return 0.0;
    }
    public static double getTransProb(String cur, String to){
        double transProb = 0.0;
        return transProb;
    }

    public static int getWordFrequency(String word){
        int count = 0;
        return count;
    }

    public static int[] getWordTagFrequecny(String word){
        int[] count = new int[2];
        return count;
    }
}
