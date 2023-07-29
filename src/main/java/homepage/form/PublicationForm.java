package homepage.form;

import lombok.Data;

@Data
public class PublicationForm {

	private Boolean isAdimin;
	
	private int seq;
	
	private String auteurs;

	private String title;

	private String doi;

	private String url;
}
