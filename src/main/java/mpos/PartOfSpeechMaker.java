package mpos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class PartOfSpeechMaker {

  public static Map<Character,String> symbolToType() {
    Map<Character,String> result = new HashMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(new File("./mpos/legend.txt") ));){
      String line = null;
      while ((line = br.readLine()) != null){
        String [] parts = line.split("\\t");
        result.put(parts[1].charAt(0), parts[0]);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } 
    return result;
  }
  
  public static SortedMap<String,List<Character>> readDictionary(){
    SortedMap<String,List<Character>> result = new TreeMap<>();
    try (BufferedReader br = new BufferedReader(new FileReader(new File("./mpos/mobyposi.i") ));){
      String line = null;
      while ((line = br.readLine()) != null){        
        String first = line.substring(0, line.indexOf( 65533));
        String parts = line.substring(line.indexOf(65533) + 1, line.length());
        List<Character> types = new ArrayList<>();
        for (int i = 0 ; i < parts.length();i++){
          types.add(parts.charAt(i));
        }
        result.put(first, types );
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } 
    return result;
  }
  
  public static void main (String [] args) throws IOException {
    for (Map.Entry<Character, String> entry: symbolToType().entrySet()){
      System.out.println(entry.getKey());
      System.out.println(entry.getValue());
    }
    for (Map.Entry<String, List<Character>> entry :readDictionary().entrySet() ){
      System.out.println(entry.getKey());
      System.out.println(entry.getValue());
    }
  }
}
