package com.test.java.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.test.java.DBUtil;

// JDBC코드 담당>DB업무담당
public class DAO {


	// 공통인 요소들은
	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	ResultSet rs = null;

	public DAO() { // 생성자로 뺴주기
		conn = DBUtil.open(); // 초기화
	}


	public int add(Address dto) {

		try {
			conn = DBUtil.open();

			String sql = "insert into tblAddress (seq, name, age, gender, tel, address, regdate) "
					+ "values (seqAddress.nextVal, ?, ?, ?, ?, ?, default)";

			// 1.sql실행
			pstat = conn.prepareCall(sql);


			// 2.값 넣기
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getAge());
			pstat.setString(3, dto.getGender());
			pstat.setString(4, dto.getTel());
			pstat.setString(5, dto.getAddress());

			// 3.쿼리 실행
			int result = pstat.executeUpdate();
			return result;


		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	// 목록가져오기
	public ArrayList<Address> list() {
		try {

			String sql = "select*from tblAddress order by seq asc";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);



			// resultset -> arrayList
			ArrayList<Address> list = new ArrayList<Address>();

			while (rs.next()) {
				// 레코드 1개> address 1개

				Address dto = new Address((rs.getString("seq")), (rs.getString("name")),
						(rs.getString("age")), (rs.getString("gender")), (rs.getString("tel")),
						(rs.getString("address")), (rs.getString("regdate")));
				// 객체에 하나씩 추가해줌


				list.add(dto);


			}



			return list; // service에게 반환

		} catch (Exception e) {

		}
		return null;
	}


	// seq에 해당하는 레코드 한줄을 가져와서 dto로 리턴함
	public Address get(String seq) {
		try {
			String sql = "select*from tblAddress where seq = ?";

			pstat = conn.prepareStatement(sql); // 1.sql실행

			pstat.setString(1, seq); // 2. ?값넣기

			rs = pstat.executeQuery(); // 3. 쿼리 실행

			if (rs.next()) {
				// 레코드 1개 > addres1개로 바꿔서 전달
				Address dto = new Address();
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getString("age"));
				dto.setGender(rs.getString("gender"));
				dto.setTel(rs.getString("tel"));
				dto.setAddress(rs.getString("address"));
				dto.setRegdate(rs.getString("regdate"));

				return dto; // 1행만 이기때문에 return으로

			}

		} catch (Exception e) {

		}
		return null;
	}



	// DB->update
	public int edit(Address dto) {

		try {

			// 모든 컬럼을 전부 업데이트함 > sql를 1개를 만들기 위해서 !!!
			conn = DBUtil.open();

			String sql =
					"update tblAddress set name = ?, age = ?, gender = ?, tel = ?, address = ? where seq = ?";

			// 1.sql실행
			// 매개변수가 있을경우 sql을 실행할때 preparecall사
			pstat = conn.prepareCall(sql);


			// 2.값 넣기 (미리 dto에 값을 다 넣어놓음)
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getAge());
			pstat.setString(3, dto.getGender());
			pstat.setString(4, dto.getTel());
			pstat.setString(5, dto.getAddress());
			pstat.setString(6, dto.getSeq());

			// 3.쿼리 실행
			int result = pstat.executeUpdate();
			return result;


		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	public int del(String seq) {

		try {
			String sql = "delete tblAddress where seq =?";

			// 1.sql실행
			pstat = conn.prepareCall(sql);


			// 2.값 넣기
			pstat.setString(1, seq);


			// 3.쿼리 실행
			int result = pstat.executeUpdate();
			return result; // 1성공 2실패


		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


}


