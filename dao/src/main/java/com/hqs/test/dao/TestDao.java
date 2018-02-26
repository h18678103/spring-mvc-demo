package com.hqs.test.dao;

import com.hqs.test.dao.entity.Test;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @author huqinsong
 * @date 2018/2/26
 */
@MapperScan
public interface TestDao {

    Test getTest(int id);

}
