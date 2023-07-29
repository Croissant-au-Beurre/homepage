package homepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import homepage.dao.PublicationDao;
import homepage.entity.PublicationEntity;

@Service
public class PublicationService {

	@Autowired
	PublicationDao publicationDao;
	
	/**
	* 按降序获得所有publication
	* @return publicationList
	*/
	public List<PublicationEntity> selectAllPublications() {
		return publicationDao.selectAllPublications();
	}
	
	/**
	* 删除publication
	* @return int
	*/
	public int deletePublicationBySeq(int seq) {
		PublicationEntity publication = publicationDao.selectPublicationBySeq(seq);
		return publicationDao.deletePublication(publication);
	}
	
	/**
	* 插入新的publication
	* @return int
	*/
	public int insertPublication(PublicationEntity publication) {
		return publicationDao.insertPublication(publication);
	}
	
	/**
	* 编辑publication
	* @return int
	*/
	public int updatePublication(PublicationEntity publication) {
		return publicationDao.updatePublication(publication);
	}
}
