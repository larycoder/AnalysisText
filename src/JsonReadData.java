package bin;

import java.io.*; 
import java.util.Iterator; 
import java.util.Map; 
  
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class JsonReadData{
    private JSONObject jo;
    private File dir;

    public JsonReadData(String path) throws Exception{
        dir= new File(path);
    }

    public int getLength(){ 
        return 0;
        }
        
    public String getString(int index) throws Exception{ 
        System.out.println(dir.getAbsolutePath());
        Object obj = new JSONParser().parse(new FileReader(dir.getAbsolutePath()+"/"+Integer.toString(index)+".json")); 
        jo = (JSONObject) obj; 
        return (String)jo.get("text"); 
        }
}