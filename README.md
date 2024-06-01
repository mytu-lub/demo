1.修改nacos配置文件
打开这两个配置
![img_1.png](img_1.png)
把这个改为你的nacos的ip
![img_2.png](img_2.png)

2.执行 mvn clean install
![img_3.png](img_3.png)

3.执行  docker-compose up
![img_4.png](img_4.png)

4. 访问接口文档
http://localhost:7573/doc.html#/home
![img.png](img.png)

5，调用接口获取token
取消传入请求头
![img_6.png](img_6.png)
输入账号密码
![img_5.png](img_5.png)
得到token

6.调用获取列表接口
传入token，调用接口，获取到列表
token 格式Bearer 76BDDF5F96914D668FAA8A5024650274
![img_7.png](img_7.png)

7.切换用户editor_1
获取editor_1用户的token
![img_8.png](img_8.png)

8.使用editor_1用户的token调用接口，修改产品
![img_10.png](img_10.png)![img_9.png](img_9.png)

调用接口，获取到列表验证是否修改成功
![img_11.png](img_11.png)

9.删除产品
![img_12.png](img_12.png)
验证是否删除成功
![img_13.png](img_13.png)