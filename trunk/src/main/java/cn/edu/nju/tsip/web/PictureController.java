package cn.edu.nju.tsip.web;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		Album album = albumService.delete((Integer)session.getAttribute("id"),(String)param.get("albumName"));
		if(album==null){
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "不存在该相册");
			return failureMessages;
		}else {
			return Collections.singletonMap("status", "true");
		}
	}
	
	@RequestMapping(value="/client/album/rename",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> renameAlbum(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		Album album = albumService.delete((Integer)session.getAttribute("id"),(String)param.get("albumName"));
		if(album==null){
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "不存在该相册");
			return failureMessages;
		}else {
			return Collections.singletonMap("status", "true");
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
