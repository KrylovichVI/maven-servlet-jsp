package com.krylovichVI.dao.mapper;

import com.krylovichVI.dao.config.DaoConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfig.class})
@Transactional
public class AuthUserMapperTest {

}
