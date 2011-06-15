package cn.edu.nju.tsip.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.BBS;
import cn.edu.nju.tsip.service.IBBSService;

@Service("bbsService")
@Transactional
public class BBSServiceImpl<T extends BBS> extends ServiceImpl<T> implements IBBSService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);

	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub

	}

	public T get(int bbsId, int userId) {
		return dao.findUniqueBy("from BBS as bbs where bbs.id and bbs.publisher.id", bbsId,userId);
	}

	public List<T> getBBSs() {
		return dao.list("from BBS as bbs order by bbs.createDate desc");
	}

}
