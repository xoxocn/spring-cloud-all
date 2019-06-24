## springcloudall系统

1. springcloudall系统介绍

   springcloudall是基于springcloud的demo系统，该系统提供了同一的编码规范，开发流程，目录结构，日志处理，异常处理...，让开发人员能简单、快速的搭建一个springcloud微服务系统。

   1.1目录结构

   该demo，基于微服务的架构方式。提供3层moudle

   ├── client // 向外暴露@Feign 接口 依赖common

   ├── common // common DTO层，server、client都需要依赖

   ├── server // 上线项目文件	// 服务层，实现client接口

   	 ├── src/main/java/com/xoxo

   		├── config		//基础配置，filter、handler、aop等

   		├── controlller	//控制层

   		├── dto			//控制层与逻辑层交互，数据传输层

   		├── enums		//各种枚举，异常枚举、基础枚举、常量枚举等

   	 	├── exception	//异常类，自定义异常

   		├── form		//前端与控制层交互，存放表单，可做数据校验

   		├── mapper		//dao层

   		├── model		//entity层

   		├── service		//逻辑层

   		├── utils		//工具包

   		├── VO			//VO，控制层与前端交互响应数据

   1.2 日志模块

   日志模块介绍

   1.2.1 日志目录结构

   ​	${LOG_PATH}/info.log

   ```
    		 	/debug.log
   
    		 	/error.log
   
    		 	/warn.log
   ```

   1.2.2 代码配置

   ​	com.xoxo.config.handler.UnifyExceptionHandler：全局异常拦截器

   ​	com.xoxo.enums：各种枚举配置

   ​	com.xoxo.exception.CommonException：基础异常类，所有自定义异常类必须继承它

   1.2.3 日志模块使用：

   ​	以前代码捕获异常需要try catch捕获，现在都交给全局异常拦截器进行捕获，如需详细配置，可以前往

   com.xoxo.common.config.ExceptionConfig进行配置。

   ​	例子：

   ```java
   /**
    * 所有日志打印使用@Slf4j注解即可
    */
   @Slf4j
   @Service
   public class ProductInfoServiceImpl implements ProductInfoService {
   
       @Resource
       private ProductInfoMapper productInfoMapper;
       @Resource
       private IRedisService redisService;
   
       @Override
       public List<ProductInfoDTO> findProductInfos() {
           log.info("findProductInfos ------start");
           String productInfoListStr;
           List<ProductInfoDTO> productInfoDTOS;
           List<ProductInfo> productInfos;
           // 异常捕获测试，直接抛出异常即可
           if("".equals("")){
           	log.error("异常：....");
               throw new BuizException(ExceptionEnum.buiz_ex001);
           }
           log.info("findProductInfos ------end");
       }
   ```

   

2. 异常介绍

   server包下的exception包下存放异常类，所有自定义异常将继承

   ```
   /**
    * @Package com.xoxo.demo.common.enums
    * @Description 所有自定义异常必须继承此类
    * @Author xiehua
    * @Date 2019-01-08 18:21
    */
   @Getter
   public abstract class CommonException extends RuntimeException{
       protected String code;
   
       public CommonException(String code, String msg){
           super(msg);
           this.code = code;
       }
   }
   ```

   在异常拦截器中配置其相关拦截

   ```
   @Slf4j
   @RestControllerAdvice
   public class ExceptionConfig {
   
       @ExceptionHandler(value = Exception.class)
       public ResultVO exceptionHandler(Exception e){
           log.warn("异常：{}",e.getMessage());
           return ResultVOUtil.exception(ResponseEnum.server_error);
       }
   
       @ExceptionHandler(value= RespBaseException.class)
       public ResultVO exceptionHandler(RespBaseException e){
           log.warn("异常：{}",e.getMessage());
           return ResultVOUtil.exception(e);
       }
   }
   ```

   

3. 开发流程

   2.1 首先确定是微服务之间调用，还是与前端页面交互。

   ​	微服务之间调用：

   ​		双方确定接口组成

   ​		服务提供者将讨论好的接口写入client模块，交互的数据类型写入common模块，然后发布jar到maven私服，或者提交版本管理，服务消费者可直接开发，无需关心接口实现。

   ​	前端页面交互：

   ​		前端与后台约定入参请求接口，出参响应返回，前端需做参数校验，后台也需要做相应的校验，后台使用hibernate-validator校验。

   具体使用参考：

   ```java
       @PostMapping("/saveProductInfo")
       public void saveProductInfo(@Valid ProductForm productForm, BindingResult bindingResult){
           if(bindingResult.hasErrors()){
               //1、有异常地方最直接进行捕获
              	//2、打印日志
               //3、异常抛出
               log.error("【创建产品】参数不正确, productForm={}",productForm);
               throw new ProductException(ResultEnum.PRODUCT_PARAM_ERROR.getCode()
               ,bindingResult.getFieldError().getDefaultMessage());
           }
           ProductInfo productInfo = new ProductInfo();
           BeanUtils.copyProperties(productForm,productInfo);
           productInfoService.saveOrUpdate(productInfo);
   		//        return ResultVOUtil.success(true);
       }
   ```

   ​	枚举配置：

   ​		错误异常,form表单校验等，错误异常需要以模块进行错误枚举配置	

   ​		enums包中配置模块或者具体状态值的对应枚举，form中配置与前台交互的表单类（应用于校验），exception包中创建模块对应异常类。

   ​	具体使用参考：

   ​	enums

   ```java
   @Getter
   public enum ProductStatusEnum {
       UP("0","上架"),
       DOWN("1","下架")
       ;
   
       private String code;
   
       private String message;
   
       ProductStatusEnum(String code, String message) {
           this.code = code;
           this.message = message;
       }
   }
   
   @Getter
   public enum ResultEnum {
   
       PRODUCT_NOT_EXiST("1","商品不存在"),
       PRODUCT_STOCK_ERROR("2","库存有误"),
       PRODUCT_PARAM_ERROR("3","商品参数有误"),
       ;
   
       private String code;
   
       private String message;
   
       ResultEnum(String code, String message) {
           this.code = code;
           this.message = message;
       }
   }
   
   ```

   ​	form:

   ​	具体使用参考 <https://www.cnblogs.com/mr-yang-localhost/p/7812038.html>

   ```java
   /**
    * 具体使用参考： https://www.cnblogs.com/mr-yang-localhost/p/7812038.html
    */
   @Data
   public class ProductForm{
   
       /**
        * 商品名称
        */
       @NotEmpty(message = "产品名称必填")
       private String productName;
   
       /**
        * 单价
        */
       @NotEmpty(message = "产品单价必填")
       private BigDecimal productPrice;
   
       /**
        * 库存
        */
       @NotEmpty(message = "产品库存必填")
       private Integer productStock;
   
       /**
        * 描述
        */
       @NotEmpty(message = "产品描述必填")
       private String productDescription;
   
       /**
        * 小图
        */
       @NotEmpty(message = "产品小图必填")
       private String productIcon;
   
       /**
        * 商品状态,0正常1下架
        */
       @NotEmpty(message = "产品状态必填")
       private Integer productStatus;
   
       /**
        * 类目编号
        */
       @NotEmpty(message = "类目必填")
       private Integer categoryType;
   
   }
   
   ```

   2.4 使用mybatis-generator工具生成代码

   2.5 进行开发。