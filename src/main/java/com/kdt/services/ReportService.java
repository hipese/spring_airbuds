package com.kdt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kdt.domain.entity.Report;
import com.kdt.domain.entity.ReportAnswer;
import com.kdt.dto.ReportDTO;
import com.kdt.mappers.ReportMapper;
import com.kdt.repositories.ReportAnswerRepository;
import com.kdt.repositories.ReportRepository;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

@Service
public class ReportService {

	@Autowired
	private ReportRepository rRepo;
	
	@Autowired
	private ReportMapper rMapper;
	
	@Autowired
	private ReportAnswerRepository raRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void insertReport(ReportDTO dto) {
		Report ab = rMapper.toEntity(dto);
		rRepo.save(ab);
	}
	
	public List<ReportDTO> selectAll(){
		List<Report> list = rRepo.findAll();
		List<ReportDTO> dtos = rMapper.toDtoList(list);
		return dtos;
	}

	public ReportDTO getContents(Long seq) throws Exception{
		Report Report = rRepo.findById(seq).get();
		ReportDTO dto = rMapper.toDto(Report);
		return dto;
	}
	
	public void deletePost(Long seq) throws Exception{
		Report Report = rRepo.findById(seq).get();
		
		//Answer delete
		List<ReportAnswer> qaList = raRepo.selectAllByParentSeq(seq);
		if(qaList.size() > 0) {
			for(ReportAnswer qa : qaList) {
				raRepo.delete(qa);
			}
		}
		//Post Delete
		rRepo.delete(Report);	
	}
	
	// 내용 전송 메서드
	public void sendEmail(String email, String contents) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();

			message.addRecipients(RecipientType.TO, email);
			message.setFrom(new InternetAddress("kdtgroovy@gmail.com","Groovy"));
			message.setSubject("Groovy 신고 관련 안내 드립니다.");
			String msgg="";
			msgg+= "<div style='margin:20px;'>";
			msgg+= "<h1> Groovy 신고 안내 </h1>";
			msgg+= "<br>";
			msgg+= "<p>현재 사용자의 신고를 받아 처리중에 있습니다. 신고에 대한 답변을 안내해드립니다.<p>";
			msgg+= "<br>";
			msgg+= "<div align='center' style='border:1px solid black; font-family:verdana;'>";
			msgg+= "<h3 style='color:blue;'>신고 답변은 다음과 같습니다.</h3>";
			msgg+= "<div style='font-size:130%'>";
			msgg+= contents+"</div><br/> ";
			msgg+= "</div>";
			msgg+= "</div>";

			message.setText(msgg,"utf-8", "html");

			javaMailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 답변 완료
	public void changeState(Long reportSeq) {
		Report r = rRepo.findById(reportSeq).get();
		ReportDTO dto = new ReportDTO();
		dto.setReportAnswerState(1L);
		
		rMapper.updateEntityFromDto(dto, r);
		rRepo.save(r);	
	}
}
