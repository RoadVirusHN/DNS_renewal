chown -R ec2-user ~ 
sudo amazon-linux-extras install -y docker
sudo service docker start
sudo chmod 666 /var/run/docker.sock

sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

sudo yum -y update 
sudo yum -y install git

git clone https://github.com/RoadVirusHN/DNS_renewal.git


