def executeScanner(tool_name, server_name){
  def scannerHome = tool "$tool_name";
  withSonarQubeEnv("$server_name") {// If you have configured more than one global server connection, you can specify its name
    sh "${scannerHome}/bin/sonar-scanner"
  }
}


def call(Map inputs){
Url=inputs.url
gitBranch=inputs.gitbranch
EmailFrom=inputs.EmailFrom
EmailTo=inputs.EmailTo
node {
   def sonarUrl = 'sonar.host.url=http://18.218.252.174:9000'
   def mvn = tool (name: 'maven3', type: 'maven') + '/bin/mvn'	
   stage('Sonar Publish'){
	   sh 'mvn clean'
	   sh 'mvn package'
	   sh 'mvn install'
	   sh 'mvn test'
	   executeScanner('Sonar-4.2','sonar-8')	         
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
