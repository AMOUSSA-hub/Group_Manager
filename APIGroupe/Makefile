# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -cp build -sourcepath src   -encoding UTF-8 -implicit:none
JAVA = java
JAVA_OPTIONS_W = -cp "build;res/mariadb-client.jar" 
JAVA_OPTIONS = -cp "build:res/mariadb-client.jar" 
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
run : ${BUILD}/VIEW/ChoixProfil.class
	${JAVA} ${JAVA_OPTIONS} fr.iutfbleau.projetIHM2022FI2.VIEW.ChoixProfil

run_W : ${BUILD}/VIEW/ChoixProfil.class
	${JAVA} ${JAVA_OPTIONS_W} fr.iutfbleau.projetIHM2022FI2.VIEW.ChoixProfil

#${JAR_MNP}
#${EXEC_JAR} ${JAR_MNP}

# AUTRE BUTS
doc :
	javadoc -d doc src/fr/iutfbleau/projetIHM2022FI2/API/*.java src/fr/iutfbleau/projetIHM2022FI2/MNP/*.java

# POUR WINDOWS#
clean_W : 
	rd /s /q ${BUILD_WINDOWS}

#POUR LINUX 
clean : 
	rm .\build\

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
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/UTILS/Utils.java

##MODEL##


${BUILD}/MODEL/MyGroupe.class: ${SRC}/MODEL/MyGroupe.java \
				${BUILD}/API/Groupe.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODEL/MyGroupe.java

${BUILD}/MODEL/MyEtudiant.class: ${SRC}/MODEL/MyEtudiant.java \
                            ${BUILD}/API/Etudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODEL/MyEtudiant.java


${BUILD}/MODEL/MyAbstractGroupeFactory.class: ${SRC}/MODEL/MyAbstractGroupeFactory.java \
				${BUILD}/API/AbstractGroupeFactory.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODEL/MyAbstractGroupeFactory.java


	

## CONTROLLER ##


${BUILD}/CONTROLLER/Observateur_creation_partition.class: ${SRC}/CONTROLLER/Observateur_creation_partition.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_creation_partition.java

${BUILD}/CONTROLLER/ObservateurChoixProfil.class: ${SRC}/CONTROLLER/ObservateurChoixProfil.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/ObservateurChoixProfil.java


${BUILD}/CONTROLLER/Observateur_ajout_etudiant.class: ${SRC}/CONTROLLER/Observateur_ajout_etudiant.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_ajout_etudiant.java


${BUILD}/CONTROLLER/ObservateurCreation.class: ${SRC}/CONTROLLER/ObservateurCreation.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/ObservateurCreation.java

${BUILD}/CONTROLLER/Observateur_arborescence.class: ${SRC}/CONTROLLER/Observateur_arborescence.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_arborescence.java

${BUILD}/CONTROLLER/Observateur_MEG.class: ${SRC}/CONTROLLER/Observateur_MEG.java \
		${BUILD}/VIEW/Admin/CreationGroupe.class \
		${BUILD}/VIEW/Admin/EditGroupe.class \
		${BUILD}/VIEW/Admin/Creation_Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_MEG.java

${BUILD}/CONTROLLER/Observateur_EG.class: ${SRC}/CONTROLLER/Observateur_EG.java \
		${BUILD}/VIEW/Admin/Ajout_Etudiant.class \
		${BUILD}/VIEW/Admin/Creation_Partition.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_EG.java

${BUILD}/CONTROLLER/Observateur_menu_etudiant.class: ${SRC}/CONTROLLER/Observateur_menu_etudiant.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_menu_etudiant.java




## VIEW ##

${BUILD}/VIEW/Admin/Creation_Partition.class: ${SRC}/VIEW/Admin/Creation_Partition.java \
		${BUILD}/CONTROLLER/Observateur_creation_partition.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/Creation_Partition.java

${BUILD}/VIEW/Admin/pan_info_groupe.class: ${SRC}/VIEW/Admin/pan_info_groupe.java 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/pan_info_groupe.java	

${BUILD}/VIEW/ChoixProfil.class: ${SRC}/VIEW/ChoixProfil.java \
			${BUILD}/VIEW/Admin/DashboardGroupe.class \
			${BUILD}/VIEW/Etudiant/ViewEtudiant.class \
			${BUILD}/CONTROLLER/ObservateurChoixProfil.class 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/ChoixProfil.java	

${BUILD}/VIEW/Etudiant/ViewEtudiant.class: ${SRC}/VIEW/Etudiant/ViewEtudiant.java 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Etudiant/ViewEtudiant.java	





${BUILD}/VIEW/Admin/EditGroupe.class: ${SRC}/VIEW/Admin/EditGroupe.java \
		${BUILD}/CONTROLLER/Observateur_EG.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/EditGroupe.java	

${BUILD}/VIEW/Admin/Creation_Etudiant.class: ${SRC}/VIEW/Admin/Creation_Etudiant.java 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/Creation_Etudiant.java	


${BUILD}/VIEW/Admin/CreationGroupe.class: ${SRC}/VIEW/Admin/CreationGroupe.java \
				${BUILD}/CONTROLLER/ObservateurCreation.class  
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/CreationGroupe.java


${BUILD}/VIEW/Admin/DashboardGroupe.class: ${SRC}/VIEW/Admin/DashboardGroupe.java \
				${BUILD}/UTILS/Utils.class \
				${BUILD}/CONTROLLER/Observateur_arborescence.class \
				${BUILD}/CONTROLLER/Observateur_MEG.class \
				${BUILD}/MODEL/MyGroupe.class \
				${BUILD}/MODEL/MyEtudiant.class \
				${BUILD}/MODEL/MyAbstractGroupeFactory.class \
				${BUILD}/CONTROLLER/Observateur_menu_etudiant.class \
				${BUILD}/VIEW/Admin/pan_info_groupe.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/DashboardGroupe.java
	
${BUILD}/VIEW/Admin/Ajout_Etudiant.class: ${SRC}/VIEW/Admin/Ajout_Etudiant.java \
				${BUILD}/CONTROLLER/Observateur_ajout_etudiant.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/Ajout_Etudiant.java






# ## JARS ##

 #${JAR_MNP} : ${BUILD}/Test/TestTexteMNP.class
#	${JAR} cvfe ${JAR_MNP} fr.iutfbleau.projetIHM2022FI2.Test.TestTexteMNP -C build fr

