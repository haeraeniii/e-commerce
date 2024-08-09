package hhplus.e_commerce.domain.test.repository;

import hhplus.e_commerce.domain.test.entity.RedisTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisTestRepositoryTest {

    @Autowired
    RedisTestRepository redisTestRepository;

    @Test
    @DisplayName("redisBasicTest")
    public void redisBasicTest() {
        //given
        RedisTest redisTest = RedisTest.builder()
                .id(1L)
                .name("정혜련")
                .build();

        redisTestRepository.save(redisTest);
        log.info("redisTest 저장");

        //when
        RedisTest found = redisTestRepository.findById(1L).get();
        log.info("정혜련 찾기");

        //then
        assertEquals(found.getId(), 1L);
        assertEquals(found.getName(), "정혜련");
    }
}