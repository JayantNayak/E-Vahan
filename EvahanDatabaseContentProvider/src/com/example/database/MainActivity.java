package com.example.database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity {

	static private final String TAG = "Main-Activity: ";
	static private final int GET_TEXT_REQUEST_CODE = 5;
	private Button gpsButton ;
	private Button submitButton ;
	private Button cancelButton ;
	private Button viewButton ;
	
	private  EditText nameText;
	private  EditText idText;
    private EditText lattText ;
    private  EditText longText;
    private  EditText licNumText;
    private  EditText serviceTypeText;
    private  EditText ageText;
    private  EditText emailText;
    private  EditText addressText;
    private  EditText ratingText;
    private  EditText contactText;
    private  EditText numVotesText;
    private  EditText locationText;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 // Get a reference to the GPS Button
        gpsButton = (Button) findViewById(R.id.get_gps_button);
        
        
    	submitButton =(Button) findViewById(R.id.submit_button);
    	cancelButton = (Button) findViewById(R.id.cancel_button);
    	viewButton= (Button) findViewById(R.id.view_button) ;
    	
    	nameText=(EditText) findViewById(R.id.vendor_name);
    	idText=(EditText) findViewById(R.id.vendor_id);
        
    	lattText = (EditText) findViewById(R.id.gps_latitude);
        longText = (EditText) findViewById(R.id.gps_longitude);
        
        licNumText=(EditText) findViewById(R.id.license_no);
        serviceTypeText=(EditText) findViewById(R.id.service_type);
        ageText=(EditText) findViewById(R.id.age);
        emailText=(EditText) findViewById(R.id.email);
        addressText=(EditText) findViewById(R.id.address);
        ratingText=(EditText) findViewById(R.id.rating);
        contactText=(EditText) findViewById(R.id.contact_num);
        numVotesText=(EditText) findViewById(R.id.num_votes);
        locationText=(EditText) findViewById(R.id.loc_name);
        
        
        
        
        // Set an OnClickListener on this Button
        // Called each time the user clicks the Button
        gpsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				
				try {
				    /* your code */
				Intent intent = new Intent(MainActivity.this, com.example.database.GetLocationActivity.class);
				
				Log.i(TAG,"to start GetLocationActivity");
				
				System.out.println("starting intent  ");

				startActivityForResult (intent, GET_TEXT_REQUEST_CODE);
				//here 1 is the request code that has to be sent back by the receiving Activity on sending an intent back
				
				//System.out.println("longititude is    "+getIntent().getDoubleExtra("Longitude", 0));
				//longText.setText( ""+getIntent().getDoubleExtra("Longitude", 0));
				//lattText.setText(" la"+getIntent().getStringExtra("Latitude"));
				//Bundle b = getIntent().getExtras();
				//double result = b.getDouble("Longitude");
				//longText.setText( ""+result);
				    
				} catch ( ActivityNotFoundException e) {
					Log.i(TAG,"EXCEPTION Occured");
				    e.printStackTrace();
				}
			}
		});
     
        
        submitButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		//int INVALID =1;
        		//int VALID =0;
        		//int checkFormatFlag = VALID;
        		int checkSumFlag=5;
        		Log.i(TAG,"to start verify");
        		
        		   // get values from UI form
        		   String  name=nameText.getText().toString();
        			 String id=idText.getText().toString();
        			 
        			 String gps_lat=lattText.getText().toString();
        			 String gps_long=longText.getText().toString();
        			 
        			 String license_no=licNumText.getText().toString();
        			 String service_type=serviceTypeText.getText().toString();
        			 
        			 //int age=Integer.parseInt(ageText.getText().toString());
        			 int age;
        			
        			 String contact=contactText.getText().toString();
        			 
        			 String email=emailText.getText().toString();
        			 String address=addressText.getText().toString();
        			 
        			 int rating;
        			 
        			 String numVotes=numVotesText.getText().toString();
        			 
        			 String locationName =locationText.getText().toString();
        			 
        			//name verification 
        		if (!isValidName(name)) {
        			nameText.setError("Invalid Name");
        					//checkFormatFlag=INVALID;// set flag
        				}
        				else
        					checkSumFlag--;
        			 
        		//email validation
        		
				if (!isValidEmail(email)) {
					emailText.setError("Invalid Email");
					//checkFormatFlag=INVALID;// set flag
						}
				else
					checkSumFlag--;
				
				//rating verification
				if(!isValidRating(ratingText.getText().toString()))
					ratingText.setError("Invalid Rating Enter between 0-10");
				
				else
					{	rating=Integer.parseInt(ratingText.getText().toString());
					checkSumFlag--;
					}
				
				//age verification
				if(!isValidAge(ageText.getText().toString()))
					ageText.setError("Invalid Age Enter between 1-110");
				
				else
				{	age=Integer.parseInt(ageText.getText().toString());
					checkSumFlag--;
				}
				
				
				//contact verification
				if(!isValidContact(contactText.getText().toString()))
					contactText.setError("Enter a valid contact");
				
				else
				{	contact=contactText.getText().toString();
					checkSumFlag--;
				}
				
				
				if(checkSumFlag==0)
					newVendor ();
        		
        	}
        	});
        viewButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		lookupVendor ();
        		
        	}
        	});
        cancelButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		removeVendor();
        		
        	}
        	});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Log.i(TAG, "Entered onActivityResult()");
		
		// TODO - Process the result only if this method received both a
		// RESULT_OK result code and a recognized request code
		// If so, update the Textview showing the user-entered text.
		
		// Check which request it is that we're responding to
		
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_layout,
		                               (ViewGroup) findViewById(R.id.toast_layout_root));

		TextView text = (TextView) layout.findViewById(R.id.toast_text);
		

	
		text.setText("GPS Unsucessful !!");
	    if (requestCode == GET_TEXT_REQUEST_CODE)
	    {
	    	//Log.i(TAG,"requestcode :"+requestCode);
	    	//Log.i(TAG,"resultCode :"+resultCode);	
	    	//Log.i(TAG,"RESULT_OK :"+ RESULT_OK);	
	        // Make sure the request was successful
	        if ((resultCode == RESULT_OK) && (data.getIntExtra("gpsStatus", RESULT_CANCELED)== RESULT_OK))
	        {
	        	longText.setText( ""+data.getDoubleExtra("Longitude", 0));
				lattText.setText(""+data.getDoubleExtra("Latitude",0));
				Log.i(TAG,"in if: "+data.getStringExtra("Latitude"));
				text.setText("Sucessfully Obtained GPS !!");
	        }
	    }
	    else{
	    	text.setText("GPS Unsucessful !!");
	    
	    }
	    
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();

	}



public void newVendor () {
	   DataBaseHandler dbHandler = new DataBaseHandler(this, null, null, 1);
	   
	   // get values from UI form
	   String  name=nameText.getText().toString();
		 String id=idText.getText().toString();
		 
		 String gps_lat=lattText.getText().toString();
		 String gps_long=longText.getText().toString();
		 
		 String license_no=licNumText.getText().toString();
		 String service_type=serviceTypeText.getText().toString();
		 
		 int age=Integer.parseInt(ageText.getText().toString());
		 String contact=contactText.getText().toString();
		 
		 String email=emailText.getText().toString();
		 String address=addressText.getText().toString();
		 
		 int rating=Integer.parseInt(ratingText.getText().toString());
		 String numVotes=numVotesText.getText().toString();
		 
		 String locationName =locationText.getText().toString();
	
	 
	
	 
	   
	   Vendor vendor= new Vendor(name,
			   					id, 
			   					gps_lat,
			   					gps_long,
			   					license_no,
			   					service_type,
			   					age,
			   					contact,
			   					email,
			   					address,
			   					rating,
			   					numVotes,
			   					locationName);
	   
	//add the vendor object to database table
	   dbHandler.addVendor(vendor);
	   
	   nameText.setText("");
	   //idText.setText("");
	   lattText.setText("");
	   longText.setText("");
	   licNumText.setText("");
	   serviceTypeText.setText("");
	   ageText.setText("");
	   contactText.setText("");
	   emailText.setText("");
	   addressText.setText("");
	   ratingText.setText("");
	   numVotesText.setText("");
	   locationText.setText("");
}

public void lookupVendor () {
	
	   DataBaseHandler dbHandler = new DataBaseHandler(this, null, null, 1);
	   
	   Vendor vendor = 
        dbHandler.findVendor(idText.getText().toString());
	   
	   LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_layout,
		                               (ViewGroup) findViewById(R.id.toast_layout_root));

		TextView text = (TextView) layout.findViewById(R.id.toast_text);

	   if (vendor != null) {
		   
		   text.setText("Vendor Info: "+String.valueOf(vendor.getName())+
				   "\n Vendor Age: "+String.valueOf(vendor.getAge())+
				   "\n Vendor Contact: "+String.valueOf(vendor.getContact())+
				   "\n Vendor Rate: "+String.valueOf(vendor.getRating()) );
		   }
	   else {
    		text.setText("No Match Found");
	   }  
	   
	  
		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	   
	   
	   
	   
}

public void removeVendor () {
	   DataBaseHandler dbHandler = new DataBaseHandler(this, null, null, 1);
	
	     boolean result = dbHandler.deleteVendor(idText.getText().toString());
	     
	     LayoutInflater inflater = getLayoutInflater();
		 View layout = inflater.inflate(R.layout.toast_layout,
			                               (ViewGroup) findViewById(R.id.toast_layout_root));

		TextView text = (TextView) layout.findViewById(R.id.toast_text);

	     if (result)
	     {
			   text.setText("Record deleted!!: ");
	     }
	     else{
			   text.setText("No Match Found");

	     }
	     Toast toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
	     
	}


//validation of input
	private boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	private boolean isValidName(String  name){
		String NAME_PATTERN = "^[_A-Za-z-\\+]$";
		//String NAME_PATTERN = "^[_A-Za-z-\\+]+(\\[_A-Za-z-])$";


		Pattern pattern = Pattern.compile(NAME_PATTERN);
		Matcher matcher = pattern.matcher(name);
		return true;
		//return matcher.matches();
	}
	private boolean isValidRating(String  rate){
		//if rate is composed of number
		String RATE_PATTERN = "^[_0-9-\\+]$";

		Pattern pattern = Pattern.compile(RATE_PATTERN );
		Matcher matcher = pattern.matcher(rate);
		
		
		///checks if  <=0 rate<=10
		if(matcher.matches())
			if((Integer.parseInt(rate)<=10) && (Integer.parseInt(rate)>=0) )
				return true;
	else
		return false;
	return false;
	}
	
	
	private boolean isValidAge(String  age){
		//check if age is composed of numbers thenn go for checking its value
		String RATE_PATTERN = "^[_0-9-\\+]$";

		Pattern pattern = Pattern.compile(RATE_PATTERN );
		Matcher matcher = pattern.matcher(age);
		
		
		///checks if 0<age<=110
		int temp;
		if(matcher.matches())
			{
			 temp=Integer.parseInt(age);
			if(1< temp && temp <110)
				return true;
			}
	
	return false;
	}
	
	
	private boolean isValidContact(String  contact){
		/* Phone Number formats: (nnn)nnn-nnnn; nnnnnnnnnn; nnn-nnn-nnnn 
	    ^\\(? : May start with an option "(" . 
	    (\\d{3}): Followed by 3 digits. 
	    \\)? : May have an optional ")"  
	    [- ]? : May have an optional "-" after the first 3 digits or after optional ) character.  
	    (\\d{3}) : Followed by 3 digits.  
	     [- ]? : May have another optional "-" after numeric digits. 
	     (\\d{4})$ : ends with four digits. 
	 
	         Examples: Matches following phone numbers: 
	         (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890 
	 
	*/  
	//Initialize reg ex for phone number. 
		String CONTACT_PATTERN =  "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";

		Pattern pattern = Pattern.compile(CONTACT_PATTERN );
		Matcher matcher = pattern.matcher(contact);
		
		
		return matcher.matches();
	}
    }
    

