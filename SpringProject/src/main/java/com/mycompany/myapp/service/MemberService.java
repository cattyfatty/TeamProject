package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.mycompany.myapp.dao.*;
import com.mycompany.myapp.dto.*;

@Component
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	
	public void signUp(Members member) {
		memberDao.insert(member);
	}
	public void SelectById(String id){
		memberDao.selectByPk(id);
	}
}
