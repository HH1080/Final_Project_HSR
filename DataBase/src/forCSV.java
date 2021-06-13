
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.*;

public class forCSV {

	public forCSV() {};

	public static String getSeatno(String train, String start, String end) throws IOException {
		String sss = "table";
		BufferedReader rDB = new BufferedReader(new FileReader("C://NTU/" + sss + ".csv"));
		// 找到指定train
		// System.out.println("here");
		String line = rDB.readLine();
		int lineCount = 1;

		boolean found = false;
		while (found == false) {

			line = rDB.readLine();
			String[] tt = line.split(",");
			if (tt[0].equals(train)) {
				found = true;
			}

			else {
				lineCount++;
			}
		}
		// 建一個array元素是各個站的string，預設空間十個車站
		String[] stationSeat = new String[10];

		// 要記錄是哪兩個站
		int st = 0;
		int s = 0, e = 0;
		boolean matchS = false, matchE = false;
		// 找到開始站，一路assign到找到為止
		try {
			while (matchS == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(start)) {
					e++;
					matchS = true;
				} else {
					s++;
					e++;
				}
				st++;
			}
			while (matchE == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(end)) {
					matchE = true;
				} else {
					e++;
				}
				st++;
			}
		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
		if (s > e) {
			rDB.close();
			return "wrong direction"; // 方向錯誤

		}
		// 把第一排設定成座位
		String[] num = stationSeat[0].split(",");
		// 把有的位置輸進一個二維的座位array
		String[][] seats = new String[e - s + 1][986];
		for (int n = 0; n <= e - s; n++) {
			seats[n] = stationSeat[n + s].split(",");
		}

		// 直的是相同座位，橫的是相同站
		// 先以一個位子為單位(大圈for)，直的向下加之後(內圈for)，再進入下一次的迴圈
		String seatno = "not found";
		for (int i = 1; i <= 985; i++) {
			int checkseat = 0;
			for (int n = 0; n <= e - s; n++) {
				if (seats[n][i].equals("0")) {
				} else {
					checkseat++;
				}
			}
			if (checkseat == 0) {
				seatno = num[i]; // 要return的東西
				break;
			}
		}
		rDB.close();
		return seatno;
	}

	public static String getSeatnoSpecial(String train, String start, String end,String kind) throws IOException {
		String sss = "table";
		BufferedReader rDB = new BufferedReader(new FileReader("C://NTU/" + sss + ".csv"));
// 找到指定train
		String line = rDB.readLine();
		boolean found = false;
		while (found == false) {
			line = rDB.readLine();
			String[] tt = line.split(",");
			if (tt[0].equals(train)) {
				found = true;
			}
			else {}
		}
//找起始站、終點站
		// 建一個array元素是各個站的string，預設空間十個車站
		String[] stationSeat = new String[10];
		// 要記錄是哪兩個站
		int st = 0;
		int s = 0, e = 0;
		boolean matchS = false, matchE = false;
		// 找到開始站，一路assign到找到為止
		try {
			while (matchS == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(start)) {
					e++;
					matchS = true;
				} else {
					s++;
					e++;
				}
				st++;
			}
			while (matchE == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(end)) {
					matchE = true;
				} else {
					e++;
				}
				st++;
			}
		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}
		if (s > e) {
			rDB.close();
			return "wrong direction"; // 方向錯誤

		}
		// 把第一排設定成座位
		String[] num = stationSeat[0].split(",");
		// 把有的位置輸進一個二維的座位array
		String[][] seats = new String[e - s + 1][986];
		for (int n = 0; n <= e - s; n++) {
			seats[n] = stationSeat[n + s].split(",");
		}

		// 直的是相同座位，橫的是相同站
		// 先以一個位子為單位(大圈for)，直的向下加之後(內圈for)，再進入下一次的迴圈
		String seatno = "not found";
		for (int i = 1; i <= 985; i++) {
			int checkseat = 0;
			for (int n = 0; n <= e - s; n++) {
				if (seats[n][i].equals("0")) {
				} else {
					checkseat++;
				}
			}
			if (checkseat == 0) {
				seatno = num[i]; // 要return的東西
				if((seatno.contains("C")||seatno.contains("D"))&&(kind.equals("aisle"))) {
					break;
				}
				else if((seatno.contains("A")||seatno.contains("E"))&&(kind.equals("window"))) {
					break;
				}
				else {
					seatno ="no seats beside "+kind;
				}
				
				
			}
		}
		rDB.close();
		return seatno;
	}
	
	public void seat0(String str, BufferedWriter bw) throws IOException {
		JSONObject obj = JSONUtils.getJSONObjectFromFile(str);
		JSONArray train = obj.getJSONArray("cars");
		for (Object cars : train) {
			JSONObject seats = ((JSONObject) cars).getJSONObject("seats");
			for (Integer line = 1; line <= 20; line++) {
				try {
					JSONArray seatNO = seats.getJSONArray(line.toString());
					for (Object ele : seatNO) {
						// System.out.println(line+" "+ele);
						bw.write(0 + ","); // 這裡我把ele直接改成0就全都是0了
					}
				} catch (Exception e) {
				}
			}
		}
	}

	public void seat(BufferedWriter bw) throws IOException { // 1-1A,1-1B,1-1C,...
		JSONObject obj = JSONUtils.getJSONObjectFromFile("/seat.json");
		JSONArray cars = obj.getJSONArray("cars");
		for (int i = 1; i <= cars.length(); i++) {
			JSONObject carsObj = cars.getJSONObject(i - 1);
			JSONObject seats = carsObj.getJSONObject("seats");
			try {
				for (int j = 1; j <= 20; j++) { // 由於每輛車廂最多到20，故取20為上限
					JSONArray Seat = seats.getJSONArray(String.valueOf(j));
					for (int k = 0; k < Seat.length(); k++) {

						String eachSeat = Seat.getString(k);
						bw.write(i + "-" + j + eachSeat + ",");
					}
				}
			} catch (Exception e) {
			}
		}
	}

	// timeTable 結構：
	// timeTable (a JSONArray) [
	// GeneralTimetable{GeneralTrainInfo{},StopTimes[],ServiceDay[]}]，這些是我們要的
	
	public static void main(String[] args) throws IOException {
		forCSV a = new forCSV();

		JSONArray timeTable = JSONUtils.getJSONArrayFromFile("/timeTable.json");
		JSONObject seat = JSONUtils.getJSONObjectFromFile("/seat.json");

		
		BufferedWriter bw = new BufferedWriter(new FileWriter("C://NTU/table.csv"));

		int Mon[] = new int[timeTable.length()];
		for (int i = 0; i < timeTable.length(); i++) {
			JSONObject element = timeTable.getJSONObject(i); // 最外層array的每一個元素為JSONObject
			JSONObject GeneralTimetable = element.getJSONObject("GeneralTimetable"); // element裡的GeneralTimetable{}
			JSONObject ServiceDay = GeneralTimetable.getJSONObject("ServiceDay"); // GeneralTimetable裡的ServiceDay
			Mon[i] = ServiceDay.getInt("Monday"); // 把每一個ServiceDay裡的Monday結合起來變一個array,方便查看每一台車是否有開(1 or 0)
		} // 其他天可以此類推

		// for (int i = 0; i < Mon.length; i++) { 單純測試
		// System.out.println(Mon[i]);
		// }

		for (int i = 0; i < Mon.length; i++) {
			if (Mon[i] == 0) {
				continue; // 0（沒開）的話就直接跳過
			}
			// if (i == 10)break; 純測試前十台Mon有開

			JSONObject element = timeTable.getJSONObject(i);
			JSONObject GeneralTimetable = element.getJSONObject("GeneralTimetable");
			// 上面的同上一個loop
			JSONArray StopTimes = GeneralTimetable.getJSONArray("StopTimes");
			JSONObject GeneralTrainInfo = GeneralTimetable.getJSONObject("GeneralTrainInfo");
			String TrainNo = GeneralTrainInfo.getString("TrainNo"); // 每台車的車號
			int Direction = GeneralTrainInfo.getInt("Direction"); // 每台車的Direction

			// System.out.println(TrainNo);
			bw.write(TrainNo + "," + Direction + "\n");
			bw.write("Seats" + ",");
			a.seat(bw);
			bw.write("\n");

			for (int j = 0; j < StopTimes.length(); j++) {
				JSONObject StopStations = StopTimes.getJSONObject(j);
				String ID = StopStations.getString("StationID");
				JSONObject StationName = StopStations.getJSONObject("StationName");
				String name = StationName.getString("En");
				name = ID + " : " + name; // 把各個站的ID跟名字結合

				// System.out.println(name);
				bw.write(name + ",");

				a.seat0("/seat.json", bw);
				bw.write("\n");

			}
			// System.out.print("\n");

		}
		bw.close();

		String aaa = getSeatno("0300", "1060 : Tainan", "1000 : Taipei");
		System.out.println(aaa);
		
		String spe = getSeatnoSpecial("0300", "1060 : Tainan", "1000 : Taipei","aisle");
		System.out.println(spe);

	}
}