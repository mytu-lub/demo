package ss.common.cache;

import java.util.List;

/**
 * @author Administrator
 */
public interface CacheService {

    public void set(String key, Object value, Long liveSeconds);

    public void set(String key, Object value);

    public void setLeftPush(String key, Object value, Long liveSeconds);

    public <T> T get(String key, Class<T> cl);

    public <T> List<T> getList(String key, Class<T> cl);

    public void delete(String key);

    public Long getExpire(String key);

    public Boolean expire(String key, Long liveSeconds);

    public Boolean hasKey(String key);

    public Long increment(String key, Long value);

    public Long increment(String key);

    public void removePattern(String pattern);

}
