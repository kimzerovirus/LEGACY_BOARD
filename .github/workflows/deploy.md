https://github.com/coturiv/setup-ionic/pull/15
https://github.com/actions/setup-java#supported-distributions

AWS CodeDeployment Access denied
-> S3관련 IAM USER에 codeDeployment에 관련된 권한을 설정해주지 않아서 denied 된것

codedeploy log 
```
cd /var/log/aws/codedeploy-agent
cat ***.log

cd /opt/codedeploy-agent/deployment-root/deployment-instructions/
```

https://goodgid.github.io/Github-Action-CI-CD-CodeDeploy-App-Spec-File/
