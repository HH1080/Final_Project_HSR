
import java.util.Arrays;
import java.util.Calendar;


import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2 {

 public static void main(String[] args) throws Exception {
  JSONObject obj = JSONUtils.getJSONObjectFromFile("/earlyDiscount.json");
  JSONArray DiscountTrains = obj.getJSONArray("DiscountTrains");
  JSONObject inobj;
  String gan = null;
  for (int i = 0; i < DiscountTrains.length(); i++) {
   
    inobj = DiscountTrains.getJSONObject(i);
    String earlyDiscountTrainNo = inobj.getString("TrainNo");
    
    if (earlyDiscountTrainNo == "0639") {
     System.out.println(earlyDiscountTrainNo);
     break;
    }
   gan = earlyDiscountTrainNo;
   System.out.println(gan.equals("0598"));
  }
//  System.out.println(gan);
//  String a = "0632";
//  if (a == "0639") System.out.println("gan");
//  else System.out.println("www");
  System.out.println("3¤p°®§AÆC");
  System.out.println(gan);
  String a = "0598";
  System.out.println(a);
  System.out.println(a.equals("0598"));
  
 }
}