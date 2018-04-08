package Appium;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class arrayDemo {
	static public  void  main  (String[] Aniket) throws ParseException {
		 Date date = Calendar.getInstance().getTime();

		
		    // Display a date in day, month, year format
		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    String today = formatter.format(date);
		    System.out.println("Today : " + today);
		    
		    
		/*int arr[] = { 1, 2, 3, 4 };
		int arr1[] = { 1, 2, 3, 4 };
		System.out.println("yes both are equal   : "+Arrays.equals(arr, arr1));
		
		for (int i = 0; i < arr.length; i++) {
			
			if (arr[i] == arr1[i]) {

				System.out.println("this Elements are Equals in array    : " + arr[i]);
			} else {

				System.out.println("This Elements` are  unique in array  :" + arr1[i]);
			}*/

		}

	}


