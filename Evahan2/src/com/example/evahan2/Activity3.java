package com.example.evahan2;


import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class Activity3 extends Activity {
 
	 String mobile[]={"9439733385","9556985644","","","","","","","",""};
	 
	 private Button button1;
		private Button button2;
		private Button button3;
		
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3);
        
        
		
		
         
     final LinearLayout pLayout = (LinearLayout) findViewById(R.id.l1);
      
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
              LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
      params.leftMargin =400;
      
      LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
              LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
      params2.leftMargin=215;
      
      
      ScrollView sv = new ScrollView(this);
        
      LinearLayout containerLayout = new LinearLayout(this);
      containerLayout.setOrientation(LinearLayout.VERTICAL);
      
      ArrayList<String> name= new ArrayList();
      name.add("Chaitan Majhi");
      name.add("Apurva Kumar");
      name.add("Tulu Behera");
      name.add("Kishore Sahu");
      name.add("Ram Sagar");
      name.add("Raj Kishan");
      name.add("Ashish Kar");
      name.add("Prakash Rao");
      name.add("Champak Singh");
      name.add("Umesh Das");
      
       String age[]={"21","22","23","24","25","26","27","28","29","30"};
       Float rate[]={5.0f,0.0f,4.0f,3.5f,2.5f,5.0f,1.0f,3.0f,2.0f,3.5f};
       final int n[]={0,1,2,3,4,5,6,7,8,9};
       for(int j=0;j<=9;j++) 
        {   
           
        	LinearLayout upLayout = new LinearLayout(this);						// Create Upper LinearLayout
             upLayout.setOrientation(LinearLayout.HORIZONTAL);					// Set Orientation
             
             
             TextView nameText = new TextView(this);							// Create TextView for Name
             nameText.setText(name.get(j));
             nameText.setGravity(1);
             upLayout.addView(nameText);										// Add TextView to upLayout
             
             
             final ImageButton callBtn = new ImageButton(this);					// Create ImageButton for Call
             callBtn.setId(j);
             int i=0;
             callBtn.setImageResource(R.drawable.ic_action_call); 
             callBtn.setLayoutParams(params);
             callBtn.setOnClickListener(new View.OnClickListener() {			// Define OnClickListener for Call ImageButton
                 public void onClick(View view) {
                	 makeCall(view.getId());
                	 }
                 });
             upLayout.addView(callBtn);
             
            LinearLayout lowerLayout = new LinearLayout(this);					// Create Lower LinearLayout		
            lowerLayout.setOrientation(LinearLayout.HORIZONTAL);				// Set Orientation
            
            TextView ageText = new TextView(this);								// Create TextView for Age
            ageText.setText(age[j]);
            lowerLayout.addView(ageText);										// Add Age textView to lowerLayout
             
           
            final RatingBar ratingBarBtn = new RatingBar(this);					// Create RatingBar 
            ratingBarBtn.setId(j+1);
            ratingBarBtn.setLayoutParams(params2);
            ratingBarBtn.setNumStars(5);
            ratingBarBtn.setRating(rate[j]);
            
            lowerLayout.addView(ratingBarBtn);
            containerLayout.addView(upLayout);
            containerLayout.addView(lowerLayout);
            }
        
        sv.addView(containerLayout);											// Add ContainerLayout to ScrollableView
        pLayout.addView(sv);													// Add ScrollableView to ParentLayout
        }
    
    
   /* public void onClick(View v){
		
		if(v.getId()==button1.getId()){
			
			button1.setBackgroundColor(TRIM_MEMORY_BACKGROUND);
			button2.setBackgroundColor(0xB0C4DE);
			button3.setBackgroundColor(0xB0C4DE);
			String str ="" + button1.getText();
			
		
			
		}else
			if(v.getId()==button2.getId()){
				String str ="" + button2.getText();
				
				button1.setBackgroundColor(0xB0C4DE);
				button2.setBackgroundColor(TRIM_MEMORY_BACKGROUND);
				button3.setBackgroundColor(0xB0C4DE);
				
				
			}else
				if(v.getId()==button3.getId()){
					
					String str ="" + button3.getText();
					button1.setBackgroundColor(0xB0C4DE);
					button2.setBackgroundColor(0xB0C4DE);
					button3.setBackgroundColor(TRIM_MEMORY_BACKGROUND);
					
					
					
					
				}
			
	
	}
   */ 
    
   final protected void makeCall(int j) {										// Function to make a Call
        Log.i("Make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:"+mobile[j]));

        try {
           startActivity(phoneIntent);
           finish();
           Log.i("Finished making a call...", "");
        } catch (android.content.ActivityNotFoundException ex) {
           Toast.makeText(Activity3.this, 
           "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
     }
}																				// End of Activity3