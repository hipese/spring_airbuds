package com.kdt.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.MemberDTO;
import com.kdt.services.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	// 이메일로 전송하는
	@PostMapping("/register/{email}")
	public ResponseEntity<String> register(@PathVariable String email) {
		memberService.sendVerificationEmail(email);
		return ResponseEntity.ok("success");
	}

	// 인증번호 확인하는
	@PostMapping("/verify/{code}")
	public ResponseEntity<String> verify(@PathVariable String code) {
		memberService.verifyMember(code);
		if(memberService.verifyMember(code)) {         
			return ResponseEntity.ok("success");
		}
		return ResponseEntity.ok("fail");
	}

	// 임시 비밀번호 전송하는
	@PostMapping("/password")
	public ResponseEntity<String> password(@RequestParam String id, @RequestParam String email) {
		MemberDTO dto = memberService.findMemberById(id);
		memberService.sendTemporaryPasswordEmail(dto.getId(),email);
		memberService.changePassword(dto.getId(),"");
		return ResponseEntity.ok("success");
	}

	// 아이디 찾는
	@PostMapping("/findId")
	public ResponseEntity <List<MemberDTO>> findId(@RequestParam String name, @RequestParam String email) {
		List<MemberDTO> list = memberService.findId(name,email);
		return ResponseEntity.ok(list);
	}

	@PostMapping("/checkID")
	public ResponseEntity<Boolean> checkID(@RequestBody MemberDTO dto) {
		return ResponseEntity.ok(memberService.isDupleID(dto.getId()));
	}

	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody MemberDTO dto) {
		try {
			memberService.register(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/isLogined")
	public ResponseEntity<String> isLogined() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {
				return ResponseEntity.ok(((UserDetails) principal).getUsername());
			} else {
				return ResponseEntity.ok(principal.toString()); 
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@GetMapping("/getProfiles/{targetID}")
	public ResponseEntity<Map<String, String>> getProfiles(@PathVariable String targetID) {
		return ResponseEntity.ok(memberService.getProfiles(targetID));
	}
	
	@PostMapping("/uploadBackground")
	public ResponseEntity<Void> uploadBackgroundImage(@RequestParam MultipartFile newBgImage, Principal principal) {
		try {
			memberService.uploadBackgroundImage(newBgImage, principal.getName());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

}
