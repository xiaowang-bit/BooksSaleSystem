package com.wax.test;



import org.junit.Test;

import com.wax.service.CategoryService;
import com.xxq.model.Category;

public class CategoryServiceTest {

	@Test
	public void testaddCategory() {
		Category ct=new Category("c005", "计算机类", "包含了计算机的所有书籍");
		CategoryService ctservice=new CategoryService();
		ctservice.addCategory(ct);
		System.out.println(ct);
	}
	@Test
	public void testsearchCategory() {
		CategoryService ctservice=new CategoryService();
		ctservice.searchCategory("08158b69-d763-407b-9e42-95964e8958fb");
	}

}
