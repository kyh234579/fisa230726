package com.fisa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.exception.DeptNotFoundException;
import com.fisa.model.dao.DeptCopyRepository;
import com.fisa.model.domain.entity.DeptCopy;

@RestController
public class DeptCopyController {

	//DAO를 멤버로 선언 및 자동 초기화
	@Autowired //타입에 맞는 spring bean 등록
	private DeptCopyRepository dao;
	/*
	 * private int deptno; 
		private String dname;
		private String loc;
	 */
	
	//데이터 저장
	@PostMapping("insert")
	public DeptCopy insertDept(DeptCopy datas) {
		System.out.println("***** "+datas); //datas.toString()
		return dao.save(datas);
	}
	
	
	
	@GetMapping("deptone")
	public DeptCopy getDept(int deptno) throws Exception {
		Optional<DeptCopy> dept = dao.findById(deptno);
		System.out.println(dept);  //Optional.empty
		dept.orElseThrow(Exception::new); 
		
		return dept.get();		
	}
	
	//예외 전담 처리 메소드
	@ExceptionHandler
	public String exHandler(Exception e) {
		e.printStackTrace();		
		return "요청시 입력 데이터 재 확인 부탁합니다";
	}
	
	
	//모든 검색 
	//http://localhost/guestbook/deptall
	@GetMapping("deptall")
	public Iterable<DeptCopy> getDeptAll(){
		System.out.println("**************");		
		return dao.findAll();
	}
	
	
	//특정 부서 번호로 삭제
	
	@GetMapping("deptdelete")
	public String deleteDept(int deptno) throws DeptNotFoundException{
		dao.findById(deptno).orElseThrow(DeptNotFoundException::new); //로직 중지
		dao.deleteById(deptno);
		return "delete 성공";
	}
	
	@ExceptionHandler(DeptNotFoundException.class)
	public String exHandler(DeptNotFoundException e) {
		e.printStackTrace();
		return "해당 부서 번호는 존재하지 않습니다.";
	}

	
	@ExceptionHandler
	public String exHandler1(Exception e) {
		e.printStackTrace();
		return "요청시 입력 데이터 재확인 부탁드립니다.";
	}
	
	
}