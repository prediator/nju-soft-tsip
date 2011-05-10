package cn.edu.nju.tsip.web;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;

import cn.edu.nju.tsip.entity.UploadFile;
import cn.edu.nju.tsip.service.IFileService;

@Controller
public class FileController {
	
	private Validator validator;
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);
	
	private IFileService<UploadFile> fileService;

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
	
	@RequestMapping(value = "/client/file/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		logger.info("client upload file");
		System.out.println("======================aaaaaaaaaaaaaa================");
		Date date = new Date();
		String path = request.getSession().getServletContext().getRealPath("upload")+"/"+session.getAttribute("id")+"/"+date.getYear()+"/"+date.getMonth();
		String fileName = file.getOriginalFilename();
		System.out.println(path);
		File targetFile = new File(path, fileName);
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
		uploadFile.setLocation(path+"/"+fileName);
		uploadFile.setName(fileName);
		fileService.create(uploadFile);
		return Collections.singletonMap("status", "true");
	}
}