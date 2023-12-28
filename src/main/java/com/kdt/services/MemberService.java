package com.kdt.services;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.domain.entity.Member;
import com.kdt.dto.MemberDTO;
import com.kdt.mappers.MemberMapper;
import com.kdt.repositories.MemberDAO;
import com.kdt.repositories.MemberRepository;
import com.kdt.security.SecurityUser;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	private MemberRepository mRepo;
	
	@Autowired
	private MemberMapper mMapper;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MemberDAO memberdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member m = mRepo.findById(username).get();
		SecurityUser su =new SecurityUser(m);
		su.setName(m.getName());
		return su;
	}
	
	@Transactional
	public void sendVerificationEmail(String email) {
		// 인증 코드 생성
		String verificationCode = generateRandomVerificationCode(6); // 6자리 인증 코드 생성
		// 세션에 인증 코드 저장
		saveVerificationCodeInSession(verificationCode);
		// 이메일 전송
		sendVerificationEmail(email, verificationCode);
	}

	public boolean verifyMember(String verificationCode) {
		// 세션에서 저장된 인증 코드 가져오기
		String storedVerificationCode = getVerificationCodeFromSession();
		// 입력된 인증 코드와 세션에 저장된 인증 코드 비교
		return verificationCode.equals(storedVerificationCode);
	}

	// 인증 코드 생성: n 자리의 랜덤한 숫자 문자열 생성
	private String generateRandomVerificationCode(int length) {
		StringBuilder verificationCode = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			verificationCode.append(random.nextInt(10)); // 0부터 9까지의 난수 생성
		}
		return verificationCode.toString();
	}

	// 세션에 인증 코드 저장
	private void saveVerificationCodeInSession(String verificationCode) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		attributes.getRequest().getSession(true).setAttribute("verificationCode", verificationCode);
	}

	// 세션에서 인증 코드 가져오기
	private String getVerificationCodeFromSession() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return (String) attributes.getRequest().getSession(true).getAttribute("verificationCode");
	}

	// 인증번호 전송 메서드
	private void sendVerificationEmail(String email, String verificationCode) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();

			message.addRecipients(RecipientType.TO, email);
			message.setFrom(new InternetAddress("kdtgroovy@gmail.com","Groovy"));
			message.setSubject("Groovy 인증코드");
			String msgg="";
			msgg+= "<div style='margin:20px;'>";
			msgg+= "<h1> Groovy에 오신것을 환영합니다. </h1>";
			msgg+= "<br>";
			msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
			msgg+= "<br>";
			msgg+= "<div align='center' style='border:1px solid black; font-family:verdana;'>";
			msgg+= "<h3 style='color:blue;'>인증 코드입니다.</h3>";
			msgg+= "<div style='font-size:130%'>";
			msgg+= "CODE : <strong>";
			msgg+= verificationCode+"</strong></div><br/> ";
			msgg+= "</div>";
			msgg+= "</div>";

			message.setText(msgg,"utf-8", "html");

			javaMailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 임시 비밀번호 생성
	private String generateRandomPasswordCode(int length) {
		if (length < 8) {
			throw new IllegalArgumentException("Password length must be at least 8");
		}

		Random random = new Random();

		StringBuilder password = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			password.append(random.nextInt(10));
		}

		password.append((char) ('A' + random.nextInt(26)));

		password.append((char) ('a' + random.nextInt(26)));

		for (int i = 5; i < length; i++) {
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			int index = random.nextInt(characters.length());
			password.append(characters.charAt(index));
		}

		List<Character> charList = password.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
		Collections.shuffle(charList);
		StringBuilder shuffledPassword = new StringBuilder();
		charList.forEach(shuffledPassword::append);

		return shuffledPassword.toString();
	}

	// 임시 비밀번호 생성 및 DB 변경 및 메일 전송 메서드
	public void sendTemporaryPasswordEmail(String id, String email) {
		// 생성한 임시 비밀번호
		String temporaryPassword = generateRandomPasswordCode(8);
		// 비밀번호 변경
		changePassword(id, temporaryPassword);
		// 이메일 전송
		sendPasswordEmail(email, temporaryPassword);
	}
	
	// 임시 비밀번호로 비밀번호 변경 메서드
	@Transactional
	public void changePassword(String id, String temporaryPassword) {
	    Member member = mRepo.findById(id).orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

	    // 비밀번호를 발급 받은 임시비밀번호로 변경
	    String encodedPassword = new BCryptPasswordEncoder().encode(temporaryPassword);
	    member.setPassword(encodedPassword);
	    mRepo.save(member);
	}

	// 임시 비밀번호 전송 메서드
	private void sendPasswordEmail(String email, String temporaryPassword) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();

			// 이메일 구성
			message.addRecipients(RecipientType.TO, email);
			message.setFrom(new InternetAddress("kdtgroovy@gmail.com", "Groovy"));
			message.setSubject("Groovy 임시 비밀번호");

			// 이메일 본문 구성
			String msgBody = "<div style='margin:20px;'>";
			msgBody += "<h1> 임시 비밀 번호를 발급 </h1>";
			msgBody += "<br/>";
			msgBody += "<p>아래 임시 비밀번호를 복사해 입력해주세요<p>";
			msgBody += "<br/>";
			msgBody += "<div align='center' style='border:1px solid black; font-family:verdana;'>";
			msgBody += "<h3 style='color:blue;'>로그인 후 비밀번호를 수정 해주세요</h3>";
			msgBody += "<div style='font-size:120%'>";
			msgBody += "임시 비밀번호 : <strong>" + temporaryPassword + "</strong></div><br/> ";
			msgBody += "</div>";
			msgBody += "</div>";

			// 메시지 설정
			message.setText(msgBody, "utf-8", "html");

			// 이메일 전송
			javaMailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberDTO findMemberById(String id){
		Member m = mRepo.findAllById(id);
		MemberDTO dto = mMapper.toDto(m);
		return dto;
	}
	
	public List<MemberDTO> findId(String name, String email){
		List<Member> list = mRepo.findByNameAndEmail(name,email);
		List<MemberDTO> dtos = mMapper.toDtoList(list);
		return dtos;
	}
	
	public MemberDTO findEmail(String id){
		Member m = mRepo.findAllById(id);
		MemberDTO dto = mMapper.toDto(m);
		return dto;
	}
	
	public boolean isDupleID(String id) {
		Optional<Member> m = mRepo.findById(id);
		if(m.isEmpty())
			return false;
		else 
			return true;
	}
	
	public void register(MemberDTO dto) throws Exception {
		dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
		dto.setRole("ROLE_MEMBER");
		dto.setEnabled(true);
		
		Member m = mMapper.toEntity(dto);
		mRepo.save(m);
	}
	
	public Map<String, String> getProfiles(String targetID) {
		Map<String, String> images = new HashMap<>();
		Member m = mRepo.findById(targetID).get();
		images.put("profile_image", m.getProfile_image());
		images.put("background_image", m.getBackground_image());
		return images;
	}
	
	public void uploadBackgroundImage(MultipartFile file, String userID) throws Exception {
		if(file == null) {
			throw new Exception("file is empty");
		}
		if(userID == null) {
			throw new Exception("userID is undefined");
		}
		
		File imagePath = new File("c:/backgroundImages");
		if (!imagePath.exists()) {
			imagePath.mkdir();
		}
		
		String filename = file.getOriginalFilename();
		String sys_filename = UUID.randomUUID() + "_" + filename;
		File destImageFile = new File(imagePath, sys_filename);
        file.transferTo(destImageFile);
        
        Member m = mRepo.findById(userID).get();
        m.setBackground_image("/backgroundImages/" + sys_filename);
        mRepo.save(m);
        
		
	}
	
	public String uploadProfileImage(MultipartFile file, String userID) throws Exception {
		
		if(file == null) {
			throw new Exception("file is empty");
		}
		if(userID == null) {
			throw new Exception("userID is undefined");
		}
		
		File imagePath = new File("c:/profileImages");
		if (!imagePath.exists()) {
			imagePath.mkdir();
		}
		
		String filename = file.getOriginalFilename();
		String sys_filename = UUID.randomUUID() + "_" + filename;
		File destImageFile = new File(imagePath, sys_filename);
        file.transferTo(destImageFile);
        
        String pathName = "/profileImages/" + sys_filename;
        
        Member m = mRepo.findById(userID).get();
        m.setProfile_image(pathName);
        mRepo.save(m);
        return pathName;
	}
	
	public void changeID(String id, String newID) {
		memberdao.updateId(id, newID);
	}
	
	public boolean checkPW(String id, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Member m = mRepo.findById(id).get();
		if(encoder.matches(password, m.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void changePW(String id, String newPassword) {
		newPassword = new BCryptPasswordEncoder().encode(newPassword);
		Member m = mRepo.findById(id).get();
		m.setPassword(newPassword);
		mRepo.save(m);
	}
	
	public void changeUserInfo(String id, MemberDTO dto) {
		Member m = mRepo.findById(id).get();
		m.setName(dto.getName());
		m.setBirth(dto.getBirth());
		m.setContact(dto.getContact());
		mRepo.save(m);
	}
}