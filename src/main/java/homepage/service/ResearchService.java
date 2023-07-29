package homepage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import homepage.dao.ResearchDao;
import homepage.entity.ResearchEntity;

@Service
public class ResearchService {

	@Autowired
	ResearchDao researchDao;
	
	/**
	* 按降序获得所有research
	* @return research
	*/
	public ResearchEntity selectResearch() {
		return researchDao.selectResearch();
	}
	
	/**
	* 删除news
	* @return int
	*/
	public int deleteResearchBySeq(int seq) {
		ResearchEntity research = researchDao.selectResearchBySeq(seq);
		return researchDao.deleteResearch(research);
	}
	
	/**
	* 插入新的news
	* @return int
	*/
	public int insertResearch(ResearchEntity research) {
		return researchDao.insertResearch(research);
	}
	
	/**
	* 编辑news
	* @return int
	*/
	public int updateResearch(ResearchEntity research) {
		return researchDao.updateResearch(research);
	}	
}
