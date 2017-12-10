package kr.ac.hansung.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {

	@Min(value=2018, message="Year must be 2018")
	@Max(value=2018, message="Year must be 2018")
	private int year;
	
	@Min(value=1, message="Term must be 1")
	@Max(value=1, message="Term must be 1")
	private int term;
	
	@NotEmpty(message="The code cannot be empty")
	private String code;
	
	@Size(min=2, max=100, message="Name must between 2 and100 chars")
	private String name;
	
	@Size(min=2, max=100, message="category must between 2 and100 chars")
	private String category;
	
	@Min(value=1, message="Credit 1~4")
	@Max(value=4, message="Credit 1~4")
	private int credit;

}