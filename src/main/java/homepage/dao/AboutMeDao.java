package homepage.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;
import org.springframework.stereotype.Repository;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;

import homepage.entity.NewsEntity;


@Repository
@ConfigAutowireable
@Dao
public interface AboutMeDao {

	/**
	* 按降序获得所有news
	* @return newsList
	*/
	default List<NewsEntity> selectAllNews() {

	Config config = Config.get(this);

	SelectBuilder builder = SelectBuilder.newInstance(config);
	builder.sql("SELECT");
	builder.sql("	*");
	builder.sql("FROM");
	builder.sql("	news");
	builder.sql("ORDER BY");
	builder.sql("	ins_date");
	builder.sql("	DESC");
	builder.sql("	LIMIT");
	builder.sql("	6");

	return builder.getEntityResultList(NewsEntity.class);
	}
	
	/**
	* 按seq获得news
	* @return newsList
	*/
	default NewsEntity selectNewsBySeq(int seq) {

	Config config = Config.get(this);

	SelectBuilder builder = SelectBuilder.newInstance(config);
	builder.sql("SELECT");
	builder.sql("	*");
	builder.sql("FROM");
	builder.sql("	news");
	builder.sql("WHERE");
	builder.sql("	seq");
	builder.sql("    = '" + seq + "'");

	return builder.getEntitySingleResult(NewsEntity.class);
	}
	
	/**
	* 按删除news
	* @return newsList
	*/
	@Delete
	int deleteNews(NewsEntity news);
	
	/**
	* 插入新的news
	* @return int
	*/
	@Insert(include ={"insDate","news","url"})
	int insertNews(NewsEntity news);
	
	/**
	* 编辑news
	* @return int
	*/
	@Update(include = {"seq","insDate","news","url"})
	public int updateNews(NewsEntity news);
	
}
