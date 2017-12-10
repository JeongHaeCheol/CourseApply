package kr.ac.hansung.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.model.Course;
import kr.ac.hansung.model.CreditByCategory;
import kr.ac.hansung.model.CreditByYearTerm;
import kr.ac.hansung.service.CourseService;

@Controller
public class CheckController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/checkByTerm")
	public String checkByTerm(Model model) {
		List<CreditByYearTerm> creditByYearTerms = courseService.getCourseByTerm();
		model.addAttribute("creditByYearTerms", creditByYearTerms);

		return "checkByTerm";
	}

	@RequestMapping(value = "/credit_detail")
	public String creditDetail(Model model, @RequestParam("year") int year, @RequestParam("term") int term) {
		List<Course> courseByTermDetails = courseService.getCourseByTermInDetail(year, term);
		model.addAttribute("courseByTermDetails", courseByTermDetails);

		return "credit_detail";
	}

	@RequestMapping(value = "/checkByCategory")
	public String checkByCategory(Model model) {
		List<CreditByCategory> creditByCategory = courseService.getCreditByCategory();
		model.addAttribute("creditByCategory", creditByCategory);
		return "checkByCategory";
	}

	@RequestMapping("/courseApplication")
	public String courseApplication(Model model) {
		model.addAttribute("course", new Course());

		return "courseApplication";
	}

	@RequestMapping("/doApply")
	public String doApply(HttpServletRequest request, Model model, @Valid Course course, BindingResult result) {
		 try {
	            request.setCharacterEncoding("UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }		
		 
		if (result.hasErrors()) {
			System.out.println("===Form data doest not validated");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "courseApplication";
		}
		courseService.insert(course);
		return "appliedCourse";
	}
	
	@RequestMapping(value = "/show2018")
	public String show2018(Model model) {
		List<Course> courseByTerm2018 = courseService.getCourseByTermInDetail(2018, 1);
		model.addAttribute("courseByTerm2018", courseByTerm2018);
		return "show2018";
	}
}
