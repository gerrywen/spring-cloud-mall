- [docker部署zipkin](https://blog.csdn.net/Lou_Lan/article/details/101198216)
- [springboot zipkin elasticsearch全链路追踪](https://www.520mwx.com/view/55641)
```
docker启动命令：

docker run  --name zipkin -d -p 9411 :9411 -e STORAGE_TYPE=elasticsearch -e ES_HOSTS=http://101.123.153.122:9200  openzipkin/zipkin


应用程序配置：
spring.sleuth.enabled=true
spring.sleuth.feign.enabled=true

spring.zipkin.base-url=http://101.123.153.122:5005
spring.sleuth.sampler.percentage = 1.0
```

