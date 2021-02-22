# Sample Wildfly Project

Simple Wildfly 20 EAR project.

## Wildfly installation

Go to https://www.wildfly.org/ find Wildfly 20.0.1.Final, download and extract 

## Manual Deployment

Requirements: Maven 3.6+

In project directory run:

	mvn package
	
Copy `wildfly.ear` from `target` directory to `<WildflyInstallationDir>/standalone/deployments`.
Copy `wildfly-ear/src/main/config/jboss/standalone/configuration/standalone.xml` to `<WildflyInstallationDir>/standalone/configuration`.
Create `wildfly.dodeploy` file in `<WildflyInstallationDir>/standalone/deployments`.

Start server on Linux by:
	
	<WildflyInstallationDir>/bin/standalone.sh

or Windows by:

	<WildflyInstallationDir>/bin/standalone.cmd 


## Deployment in Eclipse 

Requirements: Eclipse for Java EE 2020-12 (4.18.0) or newer.

Install Jboss Tools Eclipse plugin from [here](https://tools.jboss.org/downloads/jbosstools/index.html).

In eclipse open folder containing project as workspace.
Then:

	File -> Import -> Maven -> Existing Maven Project


Then:

	Window -> Show View -> Servers
	
In Servers View:

	New -> Server -> Jboss Community -> Wildfly 20

Fill the forms, set wildfly installation path.

Copy `wildfly-ear/src/main/config/jboss/standalone/configuration/standalone.xml` to `<WildflyInstallationDir>/standalone/configuration`.

To publish project on server, in Servers View:

	RMB on Server -> Add or Remove -> Add wildfly sample project -> Publish
	
To Debug:

	RMB on Server -> Debug