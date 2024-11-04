pipeline {
    agent any

    // Définir les options du pipeline
    options {
        // Option de timeout (par exemple, 1 heure)
        timeout(time: 1, unit: 'HOURS')
    }

    stages {
        // Étape de clonage du dépôt
        stage('Clone Repository') {
            steps {
                // Cloner le dépôt depuis GitHub
                git 'https://github.com/yassminenahdi/Devops'
            }
        }

        // Étape de build
        stage('Build') {
            steps {
                script {
                    // Exécuter la commande Maven pour construire le projet
                    sh 'mvn clean package'
                }
            }
        }

        // Étape de test
        stage('Test') {
            steps {
                script {
                    // Exécuter les tests
                    sh 'mvn test'
                }
            }
        }

        // Étape de déploiement (adaptable en fonction de votre configuration)
        stage('Deploy') {
            steps {
                script {
                    // Déploiement (vous pouvez ajouter des étapes spécifiques ici)
                    echo 'Déploiement en cours...'
                }
            }
        }
    }

    // Étape de post-traitement
    post {
        success {
            echo 'Le pipeline a réussi!'
        }
        failure {
            echo 'Le pipeline a échoué!'
        }
        always {
            // Actions à réaliser toujours, par exemple, nettoyer les ressources
            cleanWs()
        }
    }
}
