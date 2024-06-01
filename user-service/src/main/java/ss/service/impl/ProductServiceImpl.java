package ss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ss.entity.Product;
import ss.mapper.ProductMapper;
import ss.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
