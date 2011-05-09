package cn.edu.nju.tsip.web;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.nju.tsip.entity.UploadFile;
import cn.edu.nju.tsip.service.IFileService;

@Controller
public class FileController {
	
	private Validator validator;
	
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
	
	@RequestMapping(value = "/client/file/upload", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> handleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}else{
			
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.singletonMap("status", "true");
	}

}
