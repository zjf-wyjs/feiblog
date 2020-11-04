## 项目介绍
[http://feifei.plus](http://feifei.plus "菲菲博客")是一个前后端分离的博客系统，前后端全是由本人独立完成
* 前端因能力有限，一边学习viewUI，一边做的，故前端可能还有很多地方需要优化
* 之前做过ssm博客，由于现在前后端分离是未来趋势，所以，单独使用springboot+vue重新做一个，一方面是想学习下前端技术，另一方面也是为了搞个自己的博客，平时写点文章啥的。

feiblog-vue：前台展示+博客管理
feiblog-java：后端

### 前端介绍
前台使用 Vue + View UI，响应式布局，适配手机+电脑+平板，不同角色登录展示不同界面，本来最初采用Elment-ui的，后来看到官网说停止更新了，所以选用了当下最流行的View UI。ui也更加炫酷点，由于是边学变做出来的，所以界面不是很炫酷，后续慢慢完善

### 后端介绍
后端使用SpringBoot+ mybatis-plus + mySql 进行开发，使用Jwt + Shiro 做登录验证和权限校验，使用redis做缓存，文章编辑图片自动上传到七牛云，

### 站点演示
前端演示：[http://feifei.plus](http://feifei.plus)
通用账号：1
通用密码：1