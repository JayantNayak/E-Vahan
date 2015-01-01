package com.example.evahan2;



import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends Activity implements OnClickListener {
	public static final  int i=0;
	
	public final static String MESSAGE = "calc.MESSAGE";//
	private Button button1;
	private Button button2;
	private Button button3;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(this);
		
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(this);
		
		button3 = (Button)findViewById(R.id.button3);
		button3.setOnClickListener(this);
	}
	
	
	public void onClick(View v){
		Intent intent=new Intent(this,Activity3.class);
		if(v.getId()==button1.getId()){
			
			button1.setBackgroundColor(TRIM_MEMORY_BACKGROUND);
			String str ="" + button1.getText();
			
			intent.putExtra(MESSAGE,str );
			startActivity(intent);
			
		}else
			if(v.getId()==button2.getId()){
				String str ="" + button2.getText();
				
				intent.putExtra(MESSAGE,str );
				startActivity(intent);
				
			}else
				if(v.getId()==button3.getId()){
					
					String str ="" + button3.getText();
					
					intent.putExtra(MESSAGE,str );
					startActivity(intent);
					
				}
			
	
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
