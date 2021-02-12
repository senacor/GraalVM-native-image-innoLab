# Instruction

## Pre-requisites

1. Install AWS CLI V2: `https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-mac.html`
1. Configure aws credentials:
    1. Create credentials: `https://console.aws.amazon.com/iam/home?nc2=h_m_sc#/security_credentials?credentials=iam`
    2. Create credentials file at `~/.aws/credentials`:
        ```
       [default]
       aws_access_key_id = <aws_access_key_id>
       aws_secret_access_key = <aws_secret_access_key>
       ```

## AWS Lambda with JVM

1. `mvn clean package`
1. insert `LAMBDA_ROLE_ARN="arn:aws:iam::604370441254:role/innolab-graalvm-lambda-role"` in line 52 of `target/manage.sh` (or expose it as environment variable)
1. create lambda with `sh target/manage.sh create`
1. invoke lambda with `sh target/manage.sh invoke`
1. delete lambda with `sh target/manage.sh delete`

## AWS Lambda with Native Image

1. `mvn clean package -Pnative -Dnative-image.docker-build=true`
1. insert `LAMBDA_ROLE_ARN="arn:aws:iam::604370441254:role/innolab-graalvm-lambda-role"` in line 52 of `target/manage.sh` (or expose it as environment variable)
1. create lambda with `sh target/manage.sh native create`
1. invoke lambda with `sh target/manage.sh native invoke`
1. delete lambda with `sh target/manage.sh native delete`
