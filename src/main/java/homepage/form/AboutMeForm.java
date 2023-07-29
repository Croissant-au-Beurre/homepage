package homepage.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AboutMeForm {

	private Boolean isAdimin;
	
	private int seq;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date insDate;
	
	private String news;
	
	private String url;
}
