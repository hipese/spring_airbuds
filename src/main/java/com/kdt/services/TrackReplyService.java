package com.kdt.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.TrackReply;
import com.kdt.dto.TrackReplyDTO;
import com.kdt.mappers.TrackReplyMapper;
import com.kdt.repositories.TrackReplyRepository;

@Service
public class TrackReplyService {

	@Autowired
	private TrackReplyRepository trRepo;
	
	@Autowired
	private TrackReplyMapper trMapper;
	
	public void insert(TrackReplyDTO dto) {
		Instant time = Instant.now();
		dto.setWriteDate(time);
		TrackReply reply = trMapper.toEntity(dto);
		trRepo.save(reply);
	}
	
	public List<TrackReplyDTO> selectByTrackId(Long seq){
		List<TrackReply> replies = trRepo.selectAllByParentSeq(seq);
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
