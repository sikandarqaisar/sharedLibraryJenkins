def call(Map inputs){
Url=inputs.url
gitBranch=inputs.gitbranch
EmailFrom=inputs.EmailFrom
EmailTo=inputs.EmailTo
node {
   def sonarUrl = 'sonar.host.url=http://52.14.213.249:9000'
   def mvn = tool (name: 'maven3', type: 'maven') + '/bin/mvn'
   stage('SCM Checkout'){
	git branch: gitBranch, 
	url: Url
   }
   
   stage('Sonar Publish'){
	   withCredentials([string(credentialsId: 'jenkins-id', variable: 'sonarToken')]) {
        def sonarToken = "sonar.login=${sonarToken}"
        sh "${mvn} sonar:sonar -D${sonarUrl}  -D${sonarToken}"
	 }
      
   }

	
   stage('Email Notification'){
		mail bcc: '', body: """Hi Team, You build successfully deployed
		                       Job URL : ${env.JOB_URL}
							   Job Name: ${env.JOB_NAME}
Thanks,
DevOps Team""", cc: '', from: EmailFrom, replyTo: '', subject: "${env.JOB_NAME} Success", to: EmailTo
   
   }
}

}
