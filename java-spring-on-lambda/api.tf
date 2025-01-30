module "api_gateway" {
  source  = "terraform-aws-modules/apigateway-v2/aws"
  version = "~> 1.0"

  name          = "ProductApi"
  protocol_type = "HTTP"

  integrations = {
    lambda = {
      lambda_arn = aws_lambda_function.product_api_function.arn
      uri        = aws_lambda_function.product_api_function.invoke_arn
    }
  }

  routes = [
    {
      route_key = "ANY /{proxy+}"
      target    = "integrations/lambda"
    }
  ]
}
