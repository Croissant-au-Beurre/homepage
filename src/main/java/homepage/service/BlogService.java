package homepage.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import homepage.dao.BlogDao;
import homepage.entity.BlogEntity;
import homepage.form.BlogForm;

@Service
public class BlogService {

	/** Blog DAO. */
	@Autowired
	private BlogDao blogDao;
	

	/**
	 * blg取得.
	 * @return blog list
	 */
	public List<BlogEntity> selectAllBlog() {		
		return blogDao.selectAllBlog();		
	}
	
	/**
	 * IDでのお知らせ情報の取得.
	 * @param id ID
	 * @return お知らせ情報
	 */
	public BlogForm selectBlogToForm(int seq) {
		
		// IDでお知らせ情報を取得
		BlogForm blogForm = new BlogForm();
		BlogEntity blog = blogDao.selectOne(seq);
		blogForm.setSeq(blog.getSeq());
		blogForm.setTitle(blog.getTitle());
		blogForm.setContent(blog.getContent());
		blogForm.setDate(blog.getDate());
		blogForm.setImgPath(blog.getImgPath());
		return blogForm;
	}

	/**
	 * お知らせ登録・更新.
	 * @param oshiraseForm OshiraseForm
	 * @param request HttpServletRequest
	 * @return id
	 */
	public int regist(BlogForm blogForm, HttpServletRequest request) {
		
		String dt = (LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		BlogEntity blog;
		if(blogForm.getSeq() != 0) {
			blog = blogDao.selectOne(blogForm.getSeq());
			blog.setSeq(blog.getSeq());
		} else {
			blog = new BlogEntity();
			blog.setDate(dt);
		}
		blog.setTitle(blogForm.getTitle());
		blog.setContent(blogForm.getContent());
		blog.setImgPath(blogForm.getImgPath());
		if(blogForm.getSeq() == 0) {
			blogDao.insert(blog);
			String title = blog.getTitle().replace("'", "''");
			String content = blog.getContent().replace("'", "''");
			String date = blog.getDate().replace("'", "''");

			BlogEntity insertBlog = blogDao.selectBlog(title, content, date);
			return insertBlog.getSeq();
		} else {
			blogDao.update(blog);
			return blog.getSeq();
		}

	}

	/**
	 * お知らせ削除.
	 * @param oshiraseForm OshiraseForm
	 * @param request HttpServletRequest
	 * @return id
	 */
	public int delete(BlogForm blogForm, HttpServletRequest request) {

		BlogEntity blog = new BlogEntity();
		blog.setSeq(blogForm.getSeq());
		return blogDao.delete(blog);
	}
}
