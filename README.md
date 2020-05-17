# Spring5_Demo
> Spring简介  
> Spring是一个分层的一站式轻量级开源框架  
1. 轻量级：指的式Spring框架的非入侵性，也就是应用中的对象不必依赖Spring的API
2. 分层：每一个都提供了不同的解决技术
    * WEB层：SpringMVC
    * 业务层：Spring的IoC
    * 持久层：Spring的JDBCTemplate（ORM模块用于整合其他持久层框架）
3. Spring核心又两部分：
    * IoC：控制反转
    * AOP：面向切面编程
4. Spring的优点：
    * 方便解耦，简化开发
    * AOP编程的支持
    * 声明式事务的支持
    * 方便集成各种优秀的框架

> ApplicationContext, Spring容器

> 依赖注入  
> 所谓依赖注入，其实就是一个bean实例引用到另一个bean实例时Spring容器帮助我们创建依赖bean实例并注入到另一个bean中  
1. Spring支持的注入方式有：
    * 属性注入
    * 构造器注入
    * 工厂方法注入

2. 属性注入 ---- setter注入
    * 对象注入使用`<property>`的ref属性
    * 普通注入使用`<property>`的value属性
3. 构造器注入
    * 使用`<constructor-arg>`
    * index指定参数的顺序
    * 循环依赖(构造器注入如果存在循环依赖，spring会左右为难)
        * 可以使用setter注入解决(好奇为啥setter注入可以？？)
        * 但是最好不要出现循环依赖

4. 注入特殊对象
    * Properties对象
        * `<props><prop key value/></props>`
    * list
        * `<list><value></value></list>`
    * map
        * `<map><entry key value/></map>`
    * set
        * `<set><value></value></set>`
    * 使用utility scheme
    * 使用p命名空间简化对bean的配置

> Spring的自动装配  
> 前面都是手动注入，Spring提供自动装配功能（autowiring）  
> (推荐写明，自动装配怕出错，xml里面自动装配显得有点多余)
1. Spring自动装配的三种模式（autowire --- bean标签属性）
    * byType
    * byName
    * constructor
2. byType （Spring容器基于反射查看bean定义的类，然后找到类型相同的bean注入）
    * 需要借助setter注入（必须存在set方法）
    * IoC容器中存在多个相同类型不同名的实例bean时，会注入失败
3. byName （同名， 也就是属性名同bean的id）
4. constructor （过于复杂，没必要）
5. 如果Spring容器中没有找到可以注入的实例bean时，将不会注入任何bean

> bean之间的关系：继承、依赖  
1. 继承：不是Java类中的继承（使用bean中parent属性）
    * 其实就是提公因式
    * 使用abstract=true 使父bean不被实例化
2. 依赖：使用depends-on属性
    * 依赖的bean没被配置则报错

> bean的作用域  
> ApplicationContext在初始化时，就实例化所有的单例Bean
1. 也就是说，默认bean的作用域是单例的singleton
2. 四种作用域：
    * prototype --- 每次都是新new的
    * singleton --- 默认
    * request --- web中生效
    * session --- web中生效

> 使用外部属性文件  
1. `<context:property-placeholder location="classpath:sa.properties"/>`
2. ${}访问属性文件

> Spring的表达式语言SpEL  
1. `#{}`
    * 引用其他对象及其属性：#{car.price}
    * 调用对象中的方法：#{car.toString()}
    * 调用静态方法/属性：#{T(java.lang.Math).PI}
    * 操作list，map集合取值：#{list[0]}, #{map["name"]}
2. 支持的运算符
    * 算术
    * 比较
    * 逻辑
    * 三目
    * 正则表达式

> Spring中bean生命周期  
1. 实例化bean
2. Spring注入bean的属性
3. 检查Aware相关接口
4. BeanPostProcessor前置处理
5. 检查是否是InitializingBean
6. 是否配置自定义init-method
7. BeanPostProcessor后置方法
8. Bean准备就绪，可以使用
9. 是否实现DisposableBean接口
10. 是否配置destroy-method
11. 结束


> 配置Bean实例  
> （通常利用xml 或者 annotation注解来配置bean实例）
1. xml中还包括三小类：
    * 反射模式
    * 工厂方法模式
    * Factory Bean模式
    
2. 工厂方法模式（一般还是使用普通的反射模式）  
    （Spring不会直接利用反射机制创建bean对象，而是先用反射机制找到Factory类，再用Factory生成bean对象）
    * 静态工厂方法 --- CarStaticFactory.java
        * 指定factory-method
        * 使用constructor-arg给工厂方法传值
    * 实例工厂方法 --- 先创建工厂实例，再通过实例获取bean
        * 更加灵活

3. FactoryBean接口
    * 自定义FactoryBean --- AFactoryBean.java
    * 一般用来整合第三方框架
    
> 使用注解来配置bean  
1. @Component()
2. @Service()
3. @Controller()
4. @Repository()   // dao层

5. @Scope(scopeName)
6. @Value(value)   // 用于属性和方法上

7. @PostConstruct()  // init-method
8. @PreDestroy()  // destroy-method
9. @AutoWired
    * 配合@Qualifier指定装配哪个对象

> 泛型依赖注入  
> (Spring支持进行依赖注入的同时利用泛型的优点对代码进行精简)

### Spring的切面编程概述
> AOP (Aspect Oriented Programming)  
> AOP可以说是对OOP的补充和完善，OOP使用封装、继承、多态等概念来建立一种对象层次结构。不过OOP允许开发定义纵向的关系，但不适合
> 定义横向的关系，比如日志功能，日志代码往往横向散布在整个对象层次结构的各处，导致了大量代码的重复
1. AOP技术把软件系统分为两个部分：
    * 核心关注点 --- 业务逻辑的主要流程是核心关注点
    * 横切关注点 --- 横切关注点经常发生的核心关注点的多处（事务，权限认证，事务）
2. AOP的作用在于：分离系统中的各种核心关注点和横切关注点
    * Spring中AOP代理由Spring的IoC容器负责生成、管理，其依赖关系也由IoC容器负责管理
    * 默认使用Java动态代理来创建AOP代理
3. AOP编程
    * 1定义普通业务组件
        * 使用面向接口编程
    * 2定义切入点
        * 定义切面，IoC通过切面和业务组件生成代理，代理对象通过接口引用
    * 3定义增强处理
4. 通知： LoggerAspect.java
    * @Before  前置通知
    * @After  后置通知
    * @AfterReturning  返回通知
    * @AfterThrowing  异常通知
    * @Around 环绕通知
    
> 基于xml配置文件的AOP使用  
1. aop:config
2. aop:pointcut
3. aop:aspect
    * aop:before
    * aop:after
    * aop:around....
    
### Spring声明式事务
> 事务注解
1. @Transactional
2. 属性propagation---传播
    * Propagation.REQUIRED --- 默认（当同一事务处理）
    * Propagation.REQUIRES_NEW --- 分开事务
    * 如果这两个事务的方法在同一个service类里，不管传播属性设置的值是哪个，都按同一事务来处理
2. 属性isolation
    * DEFAULT ---- MySQL repeatable-read
    * READ_COMMITTED
    * READ_UNCOMMITTED
    * REPEATABLE_READ
    * SERIALIZABLE
3. 属性readOnly
    * true --- 建议将所有查询方法都设置为只读
    * false（默认）
4. 属性timeout
    * 单位秒

> xml配置Spring事务