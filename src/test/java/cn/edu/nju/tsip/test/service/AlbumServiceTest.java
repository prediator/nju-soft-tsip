package cn.edu.nju.tsip.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.Album;
import cn.edu.nju.tsip.service.IAlbumService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;

@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class AlbumServiceTest {
	
	private IAlbumService<Album> albumService;

	public IAlbumService<Album> getAlbumService() {
		return albumService;
	}

	@Autowired
	public void setAlbumService(IAlbumService<Album> albumService) {
		this.albumService = albumService;
	}
	
	@Test
	public void getAlbumsTest(){
		List<Album> albums = albumService.getAlbums(97);
		for(Album album : albums){
			System.out.println(album.getName());
		}
	}

}
