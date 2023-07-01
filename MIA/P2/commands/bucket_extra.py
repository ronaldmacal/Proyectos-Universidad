import boto3
aws_access_key_id = 'AKIA4G2S4MZD53UTQ54I'
aws_secret_access_key = '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'
region_name = 'us-east-2'
bucket_name = 'bucket-miaproyecto2-g10'
s3 = boto3.client(
    's3',
    aws_access_key_id=aws_access_key_id,
    aws_secret_access_key=aws_secret_access_key,
    region_name=region_name
)

def transfer_bucket_bucket():
    
    print("Bucket - bucket")
    
def transfer_server_bucket():
    print("Server - bucket")
    
def transfer_bucket_server():
    print("Bucket - server")