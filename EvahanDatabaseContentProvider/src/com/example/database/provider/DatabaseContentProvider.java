package com.example.database.provider;

import com.example.database.DataBaseHandler;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class DatabaseContentProvider extends ContentProvider {
	
			private final String TAG = "DatabaseContentProvider";

		private DataBaseHandler myDBHandler;
		private static final String AUTHORITY = "com.example.database.provider.DatabaseContentProvider";
		private static final String VENDORS_TABLE = "vendorTable";
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + VENDORS_TABLE );


		public static final int VENDORS = 1;
		public static final int VENDORS_ID = 2;


		private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

		static {
			sURIMatcher.addURI(AUTHORITY, VENDORS_TABLE, VENDORS);
			sURIMatcher.addURI(AUTHORITY, VENDORS_TABLE + "/#", VENDORS_ID);
		}

		
		@Override
		public boolean onCreate() {
			// TODO Auto-generated method stub
			myDBHandler = new DataBaseHandler(getContext(), null, null, 1);
			return false;
			
		}
		
		
/**
 * Implementing the Content Provider insert() Method

When a client application or activity requests that data be inserted into the underlying database, the insert() method of the content provider class will be called. At this point, however, all that exists in the MyContentProvider.java file of the project is a stub method, which reads as follows:
@Override
public Uri insert(Uri arg0, ContentValues arg1) {
	// TODO Auto-generated method stub
	return null;
}
Passed as arguments to the method are a URI specifying the destination of the insertion and a ContentValues object containing the data to be inserted.
This method now needs to be modified to perform the following tasks:
Use the sUriMatcher to identify the URI type.
Throw an exception if the URI is not valid.
Obtain a reference to a writable instance of the underlying SQLite database.
Perform a SQL insert operation to insert the data into the database table.
Notify the corresponding content resolver that the database has been modified.
Return the URI of the newly added table row.
Bringing these requirements together results in a modified insert() method, which reads as follows (note also that the argument names have been changed from arg0 and arg1 to names that are more self-explanatory):		
 */
		@Override
		public Uri insert(Uri uri, ContentValues values) {
			// TODO Auto-generated method stub
			Log.i(TAG, "inside insert");

			int uriType = sURIMatcher.match(uri);
			   
			SQLiteDatabase sqlDB = myDBHandler.getWritableDatabase();

			long id = 0;
			switch (uriType) {
				case VENDORS:
				 id = sqlDB.insert(DataBaseHandler.TABLE_VENDOR , 
						null, values);
				break;
				default:
				 throw new IllegalArgumentException("Unknown URI: " + uri);
			}
			getContext().getContentResolver().notifyChange(uri, null);
			return Uri.parse(VENDORS_TABLE + "/" + id);
		}




	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}
/**
 * Implementing the Content Provider query() Method


When a content provider is called upon to return data, the query() method of the provider class will be called. When called, this method is passed some or all of the following arguments:
URI – The URI specifying the data source on which the query is to be performed. This can take the form of a general query with multiple results, or a specific query targeting the ID of a single table row.
Projection – A row within a database table can comprise multiple columns of data. In the case of this application, for example, these correspond to the ID, product name and product quantity. The projection argument is simply a String array containing the name for each of the columns that is to be returned in the result data set.
Selection – The “where” element of the selection to be performed as part of the query. This argument controls which rows are selected from the specified database. For example, if the query was required to select only products named “Cat Food” then the selection string passed to the query() method would read productname = “Cat Food”.
Selection Args – Any additional arguments that need to be passed to the SQL query operation to perform the selection.
Sort Order – The sort order for the selected rows.

When called, the query() method is required to perform the following operations:
Use the sUriMatcher to identify the Uri type.
Throw an exception if the URI is not valid.
Construct a SQL query based on the criteria passed to the method. For convenience, the SQLiteQueryBuilder class can be used in construction of the query.
Execute the query operation on the database.
Notify the content resolver of the operation.
Return a Cursor object containing the results of the query.
With these requirements in mind, the code for the query() method in the MyContentProvider.java file should now read as outlined in the following listing:
 */
	


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
		        String[] selectionArgs, String sortOrder) {
		
		Log.i(TAG, "inside query");
			
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(DataBaseHandler.TABLE_VENDOR);

		int uriType = sURIMatcher.match(uri);

		Log.i(TAG, "uri Type"+ uriType);
		Log.i(TAG, "uri.getLastPathSegment()"+ uri.getLastPathSegment());

		switch (uriType) {
		
		    case VENDORS_ID:
		    	///on case matching appending takes place on the vendor id of the database table
		        queryBuilder.appendWhere(DataBaseHandler.COLUMN_VENDORID + "="
		                + uri.getLastPathSegment());
		        break;
		    case VENDORS:// refers to the data base table
		        break;
		    default:
		        throw new IllegalArgumentException("Unknown URI");
		}

		Cursor cursor = queryBuilder.query(myDBHandler.getReadableDatabase(),
		     projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	/**
	 * Implementing the Content Provider update() Method

The update() method of the content provider is called when changes are being requested to existing database table rows. The method is passed a URI, the new values in the form of a ContentValues object and the usual selection argument strings.
When called, the update() method would typically perform the following steps:
Use the sUriMatcher to identify the URI type.
Throw an exception if the URI is not valid.
Obtain a reference to a writable instance of the underlying SQLite database.
Perform the appropriate update operation on the database depending on the selection criteria and the URI type.
Notify the content resolver of the database change.
Return a count of the number of rows that were changed as a result of the update operation.
A general-purpose update() method, and the one we will use for this project, would read as follows:
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
		      String[] selectionArgs) {
		
		Log.i(TAG, "inside update");

		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = myDBHandler.getWritableDatabase();
		int rowsUpdated = 0;

		switch (uriType) {
		  case VENDORS:
		    rowsUpdated = sqlDB.update(DataBaseHandler.TABLE_VENDOR, 
		        values, 
		        selection,
		        selectionArgs);
		    break;
		  case VENDORS_ID:
		    String id = uri.getLastPathSegment();
		    if (TextUtils.isEmpty(selection)) {
		    	
		      rowsUpdated = sqlDB.update(DataBaseHandler.TABLE_VENDOR, 
							values,
							DataBaseHandler.COLUMN_VENDORID + "=" + id, 
							null);
		      
		      
		    } 
		    else {
		      rowsUpdated = sqlDB.update(DataBaseHandler.TABLE_VENDOR, 
		    		  					values,
		    		  					DataBaseHandler.COLUMN_VENDORID  + "=" + id 
		    		  					+ " and " 
		    		  					+ selection,
		    		  					selectionArgs);
		    }
		    break;
		  default:
		    throw new IllegalArgumentException("Unknown URI: " + uri);
		  }
		getContext().getContentResolver().notifyChange(uri, null);
	      return rowsUpdated;
	}
	
/**
 * Implementing the Content Provider delete() Method

In common with a number of other content provider methods, the delete() method is passed a URI, a selection string and an optional set of selection arguments. A typical delete() method will also perform the following, and by now largely familiar, tasks when called:
Use the sUriMatcher to identify the URI type.
Throw an exception if the URI is not valid.
Obtain a reference to a writable instance of the underlying SQLite database.
Perform the appropriate delete operation on the database depending on the selection criteria and the Uri type.
Notify the content resolver of the database change.
Return the number of rows deleted as a result of the operation.
A typical delete() method is, in many ways, very similar to the update() method and may be implemented as follows:	
 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {	
		
		Log.i(TAG, "inside delete");
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = myDBHandler.getWritableDatabase();
		int rowsDeleted = 0;

		switch (uriType) {
		    case VENDORS:
		      rowsDeleted = sqlDB.delete(DataBaseHandler.TABLE_VENDOR,
	              selection,
		        selectionArgs);
		        break;
		      
		    case VENDORS_ID:
		      String id = uri.getLastPathSegment();
		      if (TextUtils.isEmpty(selection)) {
		        rowsDeleted = sqlDB.delete(DataBaseHandler.TABLE_VENDOR,
		        							DataBaseHandler.COLUMN_VENDORID + "=" + id, 
		        							null);
		      } 
		      else {
		        rowsDeleted = sqlDB.delete(DataBaseHandler.TABLE_VENDOR,
		        							DataBaseHandler.COLUMN_VENDORID+ "=" + id 
		        							+ " and " + selection,
		        							selectionArgs);
		      }
		      break;
		    default:
		      throw new IllegalArgumentException("Unknown URI: " + uri);
		    }
		    getContext().getContentResolver().notifyChange(uri, null);
		    return rowsDeleted;
	}

}
