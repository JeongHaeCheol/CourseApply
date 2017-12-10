package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Course;
import kr.ac.hansung.model.CreditByCategory;
import kr.ac.hansung.model.CreditByYearTerm;



@Repository
public class CourseDAO {
	private JdbcTemplate jdbcTemplate;

	@Autowired // Autowired 쓸때 beans.xml에서 annotation기능 반드시 활성화!!!!!!!!!!!
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int getRowCount() {
		String sqlStatement = "select count(*) from course_record";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);

	}

	// query and return a single object
	public Course getCourse(String name) {
		String sqlStatement = "select * from course_record where name=?";

		// RowMapper.mapRow()는 결과를 객체에 저장해주는 역할을 한다. (mapRow메소드는 사용자가 구현해야한다.)
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { name }, new RowMapper<Course>() {

			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = new Course();
				course.setYear(rs.getInt("year"));
				course.setTerm(rs.getInt("term"));
				course.setCode(rs.getString("code"));
				course.setName(rs.getString("name"));
				course.setCategory(rs.getString("category"));
				course.setCredit(rs.getInt("credit"));

				return course;
			}

		});
	}

	// query and return a multiple object
	public List<Course> getCourseByTermInDetail(int year, int term) {
		String sqlStatement = "select * from course_record where year=? and term=?";

		// 여러개의 객체를 query할때는 query메소드 사용
		return jdbcTemplate.query(sqlStatement, new Object[] { year, term }, new RowMapper<Course>() {

			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

				Course course = new Course();
				course.setYear(rs.getInt("year"));
				course.setTerm(rs.getInt("term"));
				course.setCode(rs.getString("code"));
				course.setName(rs.getString("name"));
				course.setCategory(rs.getString("category"));
				course.setCredit(rs.getInt("credit"));

				return course;
			}

		});

	}
	
	// query and return a multiple object
	public List<CreditByYearTerm> getCourseByTerm() {
		String sqlStatement = "select year, term, sum(credit) from course_record group by year ASC, term ASC";

		// 여러개의 객체를 query할때는 query메소드 사용
		return jdbcTemplate.query(sqlStatement, new RowMapper<CreditByYearTerm>() {

			public CreditByYearTerm mapRow(ResultSet rs, int rowNum) throws SQLException {

				CreditByYearTerm creditByYearTerm = new CreditByYearTerm();
				creditByYearTerm.setYear(rs.getInt("year"));
				creditByYearTerm.setTerm(rs.getInt("term"));
				creditByYearTerm.setCredit(rs.getInt("sum(credit)"));
				return creditByYearTerm;
			}

		});

	}
	

	public List<CreditByCategory> getCreditByCategory() {
		String sqlStatement = "select category, sum(credit) from course_record group by category";

		// 여러개의 객체를 query할때는 query메소드 사용
		return jdbcTemplate.query(sqlStatement, new RowMapper<CreditByCategory>() {

			public CreditByCategory mapRow(ResultSet rs, int rowNum) throws SQLException {

				CreditByCategory creditByCategory = new CreditByCategory();
				creditByCategory.setCategory(rs.getString("category"));
				creditByCategory.setCredit(rs.getInt("sum(credit)"));

				return creditByCategory;
			}

		});

	}

	public boolean insert(Course course) {
		int year = course.getYear();
		int term = course.getTerm();
		String code = course.getCode();
		String name = course.getName();
		String category = course.getCategory();
		int credit = course.getCredit();

		String sqlStatement = "insert into  course_record (year, term, code, name, category, credit) values(?,?,?,?,?,?)";

		// update 리턴값은 integer값 (몇개가 업데이트 됐는지)이기 때문에 == 1 조건을 리턴
		return (jdbcTemplate.update(sqlStatement, new Object[] {year, term, code, name, category, credit}) == 1);
	}

	public boolean update(Course course) {

		int year = course.getYear();
		int term = course.getTerm();
		String code = course.getCode();
		String name = course.getName();
		String category = course.getCategory();
		int credit = course.getCredit();
		
		String sqlStatement = "update course_record set year=? term=? code=? name=? category=? credit=? where c=?";

		// update 리턴값은 integer값 (몇개가 업데이트 됐는지)이기 때문에 == 1 조건을 리턴
		return (jdbcTemplate.update(sqlStatement, new Object[] { year, term, code, name, category, credit }) == 1);
	}

	public boolean delete(String code) {

		String sqlStatement = "delete from course_record where code=?";

		// update 리턴값은 integer값 (몇개가 업데이트 됐는지)이기 때문에 == 1 조건을 리턴
		return (jdbcTemplate.update(sqlStatement, new Object[] { code }) == 1);
	}

}