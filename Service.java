package com.test.java.mvc;

import java.util.ArrayList;
import java.util.Scanner;

public class Service {

	public void start() {
		Scanner scan = new Scanner(System.in);

		boolean loop = true;

		while (loop) {

			View view = new View();
			view.menu();

			String input = scan.nextLine();

			if (input.equals("1")) {
				add();
			} else if (input.equals("2")) {
				list();
			} else if (input.equals("3")) {
				edit();
			} else if (input.equals("4")) {
				del();
			} else {

			}
		}
	}

	// 값넣기
	private void add() {
		System.out.println("[주소록 등록하기]");
		Scanner scan = new Scanner(System.in);

		System.out.println("이름 : ");
		String name = scan.nextLine();

		System.out.println("나이 : ");
		String age = scan.nextLine();

		System.out.println("성별(m,f) : ");
		String gender = scan.nextLine();

		System.out.println("전화번호 : ");
		String tel = scan.nextLine();

		System.out.println("주소 : ");
		String address = scan.nextLine();

		// DB담당자
		DAO dao = new DAO();

		// Service -> DTO(데이터 넘겨주는 역활) ->DAO
		Address dto = new Address();

		dto.setName(name);
		dto.setAge(age);
		dto.setGender(gender);
		dto.setTel(tel);
		dto.setAddress(address);


		int result = dao.add(dto); // 1.dao에서 1혹은 0을 넘겨받음!!!!!

		if (result == 1) {
			System.out.println("주소록 등록을 성공했습니다.");
		} else if (result == 0) {
			System.out.println("주소록 등록을 실패했습니다.");
		}

		pause();


	}

	// 값 출력
	private void list() {
		System.out.println("[주소록 목록보기]");
		// 1.DAO 위임 >DB>select
		// 2.결과셋 반환
		// 3.view한테 넘김
		DAO dao = new DAO();

		// ***********Connection,Statement,ResultSet -> 반드시 DAO.java에서만 코딩!!!!
		// address = 레코드 한줄
		// arrayList = 테이블 역활
		ArrayList<Address> list = dao.list();
		// System.out.println(list.size());

		View view = new View();
		view.list(list);

		pause();
	}



	private void edit() {
		System.out.println("[주소를 수정하기]");

		// 수정할 번호 > 입력 > 현재 내용은 출력 > 수정할 데이터 입력
		Scanner scan = new Scanner(System.in);

		System.out.println("수정할 번호 :  ");
		String seq = scan.nextLine();

		DAO dao = new DAO();


		/*
		 * 주의!!!!!!!!!
		 */
		Address dto = dao.get(seq); // 해당 seq의 번호 ->dao에 넣고 행을 가져옴- >dto에 넣어줌

		// 출력
		System.out.println("이름 " + dto.getName());
		System.out.println("나이 " + dto.getAge());
		System.out.println("성별 " + dto.getGender());
		System.out.println("전화번호 " + dto.getTel());
		System.out.println("주소 " + dto.getAddress());


		System.out.println();

		System.out.println("수정할 항목");

		// 직접수정
		System.out.print("이름: ");
		String name = scan.nextLine();

		if (!name.equals("")) {
			dto.setName(name); // 수정
		}

		System.out.print("나이: ");
		String age = scan.nextLine();

		if (!age.equals("")) {
			dto.setAge(age);
		}

		System.out.print("성별: ");
		String gender = scan.nextLine();

		if (!gender.equals("")) {
			dto.setGender(gender);
		}

		System.out.print("전화: ");
		String tel = scan.nextLine();

		if (!tel.equals("")) {
			dto.setTel(tel);
		}

		System.out.print("주소: ");
		String address = scan.nextLine();

		if (!address.equals("")) {
			dto.setAddress(address);
		}


		/*
		 * 위에서 받은 값을 다시 dto에 저장 시켜줌!!!
		 */
		// DB->update
		int result = dao.edit(dto);

		if (result == 1) {
			System.out.println("주소록 수정 완료");
		} else {
			System.out.println("실패");
		}

		pause();
	}


	private void del() {
		System.out.println("[주소 삭제]");

		Scanner scan = new Scanner(System.in);

		System.out.println("삭제 할 번호");
		String seq = scan.nextLine();

		DAO dao = new DAO();

		int result = dao.del(seq);



		if (result == 1) {
			System.out.println("주소록 수정 완료");
		} else {
			System.out.println("실패");
		}

	}



	private static void pause() {
		Scanner scan = new Scanner(System.in);
		System.out.println("계속하려면 엔터를 입력하세요");
		scan.nextLine();
	}


}
