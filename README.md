# base-grpc-framework-tools
A tool for base-grpc-framework, through config file auto generator java server project. Can create restful、grpc and fixed(grp+restful) service. 

-------------------------------------------------------------------------------
## 📦 Start
```shell
# decompression zip file
# Step1: generator sprintBoot server project
java -jar grpc-framework-project-generator-1.0-SNAPSHOT-jar-with-dependencies.jar [setting=project_fixed.setting] [slt=/slt/source/]

# Step2: Run 
cd /output/xxxxProject
mvn clean install -Dmaven.test.skip=true
mvn clean package -Dmaven.test.skip=true
java -jar -Dspring.profiles.active=dev xxxxProject.jar

# Step3: Test
open http://localhost:18080/swagger-ui.html
```

## 🧬 Test
``` shell
# decompression zip file and exec command
sh ./test.sh
```

## 🛠️ Configuration
All source in slt/source, can edit files what is exsit, also support add personal file through config /slt/source/sltext.json.
```JSON
{
  "proto": [
    {"name": "GrpcAccessInterceptor.java", "packagePath": "/grpc/interceptor", "fileType": "source", "fileOperatorType": "create"}
  ]
}
[options]
type: proto, application, persistence, common, restful, grpc, fixed
name: fileName
packagePath: file path 
fileType:  source, yml, xml or config
fileOperatorType: create, copy or append
```
## 🏗️ Change logs