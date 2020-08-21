pipeline {

   agent any
   tools {
		jdk "JAVA_HOME"
		maven "MAVEN_HOME"
   }
   stages {
		stage('Build') {
			steps {
				echo 'Buliding'
				sh '''
                    mvn clean package
				'''
			}
		}
		stage('Deploy'){
			steps{
				echo 'Deploying'
				sh '''
					PID=$(ps -ef | grep oasis | grep java | grep -v grep | awk '{ print $2 }')
					if [ -z "$PID"]
					then
						echo Application is already stopped
					else
						echo kill $PID
						kill $PID
					fi
				'''
				withEnv(['JENKINS_NODE_COOKIE=dontkillme']){
				    sh '''
    				    nohup java -jar ~/workspace/our-oasis/target/our-oasis-0.0.1-SNAPSHOT.jar > ~/workspace/log.txt &
				    '''
				}
			}
		}
		
   }
}