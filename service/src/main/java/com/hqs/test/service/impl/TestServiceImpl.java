package com.hqs.test.service.impl;

import com.hqs.test.dao.TestDao;
import com.hqs.test.service.TestService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author huqinsong
 * @date 2018/2/26
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public int get1() {
        return testDao.get1();
    }
}
