# demo

### demo架构
1. 该demo作于springcloud基本架构形式，可快速开发 
2. 该demo，基于微服务的架构方式。提供3层moudle

   ├── client 						// 向外暴露@Feign 接口 依赖common

   ├── common 					// common DTO层，server、client都需要依赖

   ├── server // 上线项目文件		// 服务层，实现client接口
