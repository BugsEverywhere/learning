本项目为promotion tool提供后端服务

项目依赖maven打包和创建

运行maven的package周期，在target目录中会生成promotion-tool-core.war

将war包放入服务器tomcat中的wabapps目录下，删除原有的war包和promotion-tool-core文件夹，重启tomcat即可



目前该项目部署在192.168.0.23的windows机器上，tomcat服务器位于C:\development\apache-tomcat-9.0.0.M21

目前tomcat端口为8081，如需修改端口，在上述目录下的conf目录下打开server.xml文件，修改如下标签中的端口号即可
<Connector port="8081" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
			   
在bin下双击startup.bat文件运行tomcat即可访问