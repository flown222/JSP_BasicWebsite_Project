package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO 
{
	private Connection conn;
	private PreparedStatement pstmt;  //sql 인젝션 같은 해킹기법을 방어를 위해 존
	private ResultSet rs;
	
	public UserDAO() { //데이터 접근 객체 
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimezone=Asia/Seoul";
			String dbID = "root";
			String dbPassword = "2402";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);  // SQL 정보에 접속하게 하 매개
		}catch(Exception e) {
			e.printStackTrace();    //예외 처리로 오류 발생시 출력 
		}	
	}
	
	public int login(String userID, String userPassword) {
		 String SQL = "SELECT userPassword FROM USER WHERE USERID = ?";  // SQL 안에서 사용될 쿼리
		 try {
			 pstmt = conn.prepareStatement(SQL);
			 pstmt.setString(1, userID);  // 쿼리문안의 ? 표에 userID 대입.
			 rs = pstmt.executeQuery();   // 결과를가져옴
			 if(rs.next()) {
				 if(rs.getString(1).equals(userPassword)) {
					 return 1; //로그인 성공 
				 }
				 else 
					 return 0; // 비밀번호 불일
			 }
			 return -1;  //아이디가 없을 경우.
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		 return -2; //데이터 베이스오류 
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate(); //INSERT 쿼리문 실행하면 반환 결과는 0이상 그외에는 음수가 나옴.
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류 
	}
}
