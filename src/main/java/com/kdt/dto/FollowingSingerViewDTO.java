package com.kdt.dto;

public class FollowingSingerViewDTO {

	private String follower;
	private String profileImage;
	private String singer;
	private Long count;
	public FollowingSingerViewDTO() {
		super();
	}
	public FollowingSingerViewDTO(String follower, String profileImage, String singer, Long count) {
		super();
		this.follower = follower;
		this.profileImage = profileImage;
		this.singer = singer;
		this.count = count;
	}
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
