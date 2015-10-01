package com.team03.albumit.dto;

public class Member {
	private int uid;
	private String email;
	private String nickname;
	private String password;
	private String profile;
	private String img_original_file_name;
	private String img_filesystem_name;
	private String img_content_type;
	
	/*private MultipartFile attach;
	
	public MultipartFile getAttach() {
		return attach;
	}
	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}*/
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getImg_original_file_name() {
		return img_original_file_name;
	}
	public void setImg_original_file_name(String img_original_file_name) {
		this.img_original_file_name = img_original_file_name;
	}
	public String getImg_filesystem_name() {
		return img_filesystem_name;
	}
	public void setImg_filesystem_name(String img_filesystem_name) {
		this.img_filesystem_name = img_filesystem_name;
	}
	public String getImg_content_type() {
		return img_content_type;
	}
	public void setImg_content_type(String img_content_type) {
		this.img_content_type = img_content_type;
	}
	
	

}
