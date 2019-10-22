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
   def sonarUrl = 'sonar.host.url=http://localhost:9000'
   def mvn = tool (name: 'maven3', type: 'maven') + '/bin/mvn'	
   stage('Sonar Publish'){
	   sh 'mvn clean'
	   sh 'mvn package'
	   sh 'mvn install'
	   sh 'mvn test'
	   sh 'mvn sonar:sonar -Dsonar.host.url=http://34.212.168.221 -Dsonar.login=admin -Dsonar.password=0yakGXSe6Wwz'
	   executeScanner('Sonar-4.2','sonar-7.8')	         
   }
	stage('jacoco'){
	    jacoco( 
	      execPattern: 'target/*.exec',
	      classPattern: 'target/classes',
	      sourcePattern: 'src/main/java',
	      exclusionPattern: 'src/test*')

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
