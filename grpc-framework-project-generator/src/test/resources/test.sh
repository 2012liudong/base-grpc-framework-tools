#!/bin/bash
java -jar grpc-framework-project-generator-1.0-SNAPSHOT-jar-with-dependencies.jar setting=project_fixed.setting
java -jar grpc-framework-project-generator-1.0-SNAPSHOT-jar-with-dependencies.jar setting=project_grpc.setting
java -jar grpc-framework-project-generator-1.0-SNAPSHOT-jar-with-dependencies.jar setting=project_restful.setting