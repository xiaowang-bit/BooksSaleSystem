package com.wax.service;

import java.util.ArrayList;
import java.util.List;

import com.wax.dao.CategoryDao;
import com.xxq.model.Category;
import com.xxq.utils.C3P0Util;

public class CategoryService {
	@SuppressWarnings("unused")
	public int addCategory(Category ct) {
		/**
		 * @author 王澳星
		 * @param Category对象
		 * @return int值，大于零表示添加成功，小于等于零则添加失败
		 */
		int row=0;
		CategoryDao cdao=new CategoryDao();
		List<Category> categoryList = cdao.searchById(ct.getId());	
		if (categoryList.size()<=0||categoryList==null) {
			row = cdao.insert(ct);
		}
		return row;
	}
	public List<Category> searchCategory(String id ) {
		/**
		 * @author 王澳星
		 * @param Category的id
		 * @return category列表
		 */
		 List<Category> list=new ArrayList<Category>();
		 CategoryDao cdao=new CategoryDao();
		 list = cdao.searchById(id);
		 return list;
	}
	public List<Category> getAllCategory() {
		/**
		 * @author 王澳星
		 * @param Category的id
		 * @return category列表
		 */
		 List<Category> list=new ArrayList<Category>();
		 CategoryDao cdao=new CategoryDao();
		 list = cdao.searchAll();
		 return list;
	}
	public int getTotalCount() {
		String sql = "select count(1) from Category ";
		return C3P0Util.getTotalCount(sql);
		
}
}
