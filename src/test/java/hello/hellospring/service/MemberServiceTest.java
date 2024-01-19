package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {
	

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStor();
	}
	
	@Test
	void 연습가입() {
		
		//given 목표
		Member member = new Member();
		
		member.setName("spring");
		
		//when 할때
		Long saveId = memberService.join(member);
		
		
		
		//then 결과
		Member findMember = memberService.findone(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());

	}
	
	
	@Test
	public void 중복회원예외() {
		//given 담고
		Member member1= new Member();
		member1.setName("spring");
		Member member2= new Member();
		member2.setName("spring");
		//when 할때
		memberService.join(member1);
		
		//assertThrows 방법으로 확인
		IllegalStateException e = assertThrows(IllegalStateException.class ,() -> memberService.join(member2));
		//assertThrows(NullPointerException.class ,() -> memberService.join(member2));
		
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
		
		
		// try catch 문법으로 예외 상황 확인
//		try {
//		memberService.join(member2);	
//		fail();
//		} catch (IllegalStateException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
//		}
		
		//then 결과
	}

	@Test
	void testValidateDuplicateMember() {
	}

	@Test
	void testFindMembers() {
	}

	@Test
	void testFindone() {
	}

}
