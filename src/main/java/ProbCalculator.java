import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ProbCalculator {

    public HashMap<String, Integer> tagMap = new HashMap<String, Integer>();
    public HashMap<String, Integer> initMap = new HashMap<String, Integer>();
    public HashMap<String,HashMap<String,Integer>> transMap = new HashMap<String, HashMap<String, Integer>>();
    public HashMap<String,Integer[]> firegMap = new HashMap<String,Integer[]>();

    public void get_statistic(){
        try {
            BufferedReader in = new BufferedReader(new FileReader(".\\src\\main\\resources\\msr_training2.txt"));
            String line;
            String[] pairs;
            String pair;
            String[] word_tag;
            String word;
            String tag = null;
            String temp_tag = null;
            HashMap<String,Integer> subtransMap;
            Integer num_transMap;
            Integer[] num_word_tag;

            int count = 0;
            while((line = in.readLine())!= null){
                count += 1;
//                line  = line.replace("\n","");
//                line  = line.trim();
                if(line == "\n"){
                    continue;
                }
                pairs = line.split(" ");
                for(int i = 0;i< pairs.length;i++){
                    pair = pairs[i];
                    word_tag = pair.split("/");
                    word = word_tag[0];
                    try {
                        tag = word_tag[1];
                    }
                    catch (Exception e){
                        System.out.println(line);
                    }
                    //tagFrequency
                    Integer num_tagMap = tagMap.get(tag);
                    if(num_tagMap == null) tagMap.put(tag,1);
                    else tagMap.put(tag,num_tagMap + 1);
                    //init map
                    if(i==0){
                        Integer num_initMap = initMap.get(tag);
                        if(num_initMap == null) initMap.put(tag,1);
                        else initMap.put(tag,num_initMap + 1);
                    }
                    //transMap
                    else{
                        subtransMap = transMap.get(temp_tag);
                        if(subtransMap == null){
                            subtransMap = new HashMap<String, Integer>();
                            transMap.put(temp_tag,subtransMap);
                        }
                        num_transMap = subtransMap.get(tag);
                        if(num_transMap == null) subtransMap.put(tag,1);
                        else subtransMap.put(tag,num_transMap + 1);
                    }
                    temp_tag = tag;
                    //FireMap
                    num_word_tag = firegMap.get(word);
                    if(num_word_tag == null){
                        num_word_tag = new Integer[2];
                        num_word_tag[0] = 0;
                        num_word_tag[1] = 0;
                    }
                    if(tag.equals("B")){
                        num_word_tag[0]++;
                    }
                    else if(tag.equals("I")){
                        num_word_tag[1]++;
                    }
                    firegMap.put(word,num_word_tag);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
