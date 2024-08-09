package hhplus.e_commerce.domain.test.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "redisTest", timeToLive = 300)
public class RedisTest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
}
