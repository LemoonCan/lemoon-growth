package cherry.service.beanconfigure;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author lee
 * @since 8/31/21
 */
public class BeanFactoryDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        BeanFactory container = bindViaCode(beanRegistry);
        FXNewsProvider newsProviders = (FXNewsProvider)container.getBean("djNewsProvider");
        newsProviders.getAndPersistNews();
    }

    public static BeanFactory bindViaCode(BeanDefinitionRegistry registry){
        AbstractBeanDefinition newsProvider = new RootBeanDefinition(FXNewsProvider.class);
        AbstractBeanDefinition newsListener = new RootBeanDefinition(DowJonesNewsListener.class);
        AbstractBeanDefinition newsPersistener = new RootBeanDefinition(DowJonesNewsPersistener.class);
        registry.registerBeanDefinition("djNewsProvider",newsProvider);
        registry.registerBeanDefinition("djNewsListener",newsListener);
        registry.registerBeanDefinition("djNewsPersistener",newsPersistener);

        ConstructorArgumentValues argValues = new ConstructorArgumentValues();
        argValues.addIndexedArgumentValue(0,newsListener);
        argValues.addIndexedArgumentValue(1,newsPersistener);
        newsProvider.setConstructorArgumentValues(argValues);

//        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue(new PropertyValue("newsListener",newsListener));
//        propertyValues.addPropertyValue(new PropertyValue("newsPersistener",newsPersistener));
//        newsProvider.setPropertyValues(propertyValues);
        return (BeanFactory)registry;
    }

}
