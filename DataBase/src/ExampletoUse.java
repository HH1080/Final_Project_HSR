
import org.json.*;

public class ExampletoUse {

	public static void main(String[] args) {
		
		/*
		 * 此檔案中的錯誤請忽略
		 * 他們是在指duplicated的名稱（所以拿去用的時候還是要改一下）
		 */
		
		
		//=============================video example=============================
		JSONObject obj = JSONUtils.getJSONObjectFromFile("/earlyDiscount.json");
		String[] names = JSONObject.getNames(obj);
		for(String string : names) {
			System.out.println(string + ": " + obj.get(string));
		}
		
		System.out.print("\n");
		
		JSONArray jsonArray = obj.getJSONArray("Array");
		for(int i = 0; i < jsonArray.length(); i++) {
			System.out.println(jsonArray.get(i));
		}
		
		System.out.print("\n");
		
		int number = obj.getInt("Number");
		System.out.println(number);
		
		System.out.print("\n");
		
		String string = obj.getString("String");
		System.out.println(string);
		
		System.out.print("\n");
		
		boolean bool = obj.getBoolean("Boolean");
		System.out.println(bool);

//		//=============================video example=============================
//
//		/*
//		//=============================early discount=============================
//		JSONObject obj = JSONUtils.getJSONObjectFromFile("/earlyDiscount.json");
//		String[] names = JSONObject.getNames(obj);
//		for(String string : names) {
//			System.out.println(string + ": " + obj.get(string));
//		}
//		
//		System.out.print("\n");
//		
//		JSONArray jsonArray = obj.getJSONArray("DiscountTrains");
//		for(int i = 0; i <jsonArray.length(); i++) {
//			System.out.println(jsonArray.get(i));
//		}
//		//=============================early discount=============================
//		
//		System.out.print("\n");
//		//================================booking================================
//		JSONArray arr = JSONUtils.getJSONArrayFromFile("/booking.json");
//		for(int i = 0; i <arr.length(); i++) {
//			System.out.println(arr.get(i));
//		}
//		 
//		System.out.print("\n");
//		
//		//================================price================================
//		JSONArray arr2 = JSONUtils.getJSONArrayFromFile("/price.json");
//		for(int i = 0; i <arr2.length(); i++) {
//			System.out.println(arr2.get(i));
//		}
//		
//		System.out.print("\n");
//		  
//		//================================seat================================
//		JSONObject obj2 = JSONUtils.getJSONObjectFromFile("/seat.json");
//		String[] names2 = JSONObject.getNames(obj2);
//		for(String string : names2) {
//		 System.out.println(string + ": " + obj2.get(string));
//		}
//		System.out.print("\n");
//		  
//		      
//		//================================station================================
//		JSONArray arr3 = JSONUtils.getJSONArrayFromFile("/station.json");
//		for(int i = 0; i <arr3.length(); i++) {
//		    System.out.println(arr3.get(i));
//		}
//		 
//		System.out.print("\n");
//		
//		//================================timetable================================
//		JSONArray arr4 = JSONUtils.getJSONArrayFromFile("/timeTable.json");
//		for(int i = 0; i <arr4.length(); i++) {
//		    System.out.println(arr4.get(i));
//		}
//		  
//		System.out.print("\n");
//		//=============================universityDiscount=============================
//
//		JSONObject obj3 = JSONUtils.getJSONObjectFromFile("/universityDiscount.json");
//		String[] names3 = JSONObject.getNames(obj3);
//		for(String string : names3) {
//			System.out.println(string + ": " + obj3.get(string));
//		}
//		
//		System.out.print("\n");
//		 
//		JSONArray jsonArray3 = obj3.getJSONArray("DiscountTrains");
//		for(int i = 0; i <jsonArray3.length(); i++) {
//			System.out.println(jsonArray3.get(i));
//		}
//		  
//		  System.out.print("\n");
//		 */
////	}
//
//}
}
}