package cn.edu.nju.tsip.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.edu.nju.tsip.entity.UploadFile;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IFileService;
import cn.edu.nju.tsip.service.IUserService;

@Controller
public class FileController {
	
	private Validator validator;
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);
	
	private IFileService<UploadFile> fileService;
	
	private IUserService<User> userService;

	public IUserService<User> getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public IFileService<UploadFile> getFileService() {
		return fileService;
	}

	@Autowired
	public void setFileService(IFileService<UploadFile> fileService) {
		this.fileService = fileService;
	}
	
	@RequestMapping(value = "/client/file/save/{prefix}/{postfix}", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> handleFileUpload(@RequestParam("file") MultipartFile file,@PathVariable String prefix,@PathVariable String postfix,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		logger.info("client upload file");
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM");
		String path = session.getAttribute("id")+"/"+dateformat.format(date);
		String fileName = prefix+"."+postfix;
		System.out.println(request.getSession().getServletContext().getRealPath("upload")+"/"+path+"/"+fileName);
		File targetFile = new File(request.getSession().getServletContext().getRealPath("upload")+"/"+path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}else{
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			Map<String, String> failure = Maps.newHashMap();
			failure.put("status", "false");
			failure.put("error", "文件已存在，发生冲突");
			return failure;
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			Map<String, String> failure = Maps.newHashMap();
			failure.put("status", "false");
			failure.put("error", "内部错误");
			return failure;
		}
		UploadFile uploadFile = new UploadFile();
		uploadFile.setCreateDate(date);
		uploadFile.setDescription("无");//客户端不支持
		uploadFile.setLocation("/upload/"+path+"/"+fileName);
		uploadFile.setName(fileName);
		uploadFile.setPublisher(userService.find(User.class, (Integer)session.getAttribute("id")));
		fileService.create(uploadFile);
		return Collections.singletonMap("status", "true");
	}
	
	@RequestMapping(value="/client/file/getAll",method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> getFile(HttpServletResponse response){
		List<UploadFile> files = fileService.getFiles();
		List<Map<String, Object>> fileMaps = Lists.newArrayList();
		for(UploadFile file:files){
			Map<String, Object> afile = Maps.newHashMap(); 
			afile.put("name", file.getName());
			afile.put("createDate", file.getCreateDate());
			afile.put("url", file.getLocation());
			afile.put("description", file.getDescription());
			afile.put("publisheId", file.getPublisher().getId());
			afile.put("publisherName", file.getPublisher().getRealName());
			fileMaps.add(afile);
		}
		return Collections.singletonMap("files", fileMaps);
	}
	
	@RequestMapping(value="/client/file/delete/{id}",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> deleteFile(@PathVariable int id, HttpServletResponse response,HttpSession session){
		UploadFile uploadFile = fileService.find(UploadFile.class, id);
		if((uploadFile != null) && (uploadFile.getPublisher().getId()== (Integer)session.getAttribute("id"))){
			return Collections.singletonMap("status", "true");
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String,String> failure = Maps.newHashMap();
			failure.put("status", "false");
			failure.put("error", "文件不存在");
			return failure;
		}
	}
}