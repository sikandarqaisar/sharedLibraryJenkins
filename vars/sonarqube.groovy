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
	 jacoco changeBuildStatus: true, sourcePattern: '**/src/'
			# Metadata
			sonar.projectName=${JOB_NAME}
			sonar.projectVersion=1.0.0

			# Source information
			sonar.sources=src/
			sonar.sourceEncoding=UTF-8
			sonar.language=java

			# Tests
			sonar.tests=src/
			sonar.junit.reportsPath=target/surefire-reports
			sonar.surefire.reportsPath=target/surefire-reports
			sonar.jacoco.reportPath=target/jacoco.exec
			sonar.binaries=target/classes
			sonar.java.coveragePlugin=jacoco

			# Debug
			sonar.verbose=true	   
		   
		   
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
