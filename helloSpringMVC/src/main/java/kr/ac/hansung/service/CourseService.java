package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.CourseDAO;
import kr.ac.hansung.model.Course;
import kr.ac.hansung.model.CreditByCategory;
import kr.ac.hansung.model.CreditByYearTerm;


@Service
public class CourseService {

	@Autowired
	private CourseDAO courseDao;
	
	public List<CreditByYearTerm> getCourseByTerm() {
		return courseDao.getCourseByTerm();
	}
	
	public List<Course> getCourseByTermInDetail(int year, int term) {
		return courseDao.getCourseByTermInDetail(year, term);
	}

	public List<CreditByCategory> getCreditByCategory() {
		return courseDao.getCreditByCategory();
	}
	
	public void insert(Course course) {
	    courseDao.insert(course);
	}

}
