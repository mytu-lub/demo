package ss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import ss.convert.DateConverter;
import ss.convert.LocalDateConverter;
import ss.convert.LocalDateTimeConverter;

import javax.annotation.PostConstruct;

/**
 * 日期格式化统一处理
 *
 * @author tjr
 */
@Component
public class ControllerHandler {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @PostConstruct
    public void init() {
        ConfigurableWebBindingInitializer initializer =
                (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService =
                    (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new DateConverter());
            genericConversionService.addConverter(new LocalDateTimeConverter());
            genericConversionService.addConverter(new LocalDateConverter());
        }
    }
}
