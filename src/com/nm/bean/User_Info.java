/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nm.bean;

/**
 *
 * @author MAHE
 */
public class User_Info {

	private int id;
    private String title;
    private String first_name;
    private String last_name;
    private int univer_id_fk;
    private int subject_fk;
    private String email;
    private long contact;
    private String contact_type;
    private int major_id_fk;
    private String roll_num;
    private int section_id_fk;
    private int year_id_fk;
    private String time_stamp;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getUniver_id_fk() {
		return univer_id_fk;
	}
	public void setUniver_id_fk(int univer_id_fk) {
		this.univer_id_fk = univer_id_fk;
	}
	public int getSubject_fk() {
		return subject_fk;
	}
	public void setSubject_fk(int subject_fk) {
		this.subject_fk = subject_fk;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getContact_type() {
		return contact_type;
	}
	public void setContact_type(String contact_type) {
		this.contact_type = contact_type;
	}
	public int getMajor_id_fk() {
		return major_id_fk;
	}
	public void setMajor_id_fk(int major_id_fk) {
		this.major_id_fk = major_id_fk;
	}
	public String getRoll_num() {
		return roll_num;
	}
	public void setRoll_num(String roll_num) {
		this.roll_num = roll_num;
	}
	public int getSection_id_fk() {
		return section_id_fk;
	}
	public void setSection_id_fk(int section_id_fk) {
		this.section_id_fk = section_id_fk;
	}
	public int getYear_id_fk() {
		return year_id_fk;
	}
	public void setYear_id_fk(int year_id_fk) {
		this.year_id_fk = year_id_fk;
	}
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}   
}
