package ss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ss.entity.User;
import ss.mapper.UserMapper;
import ss.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tjr
 * @since 2024-04-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
