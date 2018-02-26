package com.hqs.test.dao.impl;

import com.hqs.test.dao.TestDao;
import org.springframework.stereotype.Repository;

/**
 * @author huqinsong
 * @date 2018/2/26
 */
@Repository
public class TestDaoImpl implements TestDao {
    @Override
    public int get1() {
        return 100;
    }
}
