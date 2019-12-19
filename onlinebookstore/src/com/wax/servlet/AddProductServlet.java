package com.wax.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.wax.service.BookService;
import com.xxq.model.Book;
import com.xxq.utils.CreateOderId;





@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		boolean ismultipart = ServletFileUpload.isMultipartContent(request);
		if(ismultipart)//判断上传表单中是否有mutipart属性
		{
			DiskFileItemFactory factory=new DiskFileItemFactory();
            //设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			factory.setSizeThreshold(1024*100);
            // 2、创建一个文件上传解析器
			ServletFileUpload fileUpload=new ServletFileUpload(factory);
			fileUpload.setHeaderEncoding("UTF-8");
			fileUpload.setFileSizeMax(1024*1024*100);
			try {
				//通过parseRequest解析form表单中的所有请求字段，并且保存到items中
				List<FileItem> itme = fileUpload.parseRequest(request);
				//遍历item中的数据（name,pwd,file）
				Iterator<FileItem> it=itme.iterator();
				String category=null;
				Book book=new Book();
				book.setId(CreateOderId.getOrderCode(System.currentTimeMillis()));
				while(it.hasNext()) {
					FileItem fileItem = it.next();
					String fieldName= fileItem.getFieldName();
					if(fileItem.isFormField()) {
						if("categoryname".equals(fieldName)) {
							category= fileItem.getString("utf-8");
							book.setCategory_id(category);
						}
						if("bookname".equals(fieldName)) {
							book.setBookname(fileItem.getString("utf-8"));
						}
						if("price".equals(fieldName)) {
							book.setPrice(Float.parseFloat(fileItem.getString("utf-8")));
						}
						if("author".equals(fieldName)) {
							book.setAuthor(fileItem.getString("utf-8"));
						}
						if("description".equals(fieldName)) {
							book.setDescription(fileItem.getString("utf-8"));
						}
					}
					
				}
				Iterator<FileItem> it2=itme.iterator();
				while(it2.hasNext()) {
					FileItem fileItem = it2.next();
					String fieldName= fileItem.getFieldName();
					if(fileItem.isFormField()) {
						continue;
					}else {
						String filename=fileItem.getName();
						if(filename==null || filename.trim().equals("")){
							continue;
						}
						
						//处理获取到的上传文件的文件名的路径部分只保留文件名部分
						filename = filename.substring(filename.lastIndexOf("\\")+1);
						//得到上传文件的扩展名
						String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
						//如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
						String savePath=this.getServletContext().getRealPath("/assets/img/product/");
						//得到文件保存的名称
						String realSavePath = makePath(category, savePath);
						//文件全路径
						String filePath = realSavePath + "\\" + filename;
						
						File file=new File(realSavePath,filename);
						fileItem.write(file);
						//设置图片存储路径
						book.setImageName(filePath);
						//增加book
						BookService dao=new BookService();
						int row = dao.addBook(book);
						if(row>0){	
							Object[] options = { "确定" }; 
				        	JOptionPane.showOptionDialog(null, "添加成功！", "提示", 
				        	JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
				        	null, options, options[0]); 
							request.getRequestDispatcher("ManageShop.jsp");
						}else{
							response.sendRedirect("fail.jsp");
						}
						
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

	 
    private String makePath(String category,String path){
        
        //构造新的保存目录
        String dir = path +"\\"+category;  //upload\stu_id\
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }

}
