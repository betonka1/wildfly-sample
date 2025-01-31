package org.example.wildfly3.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * Sample integration test: demonstrates how to create the EAR file using the ShrinkWrap API.
 * <p>
 * Delete this file if no integration test is required.
 */
@RunWith(Arquillian.class)
public class SampleIT {

    /**
     * Creates the EAR file that is deployed to the server.
     *
     * @return EAR archive
     */
    @Deployment
    public static Archive<?> getEarArchive() {
        // Create the EAR archive:
        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, "wildfly3-ear.ear");

        // Current directory is the root of the "wildfly3-web" project. Go up one level
        // and enter the "wildfly3-ejb" project.
        // The ejb jar is found in the "target" directory:
        File f = new File("../wildfly3-ejb/target/wildfly3-ejb.jar");
        JavaArchive ejbJar = ShrinkWrap.create(ZipImporter.class, "wildfly3-ejb.jar").importFrom(f).as(JavaArchive.class);
        ear.addAsModule(ejbJar);

        // Now grab the web archive:
        f = new File("./target/wildfly3-web.war");
        if (f.exists() == false) {
            throw new RuntimeException("File " + f.getAbsolutePath() + " does not exist.");
        }
        WebArchive war = ShrinkWrap.create(ZipImporter.class, "wildfly3-web.war").importFrom(f).as(WebArchive.class);
        ear.addAsModule(war);

        // The manifest file is auto created by the Maven EAR plugin - we don't have it here.

        // Add the package containing the test classes:
        war.addPackage("org.example.wildfly3.test");

        // Export the EAR file to examine it in case of problems:
        // ear.as(ZipExporter.class).exportTo(new File("c:\\temp\\test.ear"), true);

        return ear;
    }

    /**
     * A sample test...
     */
    @Test
    public void test() {
        // This line will be written on the server console.
        System.out.println("Test is invoked...");
    }
}
