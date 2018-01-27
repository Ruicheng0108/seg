import java.io.*;

public class tagger {

    public static void twoTag(String inputfileroute, String outputfileroute){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(inputfileroute));
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputfileroute), "utf-8");
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for(String word : words){
                    for(int i = 0; i<word.length();i++){
                        if(i==0){
                            writer.write(word.substring(i,i+1) + "/B ");
                        }
                        else{
                            writer.write(word.substring(i,i+1) + "/I ");
                        }
                    }
                }
                writer.write( "\n");
            }
            br.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String args[]){
//        twoTag("H:\\segmentation\\msr_training.utf8","H:\\segmentation\\msr_training2.txt");
//    }
}
