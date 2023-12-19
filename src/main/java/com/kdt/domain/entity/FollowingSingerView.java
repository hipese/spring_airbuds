package com.kdt.domain.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Immutable
public class FollowingSingerView {

	@Id
	@Column(name = "follower")
	private String follower;
	
	@Column(name = "profile_image")
	private String profileImage;
	
	@Column(name = "singer")
	private String singer;
	
	@Column(name = "count")
	private Long count;

	public FollowingSingerView() {
		super();
	}

	public FollowingSingerView(String follower, String profileImage, String singer, Long count) {
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
