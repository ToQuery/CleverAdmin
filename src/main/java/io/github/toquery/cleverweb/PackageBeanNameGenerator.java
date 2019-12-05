package io.github.toquery.cleverweb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

import java.beans.Introspector;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class PackageBeanNameGenerator extends AnnotationBeanNameGenerator {

  @Override
  public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
    String beanClassName = Introspector.decapitalize(definition.getBeanClassName());
    if (StringUtils.startsWith(beanClassName, getPackageNamePrefix())) {
      log.info("CleverWeb Package Bean Name Generator : " + beanClassName);
      return beanClassName;
    }
    return super.generateBeanName(definition, registry);
  }

  private String getPackageNamePrefix() {
    String separator = ".";
    String[] splits = StringUtils.split(this.getClass().getPackage().getName(), separator);
    String prefix = Arrays.stream(splits).limit(3).collect(Collectors.joining(separator));
    return prefix;
  }
}
