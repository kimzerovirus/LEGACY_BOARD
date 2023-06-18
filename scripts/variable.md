```shell
# $ vi ~/.bashrc
export profile=prod
export db_url=
export db_username=
export db_password=
export db_ddl=validate

# vi 수정 후 실행
# $ source ~/.bashrc 
# $ echo $profile
# https://velog.io/@seyoung755/AWS-EC2%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4-%EB%B0%B0%ED%8F%AC%ED%95%B4%EB%B3%B4%EC%9E%90-4-MySQL-%EC%84%9C%EB%B2%84-%EC%8B%A4%ED%96%89
```

```shell
sudo apt-get update
sudo iptables -t nat -A PREROUTING -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080
```


