def call(Map inputs){
node {
   def sonarUrl = 'sonar.host.url=http://3.19.245.190:9000'
   def mvn = tool (name: 'maven3', type: 'maven') + '/bin/mvn'
   stage('SCM Checkout'){
    // Clone repo
	git branch: 'master', 
    //	credentialsId: 'github', 
	url: 'https://github.com/sikandarqaisar/sharedLibraryJenkins'
   
   }
   
   stage('Sonar Publish'){
	   withCredentials([string(credentialsId: 'jenkins-id', variable: 'sonarToken')]) {
        def sonarToken = "sonar.login=${sonarToken}"
        sh "${mvn} sonar:sonar -D${sonarUrl}  -D${sonarToken}"
	 }
      
   }
   
	
//   stage('Mvn Package'){
//	   // Build using maven
	   
//	   sh "${mvn} clean package deploy"
 //  }
   
//   stage('deploy-dev'){
 //      def tomcatDevIp = '172.31.28.172'
//	   def tomcatHome = '/opt/tomcat8/'
//	   def webApps = tomcatHome+'webapps/'
//	   def tomcatStart = "${tomcatHome}bin/startup.sh"
//	   def tomcatStop = "${tomcatHome}bin/shutdown.sh"
	   
//	   sshagent (credentials: ['tomcat-dev']) {
//	      sh "scp -o StrictHostKeyChecking=no target/myweb*.war ec2-user@${tomcatDevIp}:${webApps}myweb.war"
  //        sh "ssh ec2-user@${tomcatDevIp} ${tomcatStop}"
	//	  sh "ssh ec2-user@${tomcatDevIp} ${tomcatStart}"
  //     }
 //  }
   stage('Email Notification'){
		mail bcc: '', body: """Hi Team, You build successfully deployed
		                       Job URL : ${env.JOB_URL}
							   Job Name: ${env.JOB_NAME}
Thanks,
DevOps Team""", cc: '', from: 'sikandar@eurustechnologies.com', replyTo: '', subject: "${env.JOB_NAME} Success", to: 'sikandarqaisar@gmail.com'
   
   }
}

}






/*
import hudson.model.*
import jenkins.model.*
import hudson.plugins.sonar.*
import hudson.plugins.sonar.model.TriggersConfig
import hudson.tools.*
def call(Map inputs){
// Required environment variables
def sonar_name = "New setting"
def sonar_server_url = "http://sonarqube:9000"
def sonar_auth_token = "f397f759db7faf90233b715fbbb80ab7fc377d7c"
def sonar_mojo_version = ''
def sonar_additional_properties = ''
def sonar_triggers = new TriggersConfig()
def sonar_additional_analysis_properties = ''
def sonar_runner_version = '3.1.0.1141'

def instance = Jenkins.getInstance()

Thread.start {

    sleep(10000)
    println("Configuring SonarQube...")

    // Get the GlobalConfiguration descriptor of SonarQube plugin.
    def SonarGlobalConfiguration sonar_conf = instance.getDescriptor(SonarGlobalConfiguration.class)

    def sonar_inst = new SonarInstallation(
        sonar_name,
        sonar_server_url,
        sonar_auth_token,
        sonar_mojo_version,
        sonar_additional_properties,
        sonar_triggers,
        sonar_additional_analysis_properties
    )

    // Only add the new Sonar setting if it does not exist - do not overwrite existing config
    def sonar_installations = sonar_conf.getInstallations()
    def sonar_inst_exists = false
    sonar_installations.each {
        installation = (SonarInstallation) it
        if (sonar_inst.getName() == installation.getName()) {
            sonar_inst_exists = true
            println("Found existing installation: " + installation.getName())
        }
    }
    if (!sonar_inst_exists) {
        sonar_installations += sonar_inst
        sonar_conf.setInstallations((SonarInstallation[]) sonar_installations)
        sonar_conf.save()
    }

    // Step 2 - Configure SonarRunner
    println("Configuring SonarRunner...")
    def desc_SonarRunnerInst = instance.getDescriptor("hudson.plugins.sonar.SonarRunnerInstallation")
    def sonarRunnerInstaller = new SonarRunnerInstaller(sonar_runner_version)
    def installSourceProperty = new InstallSourceProperty([sonarRunnerInstaller])
    def sonarRunner_inst = new SonarRunnerInstallation("New Setting SonarRunner " + sonar_runner_version, "", [installSourceProperty])

    // Only add our Sonar Runner if it does not exist - do not overwrite existing config
    def sonar_runner_installations = desc_SonarRunnerInst.getInstallations()
    def sonar_runner_inst_exists = false
    sonar_runner_installations.each {
        installation = (SonarRunnerInstallation) it
        if (sonarRunner_inst.getName() == installation.getName()) {
            sonar_runner_inst_exists = true
            println("Found existing installation: " + installation.getName())
        }
    }
    if (!sonar_runner_inst_exists) {
        sonar_runner_installations += sonarRunner_inst
        desc_SonarRunnerInst.setInstallations((SonarRunnerInstallation[]) sonar_runner_installations)
        desc_SonarRunnerInst.save()
    }

    // Save the state
    instance.save()
}
}
*/
