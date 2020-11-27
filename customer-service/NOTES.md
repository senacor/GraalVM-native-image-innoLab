- works with `spring-boot-starter-parent:2.4.0-RC1`, but not with version `2.4.0`:
```
Error: Classes that should be initialized at run time got initialized during image building:
 org.springframework.boot.logging.java.JavaLoggingSystem$Factory was unintentionally initialized at build time. org.springframework.boot.logging.java.JavaLoggingSystem$Factory has been initialized without the native-image initialization instrumentation and the stack trace can't be tracked. Try marking this class for build-time initialization with --initialize-at-build-time=org.springframework.boot.logging.java.JavaLoggingSystem$Factory
org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory was unintentionally initialized at build time. org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory has been initialized without the native-image initialization instrumentation and the stack trace can't be tracked. Try marking this class for build-time initialization with --initialize-at-build-time=org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory

com.oracle.svm.core.util.UserError$UserException: Classes that should be initialized at run time got initialized during image building:
 org.springframework.boot.logging.java.JavaLoggingSystem$Factory was unintentionally initialized at build time. org.springframework.boot.logging.java.JavaLoggingSystem$Factory has been initialized without the native-image initialization instrumentation and the stack trace can't be tracked. Try marking this class for build-time initialization with --initialize-at-build-time=org.springframework.boot.logging.java.JavaLoggingSystem$Factory
org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory was unintentionally initialized at build time. org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory has been initialized without the native-image initialization instrumentation and the stack trace can't be tracked. Try marking this class for build-time initialization with --initialize-at-build-time=org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory

	at com.oracle.svm.core.util.UserError.abort(UserError.java:68)
	at com.oracle.svm.hosted.classinitialization.ConfigurableClassInitialization.checkDelayedInitialization(ConfigurableClassInitialization.java:526)
	at com.oracle.svm.hosted.classinitialization.ClassInitializationFeature.duringAnalysis(ClassInitializationFeature.java:227)
	at com.oracle.svm.hosted.NativeImageGenerator.lambda$runPointsToAnalysis$8(NativeImageGenerator.java:732)
	at com.oracle.svm.hosted.FeatureHandler.forEachFeature(FeatureHandler.java:70)
	at com.oracle.svm.hosted.NativeImageGenerator.runPointsToAnalysis(NativeImageGenerator.java:732)
	at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:555)
	at com.oracle.svm.hosted.NativeImageGenerator.lambda$run$0(NativeImageGenerator.java:468)
	at java.base/java.util.concurrent.ForkJoinTask$AdaptedRunnableAction.exec(ForkJoinTask.java:1407)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:290)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1020)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1656)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1594)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:183)
Error: Image build request failed with exit status 1
```

- `spring-graalvm-native:0.8.2` plugin requires at least spring boot `2.4.0-M2`:
```
Fatal error:org.springframework.graalvm.support.SpringFeature$VersionCheckException: Spring GraalVM Native requires Spring Boot 2.4.0-M2 or above
```

=> you really have to take care of which version of spring boot, graalvm and spring-graalvm-native work together!

- after generating a default controller with openapi the following runtime exception occured:
```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'defaultValidator' defined in class path resource [org/springframework/boot/autoconfigure/validation/ValidationAutoConfiguration.class]: Invocation of init method failed; nested exception is java.lang.NullPointerException
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1788) ~[na:na]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:609) ~[na:na]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:531) ~[na:na]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335) ~[na:na]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[na:na]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333) ~[na:na]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208) ~[na:na]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:944) ~[na:na]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:925) ~[na:na]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:588) ~[na:na]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:144) ~[na:na]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:767) ~[na:na]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:759) ~[na:na]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:426) ~[na:na]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:326) ~[na:na]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1309) ~[na:na]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1298) ~[na:na]
	at de.senacor.innolab.graalvm.customer.CustomerServiceApplication.main(CustomerServiceApplication.java:10) ~[customer-service:na]
Caused by: java.lang.NullPointerException: null
	at sun.reflect.annotation.TypeAnnotationParser.mapTypeAnnotations(TypeAnnotationParser.java:378) ~[na:na]
	at sun.reflect.annotation.AnnotatedTypeFactory$AnnotatedTypeBaseImpl.<init>(AnnotatedTypeFactory.java:140) ~[na:na]
	at sun.reflect.annotation.AnnotatedTypeFactory$AnnotatedWildcardTypeImpl.<init>(AnnotatedTypeFactory.java:326) ~[na:na]
	at sun.reflect.annotation.AnnotatedTypeFactory.buildAnnotatedType(AnnotatedTypeFactory.java:82) ~[na:na]
	at sun.reflect.annotation.AnnotatedTypeFactory$AnnotatedParameterizedTypeImpl.getAnnotatedActualTypeArguments(AnnotatedTypeFactory.java:291) ~[na:na]
	at org.hibernate.validator.internal.engine.valueextraction.ValueExtractorDescriptor.getExtractedTypeParameter(ValueExtractorDescriptor.java:76) ~[na:na]
	at org.hibernate.validator.internal.engine.valueextraction.ValueExtractorDescriptor.<init>(ValueExtractorDescriptor.java:49) ~[na:na]
	at org.hibernate.validator.internal.engine.AbstractConfigurationImpl.loadValueExtractorsFromServiceLoader(AbstractConfigurationImpl.java:647) ~[na:na]
	at org.hibernate.validator.internal.engine.AbstractConfigurationImpl.buildValidatorFactory(AbstractConfigurationImpl.java:407) ~[na:na]
	at org.springframework.validation.beanvalidation.LocalValidatorFactoryBean.afterPropertiesSet(LocalValidatorFactoryBean.java:310) ~[customer-service:na]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1847) ~[na:na]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1784) ~[na:na]
	... 17 common frames omitted
```