package com.example.database;
import com.example.database.provider.DatabaseContentProvider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHandler extends SQLiteOpenHelper {
	
	private final String TAG = "DataBaseHandler";
	
	private ContentResolver dbCR;

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "vendorDB.db";
	public static final String TABLE_VENDOR = "vendorTable";
	
	///coloumns in database
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_VENDORID = "id";
	public static final String COLUMN_GPS_LAT = "gps_lat";
	public static final String COLUMN_GPS_LONG = "gps_long";
	public static final String COLUMN_LIC_NO = "license_no";
	public static final String COLUMN_SERVICE_TYPE = "service_type";
	public static final String COLUMN_AGE = "age";
	public static final String COLUMN_CONTACT = "contact";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_RATING = "rating";
	public static final String COLUMN_NUM_VOTES = "numVotes";
	public static final String COLUMN_LOCATION = "locationName";
	
	public DataBaseHandler(Context context, String name, 
			CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		dbCR = context.getContentResolver();
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		Log.i(TAG, "creating a table");

		String CREATE_VENDOR_TABLE = "CREATE TABLE " +
		TABLE_VENDOR   		+ "("
		//+ COLUMN_ID     	+ " integer  autoincrement, "
		+ COLUMN_NAME   	+ " text not null  , " 
		+COLUMN_VENDORID	+" text not null primary key, "
		+COLUMN_GPS_LAT		+" text not null, "
		+COLUMN_GPS_LONG	+" text not null, "
		+COLUMN_LIC_NO		+" text not null, "
		+COLUMN_SERVICE_TYPE+" text not null, "
		+COLUMN_AGE			+" integer  not null, "
		+COLUMN_CONTACT		+" text  not null, "
		+COLUMN_EMAIL		+" text not null, "
		+COLUMN_ADDRESS		+" text not null, "
		+COLUMN_RATING		+" integer not null, "
		+COLUMN_NUM_VOTES	+" text not null, "
		+COLUMN_LOCATION	+" text not null"
							+");";
		
	      db.execSQL(CREATE_VENDOR_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDOR);
	      onCreate(db);
		
	}
	// Add records to database table
	public void addVendor(Vendor vendor) {
		Log.i(TAG, "inside addVendor");
		
        ContentValues values = new ContentValues();
        values.put(	COLUMN_NAME,      vendor.getName()	 		);
        values.put(	COLUMN_VENDORID,  vendor.getID()	 		);
        values.put(	COLUMN_GPS_LAT,   vendor.getLat()    		);
        values.put(	COLUMN_GPS_LONG,  vendor.getLong()   		);
        values.put(	COLUMN_LIC_NO,    vendor.getLicNo()	 		);
        values.put(	COLUMN_SERVICE_TYPE,vendor.getServiceType()	);
        values.put(	COLUMN_AGE,       vendor.getAge()	 		);
        values.put(	COLUMN_CONTACT,   vendor.getContact()		);
        values.put(	COLUMN_EMAIL,     vendor.getEmail()	 		);
        values.put(	COLUMN_ADDRESS,   vendor.getAddress()		);
        values.put(	COLUMN_RATING,    vendor.getRating()  		);
        values.put(	COLUMN_NUM_VOTES, vendor.getNumVotes()      );
        values.put(	COLUMN_LOCATION,  vendor.getLocationName()	);
       
 
        //SQLiteDatabase db = this.getWritableDatabase();
        
        //db.insert(TABLE_VENDOR, null, values);
       // db.close();
        
        
        
    	 
    	dbCR.insert(DatabaseContentProvider.CONTENT_URI, values);
}
	
	public Vendor findVendor(String vendorId) {
		
		/// below commented code is just for reference , when contentprovider was not used 
		///-->>DONOT UNCOMMENT<<---
		///String query = "Select * FROM " + TABLE_VENDOR + " WHERE " + COLUMN_VENDORID + " =  \"" + vendorId + "\"";
		
		///SQLiteDatabase db = this.getWritableDatabase();
		
		///Cursor cursor = db.rawQuery(query, null);
		
		Log.i(TAG, "inside findVendor");
		String[] projection = {COLUMN_NAME,      
								COLUMN_VENDORID,
								COLUMN_GPS_LAT,  
								COLUMN_GPS_LONG,  
								COLUMN_LIC_NO,   
								COLUMN_SERVICE_TYPE,
								COLUMN_AGE, 
								COLUMN_CONTACT,   
								COLUMN_EMAIL,     
								COLUMN_ADDRESS,   
								COLUMN_RATING,    
								COLUMN_NUM_VOTES, 
								COLUMN_LOCATION
							};
		    	
		    	// kind of select statement used normally
		    	String selection = COLUMN_VENDORID +"= \"" + vendorId + "\"";

		    	
		    	Cursor cursor = dbCR.query(DatabaseContentProvider.CONTENT_URI, 
		    								projection, selection, null,
		    								null);
		
		Vendor vendor = new Vendor();
		
		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			
			vendor.setName(cursor.getString(0));
			vendor.setID(cursor.getString(1));
			vendor.setLat(cursor.getString(2));
			vendor.setLong(cursor.getString(3));
			vendor.setLicNo(cursor.getString(4));
			vendor.setServiceType(cursor.getString(5));
			vendor.setAge(cursor.getInt(6));
			vendor.setContact(cursor.getString(7));
			vendor.setEmail(cursor.getString(8));
			vendor.setAddress(cursor.getString(9));
			vendor.setRating(cursor.getInt(10));
			vendor.setNumVotes(cursor.getString(11));
			vendor.setLocationName(cursor.getString(12));
			
			cursor.close();
		} else {
			vendor = null;
		}
	        //db.close();
		return vendor;
	}
	
	
	public boolean deleteVendor(String vendorId) {
		
		Log.i(TAG, "inside deleteVendor");
		
		boolean result = false;
		
		///the below code was without content handler just for reference donot uncomment
		///-->>DONOT UNCOMMENT<<---
		/**String query = "Select * FROM " + TABLE_VENDOR + " WHERE " + COLUMN_VENDORID + " =  \"" +vendorId + "\"";

		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		Vendor vendor = new Vendor();
		
		if (cursor.moveToFirst()) {
			vendor.setID(cursor.getString(1));
			db.delete(TABLE_VENDOR, COLUMN_VENDORID + " = ?",
		            new String[] { String.valueOf(vendor.getID()) });
			cursor.close();
			result = true;
		}
	        db.close();
		return result;
		**/
		
		
    	// the below code is used for content provider implementation of this method
    	String selection = COLUMN_VENDORID +"= \"" + vendorId + "\"";
    	
    	int rowsDeleted = dbCR.delete(DatabaseContentProvider.CONTENT_URI, 
                  selection, null);
		
    	if (rowsDeleted > 0)
    		result = true;
    	
    	return result;
		
	}
	

}
