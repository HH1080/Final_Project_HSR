import java.io.*;
import java.util.ArrayList;
import org.json.*;

public class test {
    /*public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>(); 
        list.add("admin");
        //Ū��json�ɮ�
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/json/newFile.json")); //new file
	        
	        JSONObject dataJson = new JSONObject("/booking.json");
	        JSONArray  features = dataJson.getJSONArray("features");
	        for (int i = 0; i < features.length(); i++) {
	        	
                JSONObject info = features.getJSONObject(i);				// ���features�}�C����i��json����  
                JSONObject properties = info.getJSONObject("properties");	// ���properties��json����  
                String name = properties.getString("name");					// Ū��properties����̪�name����  
                
                properties.put("NAMEID", list.get(i));						// �s�WNAMEID���
                // properties.append("name", list.get(i));  ԣ�N��???
                System.out.println(properties.getString("NAMEID"));  
                properties.remove("ISO");									// �R��ISO���
                
                String ws = dataJson.toString();	//�N�B�z����JSONObject dataJson�নString type (ws)
                
                bw.write(ws);	//�Nws�g�Jbw��
                				// bw.newLine();  ԣ�N��???
                bw.flush(); 	//�Nbw�g�J
                bw.close();		//�Nbw�g�J����
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
		    e.printStackTrace();
		}
    }*/
}