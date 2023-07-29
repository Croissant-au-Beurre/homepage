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

import homepage.entity.PublicationEntity;
import homepage.entity.ResearchEntity;

@Repository
@ConfigAutowireable
@Dao
public interface ResearchDao {

	/**
	* 获得最新的研究兴趣
	* @return Research
	*/
	default ResearchEntity selectResearch() {

	Config config = Config.get(this);

	SelectBuilder builder = SelectBuilder.newInstance(config);
	builder.sql("SELECT");
	builder.sql("	*");
	builder.sql("FROM");
	builder.sql("	research");
	builder.sql("ORDER BY");
	builder.sql("	seq");
	builder.sql("	DESC");
	builder.sql("	LIMIT");
	builder.sql("	1");


	return builder.getEntitySingleResult(ResearchEntity.class);
	}
	
	/**
	* 按seq获得研究兴趣
	* @return Research
	*/
	default ResearchEntity selectResearchBySeq(int seq) {

	Config config = Config.get(this);

	SelectBuilder builder = SelectBuilder.newInstance(config);
	builder.sql("SELECT");
	builder.sql("	*");
	builder.sql("FROM");
	builder.sql("	research");
	builder.sql("WHERE");
	builder.sql("	seq");
	builder.sql("    = '" + seq + "'");

	return builder.getEntitySingleResult(ResearchEntity.class);
	}
	
	/**
	* 删除研究兴趣
	* @return int
	*/
	@Delete
	int deleteResearch(ResearchEntity research);
	
	/**
	* 插入新的研究兴趣
	* @return int
	*/
	@Insert(include ={"content"})
	int insertResearch(ResearchEntity research);
	
	/**
	* 编辑研究兴趣
	* @return int
	*/
	@Update(include = {"seq","content"})
	public int updateResearch(ResearchEntity research);
}
