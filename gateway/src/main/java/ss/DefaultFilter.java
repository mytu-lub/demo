package ss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ss.bean.Constants;
import ss.bean.UserLoginInfo;
import ss.common.api.ApiResponse;
import ss.common.cache.CacheService;
import ss.common.utils.JacksonUtils;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@Component
@Slf4j
public class DefaultFilter implements GlobalFilter, Ordered {
    /**
     * 不需要登录校验的url
     */
    private final static String[] IGNOREURL = {"/user-service/user/token"
    };

    /**
     * swagger地址
     */
    private final static String[] SWAGGERURL = {"/swagger", "/v3/api-docs"};

    static final String PRE = "Bearer ";

    @Autowired
    private CacheService cacheService;

    @Autowired
    private Environment environment;

    private boolean isSwagger(String path) {
        String p = path.substring(path.indexOf("/", 1));
        for (String url : SWAGGERURL) {
            if (p.startsWith(url)) {
                return true;
            }
        }
        return false;
    }

    private boolean isIgnore(String path) {
        for (String url : IGNOREURL) {
            if (url.equals(path)) {
                return true;
            }
        }
        return false;
    }

    private boolean isHaveRole(Set<String> limits, String path) {
        for (String url : limits) {
            if (url.equals(path)) {
                return true;
            }
        }
        return false;
    }

    private static final String START_TIME = "startTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();

        String queryInfo = String.format("Method:{%s} Host:{%s} Path:{%s} Query:{%s}",
                exchange.getRequest().getMethod().name(),
                exchange.getRequest().getURI().getHost(),
                exchange.getRequest().getURI().getPath(),
                exchange.getRequest().getQueryParams());

        log.info(queryInfo);
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());

        String profiles = (environment.getProperty("spring.profiles.active"));

        if (isSwagger(path)) {
            return chain.filter(exchange);
        }

        if (isIgnore(path)) {
            return chain.filter(exchange).then(logExecuteTime(exchange));
        } else {
            //检查token
            String token = request.getHeaders().getFirst("Authorization");

            log.info("token:{}", token);

            if (ObjectUtils.isEmpty(token)) {
                log.error("==============token为空");
                return write(exchange.getResponse(), new ApiResponse(Constants.SESSION_TIME_OUT, "登录信息过期，请重新登录"));
            }

            token = token.substring(PRE.length());

            if (!ObjectUtils.isEmpty(token) && cacheService.hasKey(Constants.TOKEN_APP_REDIS_KEY_PRE + token)) {
                //判断权限
                UserLoginInfo info = cacheService.get(Constants.TOKEN_APP_REDIS_KEY_PRE + token, UserLoginInfo.class);
                if (!isHaveRole(info.getLimits(),path)) {
                    return write(exchange.getResponse(),
                            new ApiResponse(Constants.SESSION_TIME_OUT, "该用户暂无权限！"));
                }
                //把用户id传到服务
                ServerHttpRequest host = exchange.getRequest().mutate().header(Constants.USERAPPRHEADERUSERID,
                        "Bearer ").header("token", token).build();
                return chain.filter(exchange.mutate().request(host).build()).then(logExecuteTime(exchange));

            } else {
                return write(exchange.getResponse(),
                        new ApiResponse(Constants.SESSION_TIME_OUT, "登录信息过期，请重新登录"));

            }
        }
    }

    private Mono logExecuteTime(ServerWebExchange exchange) {
        return Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                Long executeTime = (System.currentTimeMillis() - startTime);
                log.info("URI:{},Execute:{}ms", exchange.getRequest().getURI().getRawPath(), executeTime);
            }
        });
    }

    private Mono<Void> write(ServerHttpResponse response, Object object) {
        response.getHeaders().set("Content-Type", "application/json; charset=utf-8");
        DataBuffer buffer = response.bufferFactory().wrap(JacksonUtils.toJson(object).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
