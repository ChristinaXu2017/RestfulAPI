variable "region" {
  description = "AWS region"
  type        = string
}

variable "security_group_id" {
  description = "Security Group for the application"
  type        = string
}

variable "subnet1_id" {
  description = "Subnet Id for the first subnet"
  type        = string
}

variable "subnet2_id" {
  description = "Subnet Id for the second subnet"
  type        = string
}

variable "database_secret_name" {
  description = "The name of the secret holding database credentials"
  type        = string
}

variable "database_endpoint" {
  description = "The endpoint of the database"
  type        = string
}
