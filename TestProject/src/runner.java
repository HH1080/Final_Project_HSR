import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.json.*;

public class runner {
	
	public void JSONArraydeleter(String writer, String filelocation, int i) throws IOException {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(writer));			//filewriter
			
			JSONArray dataJSON = JSONUtils.getJSONArrayFromFile(filelocation);      //�N�Ӧ�m���ɮ׽ƻs����ܦ�JSONArray(or JSONObject)
			//dataJSON.remove(i);														//�����ӽƻs���󪺲�0��
			JSONObject ticket = dataJSON.getJSONObject(i).getJSONArray("ticketInfo").getJSONObject(0); //��ticketInfo
			ticket.getJSONArray("seats").clear();                                          //�M��array���(seats�٬O�|�b�A��remove���P)
			for (int j = 0;j<5;j++) {
			    ticket.getJSONArray("seats").put(j,"0000");                                //for�j�� �[�J���w�ƶq�����
			}
			String ws = dataJSON.toString();		//�NdataJSON�ରString type
			System.out.println(ws);					//�Nws�L�X�Ӭݬ�
			bw.write(ws);
			bw.flush();
			bw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		
		JSONArray timetable = JSONUtils.getJSONArrayFromFile("/timetable.json");
		JSONObject earlyDiscount = JSONUtils.getJSONObjectFromFile("/earlyDiscount.json");
		
		System.out.println(timetable.length());
		System.out.println(earlyDiscount.getJSONArray("DiscountTrains").length());
		
		JSONArray arrayofbooking = JSONUtils.getJSONArrayFromFile("/booking.json");
		System.out.println(arrayofbooking.get(0));
		System.out.println(arrayofbooking.get(1));
		
		JSONArray train = JSONUtils.getJSONArrayFromFile("/timeTable.json");
		
		
		runner tester = new runner();
		
		String writer = "Data/booking.json";
		String filelocation = "booking.json";
		int which = 0;
		
		tester.JSONArraydeleter(writer, filelocation, which);
		
	}
}
