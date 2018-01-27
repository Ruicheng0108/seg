
import java.util.Arrays;


public class segmentor {
    public String[] seg(String[] words) {

        ProbCalculator pc = new ProbCalculator();
        pc.get_statistic();
        String[] keys = pc.initMap.keySet().toArray(new String[pc.initMap.keySet().size()]);

        double[][] probstage = new double[keys.length][words.length];
        int[][] bestpre = new int[keys.length][words.length];
        Arrays.fill(probstage, 0.0);
        Arrays.fill(bestpre, 0.0);

        for (int j = 0; j < keys.length; j++) {
            probstage[j][0] = ProbProvider.getInitProb(keys[j]) + ProbProvider.getFireProb(keys[j], words[0]);
        }

        double max_prob;
        double temp_prob;
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < keys.length; j++) {
                max_prob = -1.0;
                for (int pj = 0; pj < keys.length; pj++) {
                    temp_prob = probstage[pj][i] * ProbProvider.getTransProb(keys[pj], keys[j]) * ProbProvider.getFireProb(keys[j], words[i]);
                    if (temp_prob > max_prob) {
                        max_prob = temp_prob;
                        bestpre[j][i] = pj;
                    }
                    probstage[j][i] += temp_prob;
                }
            }
        }


        int bestEnd = 0;
        double best_prob = 0.0;
        for (int j = 0; j < keys.length; j++) {
            if (probstage[j][words.length] > best_prob) {
                bestEnd = j;
                best_prob = probstage[j][words.length];
            }
        }


        String[] tagList = new String[words.length];

        for (int i = words.length - 1; i >= 0; i--){
            tagList[i] = keys[bestEnd];
            bestEnd = bestpre[bestEnd][i];
        }
        return tagList;
    }
}
