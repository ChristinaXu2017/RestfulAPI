resource "aws_lambda_function" "product_api_function" {
  filename         = "path/to/your/lambda/package.zip"
  function_name    = "ProductApiFunction"
  handler          = "com.product.api.StreamLambdaHandler::handleRequest"
  runtime          = "java11"
  memory_size      = 2048
  timeout          = 30
  role             = module.lambda_exec_role.this_iam_role_arn
  architectures    = ["x86_64"]
  environment {
    variables = {
      POWERTOOLS_SERVICE_NAME = "ProductApi"
      SECRET_NAME             = "arn:aws:secretsmanager:${var.region}:${data.aws_caller_identity.current.account_id}:secret:${var.database_secret_name}"
      DATABASE_ENDPOINT       = var.database_endpoint
      JAVA_TOOL_OPTIONS       = "-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
    }
  }
  vpc_config {
    security_group_ids = [var.security_group_id]
    subnet_ids         = [var.subnet1_id, var.subnet2_id]
  }
}
