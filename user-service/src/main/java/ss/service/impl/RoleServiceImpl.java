package ss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ss.entity.Role;
import ss.mapper.RoleMapper;
import ss.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
