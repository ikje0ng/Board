package com.example.demo;

import com.example.demo.Entity.Board;
import com.example.demo.Repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest

class DemoApplicationTests {

	//멤버 변수 위해서 사용되며 생성자와 세터를 작성하지 않아도 Autowired가 이를 대체할 수 있다.
	//컨테이너는 Autowired를 보는 순간 자신이 만들어두었던 객체들의 타입을 확인한다
	@Autowired
	private BoardRepository boardRepo;

	//데이터 삽입
	@Test
	void func1() {
		for (int i=1;i<=300;i++)
		{
			Board board = Board.builder()
					.email("ccc"+i+"@naver.com")
					.pwd("1234")
					.subject("제목"+i)
					.content("내용"+i)
					.regdate("2021-00-00")
					.count(0)
					.build();

			//boardRepo에서 save해서 데이터 삽입
			boardRepo.save(board);
		}

	}

	//페이징처리
	@Test
	public void func2() {
		//페이지당 게시물의 개수
		Pageable pageble = PageRequest.of(0,10);	//0번째 위치에서 10개를 가져옴
		//게시물 가져오기
		Page<Board> list = boardRepo.findAll(pageble); 		//참조변수 만들어서 연결
		for(Board board: list.getContent())					//list로 받아온 게시물 하나씩 왼쪽 board에 넣어줌
		{
			System.out.println(board);
		}
		System.out.println("총 페이지 수 : " + list.getTotalPages());			//총 페이지 수
		System.out.println("전체 게시물 수 : " + list.getTotalElements());	//전체 게시물 수
		System.out.println("현재 페이지 번호 : " + list.getNumber());			//페이지 번호: 0부터 시작
		System.out.println("페이지당 게시물 수 : " + list.getSize());			//페이지당 게시물 수
		System.out.println("다음 페이지 존재 여부 : " + list.hasNext());		//현재 페이지 기준으로 다음 페이지 있는가
		System.out.println("이전 페이지 존재 여부 : " + list.hasPrevious());	//현재 페이지 기준으로 다음 페이지 있는가
		System.out.println("시작 페이지 여부: " + list.isFirst());				//시작 페이지 여부
	}

	//내림차순 정렬
	@Test
	public void Func2() {
		//Sort(num기준으로 descending내림차순)
		Sort sort1 = Sort.by("num").descending();
		//페이지당 게시물의 개수
		Pageable pageble = PageRequest.of(0,10,sort1);	//0번째 위치에서 10개를 가져옴
		//게시물 가져오기
		Page<Board> list = boardRepo.findAll(pageble); 		//참조변수 만들어서 연결
		for(Board board: list.getContent())					//list로 받아온 게시물 하나씩 왼쪽 board에 넣어줌
		{
			System.out.println(board);
		}
	}
}
