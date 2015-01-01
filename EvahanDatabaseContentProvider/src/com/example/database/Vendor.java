package com.example.database;

public class Vendor {
	private String  _name;
	private String _id;
	private String _gps_lat;
	private String _gps_long;
	private String _license_no;
	private String _service_type;
	private int _age;
	private String _contact;
	private String _email;
	private String _address;
	private int _rating;
	private String _numVotes;
	private String _locationName;
	


	public Vendor() {
		
	}
	
	public Vendor(
			 String  name,
			 String id,
			 String gps_lat,
			 String gps_long,
			 String license_no,
			 String service_type,
			 int age,
			 String contact,
			 String email,
			 String address,
			 int rating,
			 String numVotes,
			 String locationName
			) 
	{
		this._name=name;
		 this._id=id;
		 this._gps_lat=gps_lat;
		 this._gps_long=gps_long;
		 this._license_no=license_no;
		 this._service_type=service_type;
		 this._age=age;
		 this._contact=contact;
		 this._email=email;
		 this._address=address;
		 this._rating=rating;
		 this._numVotes=numVotes;
		 this._locationName=locationName;
	}
	

	
	
	public String getName() {
		return this._name;
	}
	
	
	public void  setName(String name) {
		 this._name = name;
	}
	////
	public String getID() {
		return this._id;
	}
	
	public void  setID(String id) {
		this._id=id;
	}
	/////
	public String getLat() {
		return this._gps_lat;
	}
	public void setLat(String gps_lat) {
		 this._gps_lat= gps_lat;
	}
	///
	public String getLong() {
		return this._gps_long;
	}
	public void setLong(String gps_long ) {
		this._gps_long=gps_long;
	}
	//
	
	public String getLicNo() {
		return this._license_no;
	}
	public void setLicNo(String license_no) {
		this._license_no=license_no;
	}
	///
	public String getServiceType() {
		return this._service_type;
	}
	public void setServiceType(String service_type) {
		this._service_type= service_type;
	}
	///
	public int getAge() {
		return this._age;
	}
	public void setAge(int age) {
		 this._age=age;
	}
	
	///
	public String getContact() {
		return this._contact;
	}
	public void setContact(String contact) {
		this._contact=contact;
	}
	///
	public String getEmail() {
		return this._email;
	}
	public void  setEmail(String email) {
		this._email=email;
	}
	////
	public String getAddress() {
		return this._address;
	}
	public void setAddress(String address) {
		 this._address=address;
	}
	////
	public int getRating() {
		return this._rating;
	}
	public void setRating(int rating) {
		 this._rating =rating;
	}
	///
	public String getNumVotes() {
		return this._numVotes;
	}
	public void setNumVotes(String numVotes) {
		 this._numVotes=numVotes;
	}
	///
	public String getLocationName() {
		return this._locationName;
	}
	
	public void setLocationName(String locationName) {
		this._locationName=locationName;
	}
	
	///
	
	
	

}
