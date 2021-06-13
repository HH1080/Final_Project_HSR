
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

/**
 * @author yy121
 *
 */
/**
 * @author yy121
 *
 */
public class searchDB {

	/**
	 * @param date 日期形式如0628不用加.csv 
	 * @param train
	 * @param start
	 * @param end
	 * @param number票數
	 * @return 座位的編號
	 * @throws IOException
	 */	
	public static String getSeatno(String date,String train, String start, String end,int number) throws IOException {
		BufferedReader rDB = new BufferedReader(new FileReader("C://NTU/" + date + ".csv"));
		// 找到指定train
		// System.out.println("here");
		String line=" ";// = rDB.readLine();
//		int lineCount++;
		boolean found = false;
		while (found == false) {
			line = rDB.readLine();
			String[] tt = line.split(",");
			if (tt[0].equals(train)) {
				found = true;
			}
			else {
				}
		}
		// 建一個array元素是各個站的string，預設空間十個車站
		String[] stationSeat = new String[10];

		// 要記錄是哪兩個站
		int st = 0;
		int s = 0, e = 0;
		boolean matchS = false, matchE = false;
		// 找到開始站，一路assign到找到為止
		int sts=0,ste=0;
		try {
			
			while (matchS == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(start)) {
					e++;
					sts=st;
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
					ste=st;
					matchE = true;
				} else {
					e++;
				}
				st++;
			}
		} catch (Exception ee) {
			//System.out.println(ee.getMessage());
		}
		if ((sts >= ste)) {
			rDB.close();
			return "wrong direction"; // 方向錯誤
		}

		// 把第一排設定成座位
		String[] num = stationSeat[2].split(",");
		// 把有的位置輸進一個二維的座位array
		String[][] seats = new String[e - s + 1][986];
		for (int n = 0; n <= e - s; n++) {
			seats[n] = stationSeat[n + s].split(",");
		}

		// 直的是相同座位，橫的是相同站
		// 先以一個位子為單位(大圈for)，直的向下加之後(內圈for)，再進入下一次的迴圈
		int col=0;
		String seatno = "";
		String currentSeat="no seat available";
		for(int t=1;t<=number;t++) {
		for (int i = col; i <= 985; i++) {
			int checkseat = 0;

			for (int n = 0; n <= e - s; n++) {
				
				if (seats[n][i].equals("0")) {
				} else {
					checkseat++;
				}
			}
			if (checkseat == 0) {
				currentSeat=num[i];
				seatno = seatno+currentSeat+", "; // 要return的東西
				col=i+1;
				break;
			}

		}
		}
		rDB.close();
		return seatno;
	}


	/**
	 * @param date(日期形式如0628不用加.csv )
	 * @param train(列車號碼)
	 * @param start
	 * @param end
	 * @param number(票數)
	 * @param kind(只能填window or aisle 但我沒有寫偵錯)
	 * @return 座位編號
	 * @throws IOException
	 */
	public static String getSeatnoSpecial(String date,String train, String start, String end,int number,String kind) throws IOException {
		BufferedReader rDB = new BufferedReader(new FileReader("C://NTU/" + date + ".csv"));
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
		int sts=0,ste=0;
		try {
			
			while (matchS == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(start)) {
					e++;
					sts=st;
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
					ste=st;
					matchE = true;
				} else {
					e++;
				}
				st++;
			}
		} catch (Exception ee) {
			//System.out.println(ee.getMessage());
		}
		if ((sts >= ste)) {
			rDB.close();
			return "wrong direction"; // 方向錯誤
		}
		// 把第一排設定成座位
		String[] num = stationSeat[2].split(",");
		// 把有的位置輸進一個二維的座位array
		String[][] seats = new String[e - s + 1][986];
		for (int n = 0; n <= e - s; n++) {
			seats[n] = stationSeat[n + s].split(",");
		}

		// 直的是相同座位，橫的是相同站
		// 先以一個位子為單位(大圈for)，直的向下加之後(內圈for)，再進入下一次的迴圈
		int col=0;
		String seatno = "";
		String currentSeat="no seats available";
		for(int t=1;t<=number;t+=0) {		
		for (int i = col; i <= 985; i++) {
			int checkseat = 0;
			for (int n = 0; n <= e - s; n++) {
				if (seats[n][i].equals("0")) {
				} else {
					checkseat++;
				}
			}
			if (checkseat == 0) {
				currentSeat=num[i];
				if((currentSeat.contains("C")||currentSeat.contains("D"))&&(kind.equals("aisle"))) {
					seatno =seatno+ currentSeat+", "; // 要return的東西
					col=i+1;
					t++;
					break;
				}
				else if((currentSeat.contains("A")||currentSeat.contains("E"))&&(kind.equals("window"))) {
					seatno =seatno+currentSeat+", "; // 要return的東西
					col=i+1;
					t++;
					break;
				}
				else {
					
				}		
			}
		}
		}
		rDB.close();
		if(seatno.equals("")) {
			seatno ="no seats beside "+kind;
		}
		
		return seatno;
	}


	/**
	 * @param train
	 * @param start
	 * @param end
	 * @param seatNO
	 * @throws IOException
	 */
	public static void setSeatno(String date,String train, String start, String end,String seatNO) throws IOException {
	
		String ttt="tabble";
		
		BufferedReader rDB = new BufferedReader(new FileReader("C://NTU/" + date + ".csv"));
		BufferedWriter sDB = new BufferedWriter(new FileWriter("C://NTU/" + ttt + ".csv"));
		
		// 找到指定train
		String line = rDB.readLine();
		sDB.write(line);
		boolean found = false;
		while (found == false) {
			line = rDB.readLine();
			sDB.newLine();
			sDB.write(line);
			String[] tt = line.split(",");
			if (tt[0].equals(train)) {
				found = true;
			}
			else {
			}
		}		
		//找到起始、終點
		String[] stationSeat = new String[15];
		// 要記錄是哪兩個站
		int st = 0;
		int s = 0, e = 0;
		boolean matchS = false, matchE = false;
		// 找到開始站，一路write到找到為止
		try {
			
			while (matchS == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(start)) {
					e++;
					matchS = true;
				} else {
					s++;
					e++;
					sDB.newLine();
					sDB.write(stationSeat[st]);
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
		}
		//stationSeat[s]就是從起始站
		//stationSeat[e]就是終點站
		
		//先找座位的橫的編號X
		int x = 0;
		String[] num = stationSeat[2].split(",");
		for(int t=1;t<=985;t++) {
			if (num[t].contains(seatNO)) {
				x=t;
				break;
			}
		}

		//中間的站都變成array，指派第x個元素為1
		String[] seats=new String[985] ;
		
		for(int tt=s;tt<=e;tt++) {
			seats=stationSeat[tt].split(",");
			seats[x]="1";
			stationSeat[tt]=String.join(",", seats);
			sDB.newLine();
			sDB.write(stationSeat[tt]);
		}
		
		//剩下的再重新write一次
		try {
			int fake=1;
			while(fake==1) {
				sDB.newLine();
				sDB.write(rDB.readLine());

			}
		}
		catch(Exception ee) {
			ee.getMessage();
		}
		finally {
			rDB.close();
			sDB.close();
		}
		
		BufferedReader rDB2 = new BufferedReader(new FileReader("C://NTU/" + ttt + ".csv"));
		BufferedWriter sDB2 = new BufferedWriter(new FileWriter("C://NTU/" + date + ".csv"));
		try {
			int fake2=1;
			sDB2.write(rDB2.readLine());
			while(fake2==1) {
				sDB2.newLine();
				sDB2.write(rDB2.readLine());
			}
		}
		catch(Exception ee) {
			ee.getMessage();
		}
		finally {
			rDB2.close();
			sDB2.close();
		}
		File file_dulplicate = new File("C://NTU/" + ttt +".csv");
		file_dulplicate.delete();
			
	}

	
	
	/**
	 * @param date
	 * @param train
	 * @param start
	 * @param end
	 * @param seatNO
	 * @param discount
	 * @throws IOException
	 */
	public static void setSeatnoEarly(String date,String train, String start, String end,String seatNO,String discount) throws IOException {
		
		String ttt="tabble";
		
		BufferedReader rDB = new BufferedReader(new FileReader("C://NTU/" + date + ".csv"));
		BufferedWriter sDB = new BufferedWriter(new FileWriter("C://NTU/" + ttt + ".csv"));
		
		// 找到指定train
		String line = rDB.readLine();
		sDB.write(line);
		boolean found = false;
		while (found == false) {
			line = rDB.readLine();
			sDB.newLine();
			sDB.write(line);
			String[] tt = line.split(",");
			if (tt[0].equals(train)) {
				found = true;
			}
			else {
			}
		}		


		//找到並扣掉折價的票
		String[] caldiscount = new String[2];
		caldiscount[0] = rDB.readLine();
		sDB.newLine();
		sDB.write(caldiscount[0]);
		caldiscount[1] = rDB.readLine();
		String[] dis = caldiscount[0].split(",");
		String[] tickets = caldiscount[1].split(",");		
		for(int t=1;t<=10;t++) {
			if (dis[t].contains(discount)) {
				int tick = Integer.parseInt(tickets[t])-1;
				tickets[t]=Integer.toString(tick);
				System.out.println(tick);
				caldiscount[1]=String.join(",", tickets);
				sDB.newLine();
				sDB.write(caldiscount[1]);
				break;
			}
		}
		
		//找到起始、終點
		String[] stationSeat = new String[15];
		// 要記錄是哪兩個站
		int st = 0;
		int s = 0, e = 0;
		boolean matchS = false, matchE = false;
		
		// 找到開始站，一路write到找到為止
		try {
			
			while (matchS == false) {
				stationSeat[st] = rDB.readLine();
				if (stationSeat[st].contains(start)) {
					e++;
					matchS = true;
				} else {
					s++;
					e++;
					sDB.newLine();
					sDB.write(stationSeat[st]);
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
		}
		//stationSeat[s]就是從起始站
		//stationSeat[e]就是終點站
		

		
		
		//先找座位的橫的編號X
		int x = 0;
		String[] num = stationSeat[2].split(",");
		for(int t=1;t<=985;t++) {
			if (num[t].contains(seatNO)) {
				x=t;
				break;
			}
		}

		//中間的站都變成array，指派第x個元素為1
		String[] seats=new String[985] ;
		
		for(int tt=s;tt<=e;tt++) {
			seats=stationSeat[tt].split(",");
			seats[x]="1";
			stationSeat[tt]=String.join(",", seats);
			sDB.newLine();
			sDB.write(stationSeat[tt]);
		}
		
		//剩下的再重新write一次
		try {
			int fake=1;
			while(fake==1) {
				sDB.newLine();
				sDB.write(rDB.readLine());

			}
		}
		catch(Exception ee) {
			ee.getMessage();
		}
		finally {
			rDB.close();
			sDB.close();
		}
		
		BufferedReader rDB2 = new BufferedReader(new FileReader("C://NTU/" + ttt + ".csv"));
		BufferedWriter sDB2 = new BufferedWriter(new FileWriter("C://NTU/" + date + ".csv"));
		try {
			int fake2=1;
			sDB2.write(rDB2.readLine());
			while(fake2==1) {
				sDB2.newLine();
				sDB2.write(rDB2.readLine());
			}
		}
		catch(Exception ee) {
			ee.getMessage();
		}
		finally {
			rDB2.close();
			sDB2.close();
		}
		File file_dulplicate = new File("C://NTU/" + ttt +".csv");
		file_dulplicate.delete();
			
	}
	
	
	
	public static void main(String[] args) throws IOException {
		String ss=getSeatno("0612","0565", "1000 : Taipei", "1035 : Miaoli",10);
		System.out.println(ss);
		//setSeatno("0612","0565", "1000 : Taipei", "1035 : Miaoli","0102D");
		setSeatnoEarly("0612","0565", "1000 : Taipei", "1035 : Miaoli","0102D","0.9");
	}

}	