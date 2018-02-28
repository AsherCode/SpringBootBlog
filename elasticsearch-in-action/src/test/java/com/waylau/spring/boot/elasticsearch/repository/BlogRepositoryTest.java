package com.waylau.spring.boot.elasticsearch.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.waylau.spring.boot.elasticsearch.domain.Blog;


/**
 * BlogRepository 测试类.
 * 
 * @since 1.0.0 2017年3月12日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogRepositoryTest {
	
	@Autowired
    private BlogRepository blogRepository;
	@Before
	public void initRepositoryData(){
		blogRepository.deleteAll();
		blogRepository.save(new Blog("登鹳雀楼","王之涣的登鹳雀楼","白日衣衫尽，黄河入海流。欲穷千里目，更上一层楼"));
		blogRepository.save(new Blog("相思","王维的相思","红豆生南国，春来发几枝。愿君多采撷，此物最相思。"));
		blogRepository.save(new Blog("静夜思","李白的静夜思","床前明月光，疑是地上霜。举头望明月，低头思故乡"));
	}
	@Test
	public void testfindDistinctBlogByTitleContainingOrSummaryContainingOrContentContaining() {

		Pageable pageable = new PageRequest(0, 20);
		Page<Blog> page = blogRepository.findDistinctBlogByTitleContainingOrSummaryContainingOrContentContaining("思", "相思", "相思",pageable);
		assertThat(page.getTotalElements()).isEqualTo(2);
        System.out.println("--------start----------");
        for(Blog blog:page.getContent()){
            System.out.println(blog.toString());
        }
        System.out.println("--------start----------");
    }
}
