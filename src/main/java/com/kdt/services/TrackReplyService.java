package com.kdt.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.ReplyLikeCount;
import com.kdt.domain.entity.TrackReply;
import com.kdt.dto.ReplyLikeCountDTO;
import com.kdt.dto.TrackReplyDTO;
import com.kdt.mappers.ReplyLikeCountMapper;
import com.kdt.mappers.TrackReplyMapper;
import com.kdt.repositories.ReplyLikeCountRepository;
import com.kdt.repositories.TrackReplyRepository;

@Service
public class TrackReplyService {

	@Autowired
	private TrackReplyRepository trRepo;
	
	@Autowired
	private ReplyLikeCountRepository lcRepo;
	
	@Autowired
	private TrackReplyMapper trMapper;
	
	@Autowired
	private ReplyLikeCountMapper lcMapper;
	
	public void insert(TrackReplyDTO dto) {
		Instant time = Instant.now();
		dto.setWriteDate(time);
		TrackReply reply = trMapper.toEntity(dto);
		trRepo.save(reply);
	}
	
	public List<ReplyLikeCountDTO> selectByTrackId(Long seq){
		List<ReplyLikeCount> replies = lcRepo.selectAllByParentSeq(seq);
		List<ReplyLikeCountDTO> replyDtos = lcMapper.toDtoList(replies);
		return replyDtos;
	}
	
	public List<TrackReplyDTO> selectByWriter(String writer){
		List<TrackReply> replies = trRepo.selectAllById(writer);
		List<TrackReplyDTO> replyDtos = trMapper.toDtoList(replies);
		return replyDtos;
	}
	
	public void deleteReply(Long seq) {
		TrackReply reply = trRepo.findById(seq).get();
		trRepo.delete(reply);
	}
	
	public void updateReply (TrackReplyDTO dto) {
		TrackReply reply = trRepo.findById(dto.getSeq()).get();
		trMapper.updateEntityFromDto(dto, reply);
		trRepo.save(reply);
	}
		
}
