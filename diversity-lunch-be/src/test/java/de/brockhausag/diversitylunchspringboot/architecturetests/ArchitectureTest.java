package de.brockhausag.diversitylunchspringboot.architecturetests;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import de.brockhausag.diversitylunchspringboot.DiversityLunchSpringBootApplication;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.Objects;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.adhereToPlantUmlDiagram;

class ArchitectureTest {

    private final JavaClasses importedClasses = new ClassFileImporter().importPackagesOf(DiversityLunchSpringBootApplication.class);

    @Test
    void noServiceDependsOnControllerTest() {

        ArchRule rule = noClasses()//
                .that()
                .resideInAnyPackage("..service..") //
                .should().dependOnClassesThat().resideInAnyPackage("..controller..");

        rule.check(importedClasses);
    }

    @Test
    void controllerTest() {

        ArchRule rule = classes().that()
                .resideInAPackage("..controller..")
                .or()
                .areAnnotatedWith(RestController.class)
                .or()
                .areAssignableTo(AbstractController.class)
                .and()
                .haveSimpleNameNotEndingWith("Test")
                .should()
                .haveSimpleNameEndingWith("Controller");

        rule.check(importedClasses);
    }

    @Test
    void configTest(){
        ArchRule rule = classes().that()
                .resideInAPackage("..config..")
                .or()
                .resideInAPackage("..properties..")
                .or()
                .areAnnotatedWith(Configuration.class)
                .should()
                .haveSimpleNameEndingWith("Config")
                .orShould()
                .haveSimpleNameEndingWith("ConfigTest")
                .orShould()
                .haveSimpleNameEndingWith("Properties");

        rule.check(importedClasses);
    }

    @Test
    void testNameTest() {

        ArchRule rule = classes().that()
                .haveSimpleNameEndingWith("Test")
                .should()
                .resideInAnyPackage("..integrationstests..", "..architecturetests..", "..controller..", "..service..", "..mapper..", "..model..", "..diversitylunchspringboot..");

        rule.check(importedClasses);
    }

    @Test
    void serviceTest() {

        ArchRule rule = classes().that()
                .resideInAPackage("..service..")
                .and()
                .areAnnotatedWith(Service.class)
                .should()
                .haveSimpleNameEndingWith("Service");

        rule.check(importedClasses);
    }

    @Test
    void noGenericExceptionsTest() {
        NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS.check(importedClasses);
    }

    @Test
    void classes_should_not_use_java_util_logging() {
        NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING.check(importedClasses);
    }

    @Test
    void diagramStructureTest() {

        ArchRule rule = classes() //
                .that().resideInAnyPackage("..controller..", "..model..", "..service..") //
                .should(adhereToPlantUmlDiagram(Objects.requireNonNull(ArchitectureTest.class.getResource("/ArchitectureDiagram.puml")),
                        consideringOnlyDependenciesInDiagram()));

        rule.check(importedClasses);
    }
}
