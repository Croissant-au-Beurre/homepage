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
@Table(name="publications")
public class PublicationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private int seq;	
	
	@Column(name = "auteurs")
	private String auteurs;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "doi")
	private String doi;
	
	@Column(name = "url")
	private String url;
}
