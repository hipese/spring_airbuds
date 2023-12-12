package com.kdt.controllers;

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
import org.springframework.web.bind.annotation.RestController;

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
//	@PostMapping("/register/{email}")
//	public ResponseEntity<String> password(@PathVariable String email) {
//		memberService.sendTemporaryPasswordEmail(email);
//		return ResponseEntity.ok("success");
//	}
	
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
	
}
