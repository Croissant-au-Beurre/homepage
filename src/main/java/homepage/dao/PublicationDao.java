package homepage.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;
import org.springframework.stereotype.Repository;

import homepage.entity.NewsEntity;
import homepage.entity.PublicationEntity;

@Repository
@ConfigAutowireable
@Dao
public interface PublicationDao {

	/**
	* 获得所有出版成绩
	* @return publicationList
	*/
	default List<PublicationEntity> selectAllPublications() {

	Config config = Config.get(this);

	SelectBuilder builder = SelectBuilder.newInstance(config);
	builder.sql("SELECT");
	builder.sql("	*");
	builder.sql("FROM");
	builder.sql("	publications");
	builder.sql("ORDER BY");
	builder.sql("	seq");


	return builder.getEntityResultList(PublicationEntity.class);
	}
	
	/**
	* 按seq获得出版成绩
	* @return publication
	*/
	default PublicationEntity selectPublicationBySeq(int seq) {

	Config config = Config.get(this);

	SelectBuilder builder = SelectBuilder.newInstance(config);
	builder.sql("SELECT");
	builder.sql("	*");
	builder.sql("FROM");
	builder.sql("	publications");
	builder.sql("WHERE");
	builder.sql("	seq");
	builder.sql("    = '" + seq + "'");

	return builder.getEntitySingleResult(PublicationEntity.class);
	}
	
	/**
	* 删除出版成绩
	* @return int
	*/
	@Delete
	int deletePublication(PublicationEntity publication);
	
	/**
	* 插入新的news
	* @return int
	*/
	@Insert(include ={"auteurs","title","doi","url"})
	int insertPublication(PublicationEntity publication);
	
	/**
	* 编辑news
	* @return int
	*/
	@Update(include = {"seq","auteurs","title","doi","url"})
	public int updatePublication(PublicationEntity publication);
}
