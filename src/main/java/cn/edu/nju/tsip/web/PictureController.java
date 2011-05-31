package cn.edu.nju.tsip.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.edu.nju.tsip.entity.Album;
import cn.edu.nju.tsip.entity.Picture;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IAlbumService;
import cn.edu.nju.tsip.service.IPictureService;
import cn.edu.nju.tsip.service.IUserService;
import cn.edu.nju.tsip.service.ServiceException;

@Controller
public class PictureController {
	
	private Validator validator;
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);
	
	private IPictureService<Picture> pictureService;
	
	private IAlbumService<Album> albumService;
	
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

	public IPictureService<Picture> getPictureService() {
		return pictureService;
	}

	@Autowired
	public void setPictureService(IPictureService<Picture> pictureService) {
		this.pictureService = pictureService;
	}

	public IAlbumService<Album> getAlbumService() {
		return albumService;
	}

	@Autowired
	public void setAlbumService(IAlbumService<Album> albumService) {
		this.albumService = albumService;
	}
	
	@RequestMapping(value="/client/album/add",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> addAlbum(@RequestBody Map<String,String> param, HttpServletResponse response,HttpSession session){
		logger.info("client add album");
		Album album = new Album();
		album.setCreateDate(new Date());
		album.setName(param.get("name"));
		album.setOwner(userService.find(User.class, (Integer)session.getAttribute("id")));
		albumService.create(album);
		return Collections.singletonMap("status", "true");
	}
	
	@RequestMapping(value="/client/album/share",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> shareAlbum(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		Album album = new Album();
		album.setCreateDate(new Date());
		album.setName((String)param.get("shareReason"));
		album.setOwner(userService.find(User.class, (Integer) session.getAttribute("id")));
		album.setShareAlbum(albumService.find(Album.class, (Integer) param.get("id")));
		albumService.create(album);
		return Collections.singletonMap("status", "true");//====================problem===============
		//相册分享时可能会与你的相册名重复
	}
	
	@RequestMapping(value="/client/album/delete",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> deleteAlbum(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		Album album = albumService.get((Integer)session.getAttribute("id"),(String)param.get("albumName"));
		if(album==null){
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "不存在该相册");
			return failureMessages;
		}else {
			albumService.delete(album);
			return Collections.singletonMap("status", "true");
		}
	}
	
	@RequestMapping(value="/client/album/rename",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> renameAlbum(@RequestBody Map<String,String> param, HttpServletResponse response,HttpSession session){
		Album album = albumService.get((Integer)session.getAttribute("id"),(String)param.get("oldName"));
		if(album==null){
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "不存在该相册");
			return failureMessages;
		}else {
			if(albumService.get((Integer)session.getAttribute("id"),(String)param.get("newName"))==null){
				album.setName(param.get("newName"));
				return Collections.singletonMap("status", "true");
			}else{
				Map<String, String> failureMessages = new HashMap<String, String>();
				failureMessages.put("status", "false");
				failureMessages.put("error", "相册名重复");
				return failureMessages;
			}
			
		}
	}
	
	@RequestMapping(value = "/client/picture/upload/{albumName}/{descrip}", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> pictureUpload(@RequestParam("file") MultipartFile file,@PathVariable String albumName,@PathVariable String descrip,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		logger.info("client upload picture");
		Date date = new Date();
		String fileName = session.getAttribute("id")+session.toString()+date.getTime();
		File targetFile = new File(request.getSession().getServletContext().getRealPath("images"), fileName);
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
		Picture picture = new Picture();
		picture.setCreateDate(date);
		picture.setDescritpion(descrip);
		picture.setName(fileName);
		pictureService.create(picture);
		Album album = albumService.get((Integer) session.getAttribute("id"), albumName);
		if(album==null){
			Map<String, String> failure = Maps.newHashMap();
			failure.put("status", "false");
			failure.put("error", "不存在该相册");
			return failure;
		}else{
			album.getPictures().add(picture);
			albumService.update(album);
			return Collections.singletonMap("status", "true");
		}
		
	}
	
	@RequestMapping(value="/client/album/getMy",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getMyAlbums(@RequestBody Map<String,String> param, HttpServletResponse response,HttpSession session){
		List<Album> albums = albumService.getAlbums((Integer) session.getAttribute("id"));
		List<Map<String, Object>> contents = Lists.newArrayList();
		for(Album album:albums){
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("id", album.getId());
			tempResult.put("name", album.getName());
			tempResult.put("createDate", album.getCreateDate().toString());
			Picture cover = albumService.getCover(album.getId());
			if(cover==null){
				tempResult.put("cover","nopicture.jpg");
			}else{
				tempResult.put("cover",cover.getName());
			}
		}
		return Collections.singletonMap("albums", contents);
		
	}
	
	@RequestMapping(value="/client/album/get",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getAlbums(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		List<Album> albums = albumService.getAlbums((Integer) param.get("userId"));
		List<Map<String, Object>> contents = Lists.newArrayList();
		for(Album album:albums){
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("id", album.getId());
			tempResult.put("name", album.getName());
			tempResult.put("createDate", album.getCreateDate().toString());
			Picture cover = albumService.getCover(album.getId());
			if(cover==null){
				tempResult.put("cover","nopicture.jpg");
			}else{
				tempResult.put("cover",cover.getName());
			}
		}
		return Collections.singletonMap("albums", contents);
	}
	
	@RequestMapping(value="/client/album/detail",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getAlbumDetail(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		Album album = albumService.find(Album.class, (Integer) param.get("id"));
		if(album==null){
			Map<String, String> failure = Maps.newHashMap();
			failure.put("status", "false");
			failure.put("error", "不存在该相册");
			return failure;
		}else{
			Map<String, Object> result = Maps.newHashMap();
			
			return result;
		}
	}
	
	@ExceptionHandler(ServiceException.class)
	public @ResponseBody Map<String, String> handleServiceException(ServiceException exception){
		Map<String, String> failureMessages = new HashMap<String, String>();
		failureMessages.put("status", "false");
		failureMessages.put("error", exception.getMessage());
		return failureMessages;
	}
	
}
