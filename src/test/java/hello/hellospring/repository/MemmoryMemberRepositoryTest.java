package hello.hellospring.repository;

import org.apache.catalina.Store;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import domain.Member;
import static org.assertj.core.api.Assertions.*; //Assertions를 기본 메소드로 넣고 메소드만쓴다.

import java.util.List;

public class MemmoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();

	// 테스트가 중첩되다보면 다른 메소드가 새로운 클래스에 영향을 준다
	
	@AfterEach
	public void afterEach() {
		
		repository.clearStor();
		
	}
	
	
	@Test
	public void save() {
		Member member = new Member();
		
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		
		assertThat(member).isEqualTo(result);
		
		
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		
		member1.setName("spring2");
		repository.save(member2);
		
		
		Member result = repository.findByName("spring2").get();
		
		assertThat(result).isEqualTo(member1);
		
	}
	
	@Test
	public void findAll() {
		
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);

	}
	
	
	
	
}
