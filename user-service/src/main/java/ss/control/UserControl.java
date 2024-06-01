package ss.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;
import io.swagger.annotations.Api;
import org.springframework.util.StringUtils;
import ss.bean.Constants;
import ss.bean.UserLoginInfo;
import ss.common.api.ApiResponse;
import ss.common.cache.CacheService;
import ss.common.utils.UUIDUtils;
import ss.entity.Product;
import ss.entity.Role;
import ss.service.ProductService;
import ss.service.RoleService;
import ss.service.UserService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import ss.entity.User;
import ss.exception.BaseException;

import java.util.List;
import java.util.Set;


/**
 * UserController
 *
 * @author tjr
 * @since 2024-04-05
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserControl {
    private UserService userService;
    private RoleService roleService;
    private ProductService productService;

    private CacheService cacheService;


    @GetMapping("/token")
    @ApiOperation(value = "获取ACCESS TOKEN有效时间一小时")
    public ApiResponse token(@RequestParam String userName, @RequestParam String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            throw BaseException.of(2000, "请输入正确参数！");
        }
        User user = userService.getOne(new QueryWrapper<User>().eq(User.USERNAME, userName));
        if (ObjectUtils.isEmpty(user)) {
            throw BaseException.of(2000, "用户不存在！");
        } else if (!password.equals(user.getPassword())) {
            throw BaseException.of(2000, "密码错误！");
        } else {
            UserLoginInfo info = new UserLoginInfo();
            info.setToken(UUIDUtils.get());
            //查找权限
            Role role = roleService.getOne(new QueryWrapper<Role>().eq(Role.NAME, user.getRole()));
            String[] split = role.getUrl().split(",");
            info.setLimits(Set.of(split));
            cacheService.set(Constants.TOKEN_APP_REDIS_KEY_PRE + info.getToken(), info, Constants.TOKENAPPTIME);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setCode(0);
            apiResponse.setData(info.getToken());
            return apiResponse;
        }
    }

    @PostMapping("/addProduct")
    @ApiOperation(value = "添加产品")
    public boolean addProduct(@RequestParam String productName) {
        if (StringUtils.isEmpty(productName)) {
            throw BaseException.of(2000, "请输入正确参数");
        }
        int count = productService.count(new QueryWrapper<Product>().eq(Product.PRODUCT_NAME, productName));
        if (count == 0) {
            Product product = new Product();
            product.setProductName(productName);
            return productService.save(product);
        }
        return true;
    }


    @PostMapping("/updateProduct")
    @ApiOperation(value = "修改产品")
    public boolean updateProduct(@RequestParam Integer id, @RequestParam String newProductName) {
        if (ObjectUtils.isEmpty(id) || StringUtils.isEmpty(newProductName)) {
            throw BaseException.of(2000, "请输入正确参数");
        }
        Product product = productService.getById(id);
        if (ObjectUtils.isEmpty(product)) {
            throw BaseException.of(2000, "该产品不存在！");
        }
        product.setProductName(newProductName);
        return productService.updateById(product);
    }

    @PostMapping("/deleteProduct")
    @ApiOperation(value = "删除产品")
    public boolean deleteProduct(@RequestParam Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            throw BaseException.of(2000, "请输入正确参数");
        }
        if (productService.removeById(id)) {
            return true;
        } else {
            throw BaseException.of(2000, "操作失败！");
        }
    }

    @PostMapping("/getProductList")
    @ApiOperation(value = "获取产品列表")
    public List<Product> getProductList() {
        return productService.list();
    }
}
