package com.smhrd.myapp.entity;

import com.smhrd.myapp.vo.MemberVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity annotation이 안나오면 JAP 라이브러리를 가져오지 않은거다
@Entity // DB에 있는 Table을 표현하는 자료형
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
	
	public MemberEntity(MemberVO vo) {
		// TODO Auto-generated constructor stub
		this.id = vo.getId();
		this.pw = vo.getPw();
		name = vo.getName();
		tel = vo.getTel();
	}
	@Id // pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long idx; // JAP 쓰려면 필수로 적어주기
	
	// 유니크, 길이, null허용 등등 설정
	@Column(unique = true) // 해당 컬럼에 대한 설정값
	private String id;
	@Column(length = 200)
	private String pw;
	@Column(nullable = false)
	private String name;
	private String tel;
}
