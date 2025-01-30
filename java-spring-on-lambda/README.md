Here we copy java code from [java-spring-on-lambda repo](https://github.com/jeastham1993/java-spring-on-lambda), but convert the SAM to Terraform.

## Backgroud
run your SpringBoot applications on AWS Lambda in as little as 10 minutes without changing a single line of code.
- vedio: https://www.youtube.com/watch?v=A1rYiHTy9Lg&list=PLCOG9xkUD90IDm9tcY-5nMK6X6g8SD-Sz
  - [ GitHub Repo](https://github.com/jeastham1993/java-spring-on-lambda)
  - [AWS Serverless Java Container](https://github.com/aws/serverless-java-container) 
  - AWS Lambda - https://aws.amazon.com/lambda/
  - AWS SAM - https://aws.amazon.com/serverless/sam/

## AWS SAM
- Abstraction: SAM provides higher-level abstractions specifically for serverless applications, such as AWS::Serverless::Function, AWS::Serverless::Api, and AWS::Serverless::SimpleTable. These abstractions simplify the process of defining and deploying serverless applications.
- Transform: SAM uses the Transform directive to automatically handle the creation and configuration of underlying AWS resources, such as IAM roles, API Gateway, and Lambda functions.

## Terraform
To convert [java-spring-on-lambda/template.yaml](https://github.com/jeastham1993/java-spring-on-lambda/blob/main/template.yaml) to TF by using 
- source = "terraform-aws-modules/apigateway-v2/aws" refer to branch of `website ::CapsidBuilder/capbuild-web/terraform/api.tf`
- [Terraform Modules git repo](https://github.com/terraform-aws-modules)

## Proxy
### RDS proxy
put a layer in front of your database to act as a proxy, hence your lambda function can reuse connections that already exists. 

### dynamoDB
DynamoDB is designed to handle high request rates and can scale automatically to accommodate increased traffic. no extra proxy required. 

### snapStart
AWS Lambda SnapStart is a feature designed to improve the startup performance of Java-based AWS Lambda functions. It reduces the cold start latency by initializing the function ahead of time and caching a snapshot of the initialized execution environment. When the function is invoked, AWS Lambda can quickly restore the execution environment from the snapshot, significantly reducing the startup time.

```
aws lambda update-function-configuration --function-name my-java-function --snap-start ApplyOn=PublishedVersions
```

## credential 
### Secret Manager
store database credential here
- add dependency
```
        <dependency>
            <groupId>software.amazon.awssdk</groupId>
            <artifactId>secretsmanager </artifactId>
        </dependency>
```
- java code examples: [AwsSecret.java](https://github.com/jeastham1993/java-spring-on-lambda/blob/main/src/main/java/com/product/api/AwsSecret.java) and [JpaConfiguration.java](https://github.com/jeastham1993/java-spring-on-lambda/blob/main/src/main/java/com/product/api/JpaConfiguration.java)
  
### aws parameter store
- store environment parameters
  refer to java code [software.amazon.awssdk.services.ssm.*](https://github.com/jeastham1993/java-spring-on-lambda/blob/main/src/main/java/com/product/api/ApplicationConfiguration.java)

### cognito 
refer to https://github.com/GSI-Xapiens-CSIRO/BGSI-GeneticAnalysisSupportPlatformIndonesia-GASPI/blob/main/cognito/outputs.tf







