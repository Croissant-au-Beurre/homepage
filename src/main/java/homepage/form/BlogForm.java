package homepage.form;

import lombok.Data;

@Data
public class BlogForm {
	private int seq;
	
	private String title;
	
	private String content;
	
	private String date;
	
	private String imgPath;
}
