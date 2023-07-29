package homepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import homepage.dao.AboutMeDao;
import homepage.entity.NewsEntity;

@Service
public class AboutMeService {
	
	@Autowired
	AboutMeDao aboutMeDao;
	
	/**
	* 按降序获得所有news
	* @return newsList
	*/
	public List<NewsEntity> selectAllNews() {
		return aboutMeDao.selectAllNews();
	}
	
	/**
	* 删除news
	* @return newsList
	*/
	public int deleteNewsBySeq(int seq) {
		NewsEntity news = aboutMeDao.selectNewsBySeq(seq);
		return aboutMeDao.deleteNews(news);
	}
	
	/**
	* 插入新的news
	* @return int
	*/
	public int insertNews(NewsEntity news) {
		return aboutMeDao.insertNews(news);
	}
	
	/**
	* 编辑news
	* @return int
	*/
	public int updateNews(NewsEntity news) {
		return aboutMeDao.updateNews(news);
	}
}
