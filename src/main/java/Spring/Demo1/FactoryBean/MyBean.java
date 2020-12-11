package Spring.Demo1.FactoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author 14417
 */
class MyBean implements FactoryBean<Course> {
    @Override
    public Course getObject() {
        Course course = new Course();
        course.setCname("abc");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
