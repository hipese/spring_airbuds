package com.kdt.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kdt.dto.MemberDTO;
import com.kdt.dto.PasswordDTO;
import com.kdt.services.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	
	// 아이디를 기준으로 Member 정보를 뽑아내는
	@PostMapping("/{loginId}")
	public ResponseEntity<MemberDTO> selectMember(@PathVariable String loginId) {
		MemberDTO dto = memberService.findMemberById(loginId);
		return ResponseEntity.ok(dto);
	}

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
			logger.error(e.getMessage());;
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
	
	@GetMapping("/getProfile")
	public ResponseEntity<Map<String, String>> getProfile() {
		
		String userID = "";
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {
				userID = ((UserDetails) principal).getUsername();
			} else {
				userID = principal.toString(); 
			}
		}
		
		if (userID == "") {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		return ResponseEntity.ok(memberService.getProfiles(userID));
	}
	
	@PostMapping("/uploadBackground")
	public ResponseEntity<Void> uploadBackgroundImage(@RequestParam MultipartFile newBgImage, Principal principal) {
		try {
			memberService.uploadBackgroundImage(newBgImage, principal.getName());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@PostMapping("/uploadProfile")
	public ResponseEntity<String> uploadProfileImage(@RequestParam MultipartFile newProfileImage, Principal principal) {
		try {
			String path = memberService.uploadProfileImage(newProfileImage, principal.getName());
			return ResponseEntity.ok(path);
		} catch (Exception e) {
			logger.error(e.getMessage());;
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/changeID")
	public ResponseEntity<Void> changeID(@RequestBody String newID) {
		String userID = "";
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {
				userID = ((UserDetails) principal).getUsername();
			} else {
				userID = principal.toString(); 
			}
		}
		
		if (userID == "") {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		memberService.changeID(userID, newID);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/changePW")
	public ResponseEntity<Void> changePW(@RequestBody PasswordDTO dto) {

		String userID = "";
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {
				userID = ((UserDetails) principal).getUsername();
			} else {
				userID = principal.toString(); 
			}
		}
		
		if (userID == "") {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if(!memberService.checkPW(userID, dto.getPassword()))
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		memberService.changePassword(userID, dto.getNewPassword());
		return ResponseEntity.ok().build();
	}

	@PostMapping("/changeUserInfo")
	public ResponseEntity<Void> changeUserInfo(@RequestBody MemberDTO dto) {
		
		String userID = "";
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {
				userID = ((UserDetails) principal).getUsername();
			} else {
				userID = principal.toString(); 
			}
		}
		
		if (userID == "") {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		memberService.changeUserInfo(userID, dto);
		return ResponseEntity.ok().build();
	}
}
