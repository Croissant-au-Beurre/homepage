package homepage.entity;

import java.util.Date;


import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;

@Entity
@Data
@Table(name="news")
public class NewsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private int seq;	
	
	@Column(name = "ins_date")
	private Date insDate;
	
	@Column(name = "news")
	private String news;
	
	@Column(name = "url")
	private String url;
	
	private String formattedDate;
}
