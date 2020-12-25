package Spring.AOP1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author kikukk
 */

@Configuration
@ComponentScan(basePackages = {"Spring.AOP1"})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ConfigAop {
}
