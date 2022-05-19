# 远程Docker环境

testcontainers会自动检测Docker环境，通常在本地开发时，可能本机已经装了Docker，那么可以直接使用本机的Docker，如果本机没有Docker或者是在CI上进行测试，我们就需要自定义Docker主机检测,需要使用远程服务器的Docker。

```bash
# ~/.testcontainers.properties
docker.client.strategy=org.testcontainers.dockerclient.EnvironmentAndSystemPropertyClientProviderStrategy
docker.host=tcp\://my.docker.host\:1234 # 这里host指向服务器docker环境
```

# Reference

* https://github.com/testcontainers/testcontainers-java/blob/master/docs/features/configuration.md
* https://github.com/Playtika/testcontainers-spring-boot
* https://www.testcontainers.org/
