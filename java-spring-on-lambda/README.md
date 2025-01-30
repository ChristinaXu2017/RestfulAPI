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














