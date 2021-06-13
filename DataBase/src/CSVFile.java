

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * �ھڭn�q�����,����q�������O�ɳЫإX�@���ɬ����ѩҦ����������,�ç�o�ѥ[�J����(recentDateFile),�H�@������R�����Ѧ�
 * �Χ�q����Ѥ��e�Ҧ����ɥ����R��(�bconstructor(����)�ɰ���H)
 * p.s�n�Ϋe�Х�����|�令�ۤv��,windows��mac�s���|���覡�n�����Ӥ@��,windows�i�H�ݫ~���j����
 * 
 * @author fuhming
 *
 */
public class CSVFile {

 public CSVFile() { };

 /**
  * �ھڵ��w��date�]�n�q������^����
  * 
  * @param date
  * @throws Exception
  */
 public void createFile(Date date) throws Exception {
  Calendar cal = Calendar.getInstance();
  cal.setTime(date);
//  cal.add(Calendar.DATE, -1); ���ե�
  int Weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
  // �P�_�o�Ѥ��e�O�_�w�йL
  if (!(new File("/Users/fuhming/Desktop/document/" + String.format("%02d", cal.get(Calendar.MONTH) + 1)
    + String.format("%02d", cal.get(Calendar.DATE)) + ".csv").exists())) { 
   writeFile(Weekday, String.format("%02d", cal.get(Calendar.MONTH) + 1),
     String.format("%02d", cal.get(Calendar.DATE)));
   //�N�o�ѥ[�J����
   writeRecentFile(
     String.format("%02d", cal.get(Calendar.MONTH) + 1) + String.format("%02d", cal.get(Calendar.DATE)));
  } else
   System.out.println("this date has already been created");
 }

 
 public void writeFile(int whichWeekdays, String month, String date) throws Exception {
  BufferedWriter bw = new BufferedWriter(
                                                    //�ɦW�����(ex:0613)
    new FileWriter("/Users/fuhming/Desktop/document/" + month + date + ".csv"));
  //��X��ѩҦ����}������
  int[] days = FindTrain(whichWeekdays);
  // -----------------------------------------------------------
  JSONObject obj = JSONUtils.getJSONObjectFromFile("/earlyDiscount.json");
  JSONArray DiscountTrains = obj.getJSONArray("DiscountTrains");
  // ----------------------------------------------------------- get earlyDiscount.json
   
  JSONArray timeTable = JSONUtils.getJSONArrayFromFile("/timeTable.json"); // get timeable.json

  for (int i = 0; i < days.length; i++) {
   if (days[i] == 0) {
    continue; // 0�]�S�}�^���ܴN�������L
   }
//   if (i == 5)
//    break;
   JSONObject element = timeTable.getJSONObject(i);
   JSONObject GeneralTimetable = element.getJSONObject("GeneralTimetable");
   JSONObject GeneralTrainInfo = GeneralTimetable.getJSONObject("GeneralTrainInfo");
   String TrainNo = GeneralTrainInfo.getString("TrainNo"); // �C�x��������

   int Direction = GeneralTrainInfo.getInt("Direction"); // �C�x����Direction
   JSONArray StopTimes = GeneralTimetable.getJSONArray("StopTimes");
   // -------------------------------------------------------
   JSONObject inobj = null;
   String earlyDiscountTrainNo = null;
   for (int k = 0; k < DiscountTrains.length(); k++) {
    inobj = DiscountTrains.getJSONObject(k);
    earlyDiscountTrainNo = inobj.getString("TrainNo");
    if (earlyDiscountTrainNo.equals(TrainNo))
     break;
   }
   System.out.println(earlyDiscountTrainNo.equals(TrainNo));
   // -------------------------------------------------------�B�zearlyDiscount

   bw.write(TrainNo + "," + Direction + "\n");
   // -------------------------------------------------------

   if (earlyDiscountTrainNo.equals(TrainNo)) {
    JSONObject ServiceDayDiscount = inobj.getJSONObject("ServiceDayDiscount");
    String Weekdays = FindWeekday(whichWeekdays);
    try {
     JSONArray day = ServiceDayDiscount.getJSONArray(Weekdays);
     for (int j = 0; j < day.length(); j++) {
      JSONObject earlyDiscount = day.getJSONObject(j);
      double discount = earlyDiscount.getDouble("discount");
      bw.write(String.valueOf(discount) + ",");

     }
     bw.write("\n");
     for (int j = 0; j < day.length(); j++) {
      JSONObject earlyDiscount = day.getJSONObject(j);
      int tickets = earlyDiscount.getInt("tickets");
      bw.write(String.valueOf(tickets) + ",");
     }
     bw.write("\n");
    } catch (Exception e) {
    }
    ;
   }
   // ------------------------------------------------------- �g�JearlyDiscount
   bw.write("Seats" + ",");
   seat(bw);
   bw.write("\n");

   for (int j = 0; j < StopTimes.length(); j++) {
    JSONObject StopStations = StopTimes.getJSONObject(j);
    String ID = StopStations.getString("StationID");
    JSONObject StationName = StopStations.getJSONObject("StationName");
    String name = StationName.getString("En");
    name = ID + " : " + name; // ��U�ӯ���ID��W�r���X

    // System.out.println(name);
    bw.write(name + ",");

    seat0("/seat.json", bw);
    bw.write("\n");

   }

  }
  bw.close();

 }

 public void writeRecentFile(String createdDate) throws IOException {

  if ((new File("/Users/fuhming/Desktop/document/recentDateFile.csv").exists())) {
   BufferedReader br = new BufferedReader(
     new FileReader("/recentDateFile.csv"));
   String Line = br.readLine(); // Ū�ª��X��
   br.close();
   String[] OLD = Line.split(",");
   ArrayList<String> DateList = new ArrayList(Arrays.asList(OLD));
   DateList.add(createdDate); // �[�W�o���ت�
   String[] NEW = DateList.toArray(new String[0]);
   BufferedWriter bw1 = new BufferedWriter(
     new FileWriter("/Users/fuhming/Desktop/document/recentDateFile.csv"));
   for (int i = 0; i < NEW.length; i++) {
    bw1.write(NEW[i] + ","); // ���g�s��recentDateFile
   }
   bw1.close();
  } else {
   BufferedWriter bw1 = new BufferedWriter(
     new FileWriter("/Users/fuhming/Desktop/document/recentDateFile.csv"));
   bw1.write(createdDate);
   bw1.close();
  }
 }

 public void removeFile() throws IOException { 
  Calendar cal = Calendar.getInstance(); // ���o��Ѥ��
  String CAL = String.format("%02d", cal.get(Calendar.MONTH) + 1) + String.format("%02d", cal.get(Calendar.DATE)); // ��Ѥ��+���
  BufferedReader br = new BufferedReader(new FileReader("/Users/fuhming/Desktop/document/recentDateFile.csv"));
  String Line = br.readLine(); // ���o���e�Ҧ��عL�����
  br.close();
  String[] OLD = Line.split(",");
  ArrayList<String> DateList = new ArrayList(Arrays.asList(OLD));
  int j = 0; // �q0�}�l�ˬd,�S�R�����ܴN���e�ˬd
  for (int i = 0; i < DateList.size(); i++) {
   if (Integer.valueOf(DateList.get(j)) < Integer.valueOf(CAL)) { // �عL����� < ��ѫh�R��
    new File("/Users/fuhming/Desktop/document/" + DateList.get(j) + ".csv").delete();
    DateList.remove(j);
   } else
    j++;
  }
  String[] NEW = DateList.toArray(new String[0]);
  BufferedWriter bw2 = new BufferedWriter(new FileWriter("/Users/fuhming/Desktop/document/recentDateFile.csv"));
  for (int i = 0; i < NEW.length; i++) {
   bw2.write(NEW[i]); // �мg�s��recentDateFile
  }
  bw2.close();
 }

 public int[] FindTrain(int whichWeekdays) {
  JSONArray timeTable = JSONUtils.getJSONArrayFromFile("/timeTable.json");
  int whichDay[] = new int[timeTable.length()];

  for (int i = 0; i < timeTable.length(); i++) {
   JSONObject element = timeTable.getJSONObject(i);
   JSONObject GeneralTimetable = element.getJSONObject("GeneralTimetable");
   JSONObject ServiceDay = GeneralTimetable.getJSONObject("ServiceDay");
   switch (whichWeekdays) {
   case 0:
    for (int m = 0; m < timeTable.length(); m++) {
     whichDay[i] = ServiceDay.getInt("Sunday");
    }
    break;
   case 1:
    for (int m = 0; m < timeTable.length(); m++) {
     whichDay[i] = ServiceDay.getInt("Monday");
    }
    break;
   case 2:
    for (int m = 0; m < timeTable.length(); m++) {
     whichDay[i] = ServiceDay.getInt("Tuesday");
    }
    break;
   case 3:
    for (int m = 0; m < timeTable.length(); m++) {
     whichDay[i] = ServiceDay.getInt("Wednesday");
    }
    break;
   case 4:
    for (int m = 0; m < timeTable.length(); m++) {
     whichDay[i] = ServiceDay.getInt("Thursday");
    }
    break;
   case 5:
    for (int m = 0; m < timeTable.length(); m++) {
     whichDay[i] = ServiceDay.getInt("Friday");
    }
    break;
   case 6:
    for (int m = 0; m < timeTable.length(); m++) {
     whichDay[i] = ServiceDay.getInt("Saturday");
    }
    break;
   }
  }

  return whichDay;
 }

 public String FindWeekday(int whichWeekdays) {
  switch (whichWeekdays) {
  case 0:
   return "Sunday";
  case 1:
   return "Monday";
  case 2:
   return "Tuesday";
  case 3:
   return "Wednesday";
  case 4:
   return "Thursday";
  case 5:
   return "Friday";
  case 6:
   return "Saturday";
  }
  return "3�p�k";
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
      bw.write(0 + ","); // �o�̧ڧ�ele�����令0�N�����O0�F
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
    for (int j = 1; j <= 20; j++) { // �ѩ�C�����[�̦h��20�A�G��20���W��
     JSONArray Seat = seats.getJSONArray(String.valueOf(j));
     for (int k = 0; k < Seat.length(); k++) {
      String eachSeat = Seat.getString(k);
      bw.write(String.format("%02d", i) + String.format("%02d", j) + eachSeat + ",");

     }
    }
   } catch (Exception e) {
   }
  }
 }



}
