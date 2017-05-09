package com.zcckj.storeshow;

import com.zcckj.storeshow.dto.ResponseDto;
import com.zcckj.storeshow.dto.UserDto;
import com.zcckj.storeshow.entity.UserEntity;
import com.zcckj.storeshow.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.zcckj.storeshow.StoreShowServiceApplication.class)
public class DemoServiceApplicationTests {

	@Autowired
    UserService userService;

	@Test
	public void contextLoads() {
		UserEntity user = new UserEntity();
		//user.setPage(2);
		//user.setPageSize(5);
        ResponseDto<List<UserDto>> list = userService.getAll();
		System.out.println(list.toString());
	}

}*/
