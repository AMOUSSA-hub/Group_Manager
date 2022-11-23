# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -cp build:$$CLASSPATH -implicit:none -sourcepath src -encoding UTF-8
JAVA = java
JAVA_OPTIONS = -cp .\build\ 
JAR = jar
EXEC_JAR = ${JAVA} -jar
SUP = rm -rf

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/projetIHM2022FI2
BUILD = build/fr/iutfbleau/projetIHM2022FI2
DOC = doc/fr/iutfbleau/projetIHM2022FI2
BUILD_WINDOWS = "build/fr/iutfbleau/projetIHM2022FI2/"
# CHOIX NOMS
JAR_MNP = test-mnp.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #
run : ${BUILD}/VIEW/DashboardGroupe.class
	${JAVA} ${JAVA_OPTIONS} fr.iutfbleau.projetIHM2022FI2.VIEW.DashboardGroupe

#${JAR_MNP}
#${EXEC_JAR} ${JAR_MNP}

# AUTRE BUTS
doc :
	javadoc -d doc src/fr/iutfbleau/projetIHM2022FI2/API/*.java src/fr/iutfbleau/projetIHM2022FI2/MNP/*.java

# POUR WINDOWS#
##clean : 
##	rd /s /q ${BUILD_WINDOWS}

# POUR LINUX #
clean : 
	rm -rf ${BUILD}/

##clean : 



# REGLES DE DEPENDANCE #

## API ##
${BUILD}/API/MonPrint.class : ${SRC}/API/MonPrint.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/MonPrint.java

${BUILD}/API/TypeGroupe.class : ${SRC}/API/TypeGroupe.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/TypeGroupe.java

${BUILD}/API/Groupe.class : ${SRC}/API/Groupe.java \
	  		     ${BUILD}/API/TypeGroupe.class\
			     ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Groupe.java

${BUILD}/API/Etudiant.class : ${SRC}/API/Etudiant.java \
                            ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Etudiant.java

${BUILD}/API/Changement.class : ${SRC}/API/Changement.java \
	  		            ${BUILD}/API/Etudiant.class \
	  		     	    ${BUILD}/API/Groupe.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Changement.java

${BUILD}/API/AbstractGroupeFactory.class : ${SRC}/API/AbstractGroupeFactory.java \
				${BUILD}/API/Groupe.class \
	  		         ${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/AbstractGroupeFactory.java

${BUILD}/API/AbstractChangementFactory.class : ${SRC}/API/AbstractChangementFactory.java \
	  		            ${BUILD}/API/AbstractGroupeFactory.class \
				    ${BUILD}/API/Changement.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/AbstractChangementFactory.java


## MNP ##

${BUILD}/MNP/EtudiantNP.class : ${SRC}/MNP/EtudiantNP.java \
                              ${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/EtudiantNP.java


${BUILD}/MNP/GroupeNP.class : ${SRC}/MNP/GroupeNP.java \
                              ${BUILD}/API/Groupe.class \
			      ${BUILD}/API/TypeGroupe.class \
                              ${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/GroupeNP.java


${BUILD}/MNP/ChangementNP.class : ${BUILD}/API/Changement.class \
				${SRC}/MNP/ChangementNP.java \
                              ${BUILD}/API/Groupe.class \
                              ${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ChangementNP.java

${BUILD}/MNP/AbstractGroupeFactoryNP.class : ${SRC}/MNP/AbstractGroupeFactoryNP.java \
				${BUILD}/API/AbstractGroupeFactory.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/AbstractGroupeFactoryNP.java

${BUILD}/MNP/AbstractChangementFactoryNP.class : ${SRC}/MNP/AbstractChangementFactoryNP.java \
				${BUILD}/API/AbstractChangementFactory.class \
				${BUILD}/API/Changement.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/AbstractChangementFactoryNP.java


###...

## TEST ##

 ${BUILD}/Test/TestTexteMNP.class : ${SRC}/Test/TestTexteMNP.java \
			 ${BUILD}/MNP/EtudiantNP.class \
			 ${BUILD}/MNP/GroupeNP.class \
			 ${BUILD}/MNP/ChangementNP.class \
                         ${BUILD}/MNP/AbstractGroupeFactoryNP.class \
                         ${BUILD}/MNP/AbstractChangementFactoryNP.class	
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/Test/TestTexteMNP.java



##UTILS#

${BUILD}/UTILS/Utils.class: ${SRC}/UTILS/Utils.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/UTILS/utils.java

## CONTROLLER ##

${BUILD}/CONTROLLER/Observateur_CG.class: ${SRC}/CONTROLLER/Observateur_CG.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_CG.java

${BUILD}/CONTROLLER/Observateur_arborescence.class: ${SRC}/CONTROLLER/Observateur_arborescence.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_arborescence.java

${BUILD}/CONTROLLER/Observateur_MEG.class: ${SRC}/CONTROLLER/Observateur_MEG.java \
		${BUILD}/VIEW/CreationGroupe.class \
		${BUILD}/VIEW/EditGroupe.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_MEG.java

## VIEW ##

${BUILD}/VIEW/EditGroupe.class: ${SRC}/VIEW/EditGroupe.java 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/EditGroupe.java		


${BUILD}/VIEW/CreationGroupe.class: ${SRC}/VIEW/CreationGroupe.java \
				${BUILD}/CONTROLLER/Observateur_CG.class \ 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/CreationGroupe.java


${BUILD}/VIEW/DashboardGroupe.class: ${SRC}/VIEW/DashboardGroupe.java \
				${BUILD}/UTILS/Utils.class \
				${BUILD}/CONTROLLER/Observateur_arborescence.class \
				${BUILD}/CONTROLLER/Observateur_MEG.class \				
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/DashboardGroupe.java
	







# ## JARS ##

 #${JAR_MNP} : ${BUILD}/Test/TestTexteMNP.class
#	${JAR} cvfe ${JAR_MNP} fr.iutfbleau.projetIHM2022FI2.Test.TestTexteMNP -C build fr

