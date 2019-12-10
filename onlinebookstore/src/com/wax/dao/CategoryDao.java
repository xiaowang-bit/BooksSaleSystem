package com.wax.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.xxq.model.Category;
import com.xxq.utils.C3P0Util;

public class CategoryDao {
	QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
	public int insert(Category ct) {
		/**
		 * @param 传入一个category对象
		 * @return int值，大于零表示插入成功，小于等于零则插入失败
		 * @author 王澳星
		 */
		int row=0;
		String sql="insert into category(id,name,description) vale(?,?,?)";
		Object[]ob={ct.getId(),ct.getName(),ct.getDescription()};
		try {
			row = qr.update(sql,ob);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	public List<Category> searchById(String id) {
		/**
		 * @param 传入一个category的id
		 * @return category列表
		 * @author 王澳星
		 */
		List<Category> list=new ArrayList<Category>();
		String sql="select * from category";
		try {
			list = qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int update(Category ct) {
		/**
		 * @param 传入一个category对象
		 * @return int值，大于零表示更新成功，小于等于零则更新失败
		 * @author 王澳星
		 */
		int row=0;
		String sql="update category set name=?,description=? where id=?";
		Object[] ob={ct.getName(),ct.getDescription(),ct.getId()};
		try {
			row=qr.update(sql,ob);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	public int delete(String id) {
		/**
		 * @author 王澳星
		 * @param 传入一个category的id
		 * @return int值，大于零表示删除成功，小于等于零则删除失败
		 */
		int row =0;
		String sql="delete from category where id=?";
		try {
			row = qr.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}
}
