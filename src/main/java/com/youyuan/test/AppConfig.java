package com.youyuan.test;

import com.youyuan.dao.ProductDao;
import com.youyuan.dao.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyu
 * @version 1.0
 * @description 配置类   bean通过配置类的方式注册到IOC容器
 *
 * 1、包扫描(@ComponentScan)+注解组件(@Component、@Service、@Controller、@Repository)
 * 2、@Bean注解  默认id的值是方法名,可以通过@Bean("")修改
 * 3、@Import注解 往ioc容器导入组件,在配置类上用@Import注解导入
 *      ①@Import快速导入组件到ioc容器  参数就是要注册组件的class  如:@Import({Color.class,Red.class})
 *      ②@Import根据条件导入组件到ioc容器  用法:定义一个类实现ImportSelector接口,重写方法，方法返回值是要导入ioc容器的类的全路径名数组,然后配置类上的@Import注解引入自定义的那个类,如 @Import({Color.class,Red.class,MyImportSelector.class})
 *      ③@Import   ImportBeanDefinitionRegist手动注册组件到ioc容器     用法:定义一个类实现ImportBeanDefinitionRegist接口,如@Import({Color.class,Red.class,MyImportSelector.class,MyImportBeanDefinitionRegist})
 * 4、通过Spring的FactoryBean工厂方式将组件注册到ioc容器
 *       用法:
 *          1)自定义一个类实现FactoryBean接口,重写getObject方法，在里面new一个要注册的到ioc容器的组件对象
 *          2)在配置类通过@Bean的方式将自定义的类注册到ioc容器中，实际注册到ioc容器的组件是自定义类中getObject方法new的组件
 *
 * @date 2019/7/3 19:00
 */
@Configuration
@ComponentScan("com.youyuan")
public class AppConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao=applicationContext.getBean(UserDao.class);
        System.out.println(userDao);

        AppConfig appConfig=applicationContext.getBean(AppConfig.class);

        System.out.println(appConfig);

        ProductDao productDao=applicationContext.getBean(ProductDao.class);
        System.out.println(productDao);

    }

    @Bean
    public UserDao userDao(){
        productDao();
        return new UserDao();
    }

    @Bean
    public ProductDao productDao(){
        return new ProductDao();
    }

}
