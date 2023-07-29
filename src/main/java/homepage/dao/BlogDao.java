package homepage.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;

import homepage.entity.BlogEntity;

@ConfigAutowireable
@Dao
public interface BlogDao {

	/**
	 * お知らせテーブル登録
	 * @param entity エンティティ
	 * @return 件数
	 */
	@Insert
	int insert(BlogEntity blog);
	
	/**
	 * お知らせテーブル更新
	 * @param entity エンティティ
	 * @return 件数
	 */
	@Update(include = {"title","content","date","imgPath"})
	int update(BlogEntity blog);
	
	/**
	 * お知らせテーブル削除
	 * @param entity エンティティー
	 * @return 件数
	 */
	@Delete
	int delete(BlogEntity blog);

	/**
	 * 以seq取得blog
	 * 
	 * @param seq
	 * @return blog
	 */
	default BlogEntity selectOne(int seq) {

		Config config = Config.get(this);

		SelectBuilder builder = SelectBuilder.newInstance(config);
		builder.sql("SELECT *");
		builder.sql(" FROM  blog ");
		builder.sql(" WHERE seq = " + seq);
		BlogEntity dto = builder.getEntitySingleResult(BlogEntity.class);
		return dto;
	}
	
	default BlogEntity selectBlog(String title, String content, String date) {
		Config config = Config.get(this);

		SelectBuilder builder = SelectBuilder.newInstance(config);
		builder.sql("SELECT *");
		builder.sql(" FROM  blog ");
		builder.sql(" WHERE title = '" + title + "'");
		builder.sql(" AND content = '" + content + "'");
		builder.sql(" AND date = '" + date + "'");
		BlogEntity dto = builder.getEntitySingleResult(BlogEntity.class);
		return dto;
	}
	
	/**
	 * 取得blog
	 * 
	 * @param seq
	 * @return blog list
	 */
	default List<BlogEntity> selectAllBlog() {

		Config config = Config.get(this);

		SelectBuilder builder = SelectBuilder.newInstance(config);
		builder.sql("SELECT *");
		builder.sql(" FROM  blog ");
		builder.sql(" ORDER BY seq ");
		return builder.getEntityResultList(BlogEntity.class);		
	}
}
