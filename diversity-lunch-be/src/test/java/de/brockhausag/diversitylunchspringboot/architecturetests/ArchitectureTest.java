package de.brockhausag.diversitylunchspringboot.architecturetests;
import com.tngtech.archunit.base.PackageMatcher;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import de.brockhausag.diversitylunchspringboot.DiversityLunchSpringBootApplication;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.GeneralCodingRules.*;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.adhereToPlantUmlDiagram;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

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

    void displayDiagram() {

        String rootPackageName    = "de.brockhausag.diversitylunchspringboot";
        String diagramTemplate    = "@startuml%n%n%s%n%n@enduml";
        String dependencyTemplate = "[%s] --> [%s]";
        createDiagram(importedClasses, rootPackageName, diagramTemplate, dependencyTemplate);
    }

    private void createDiagram(JavaClasses classes, String rootPackageName, String diagramTemplate, String dependencyTemplate) {
        JavaPackage rootPackage = classes.getPackage(rootPackageName);

        PackageMatcher packageMatcher = PackageMatcher.of(rootPackageName + ".(*)..");

        Set<String> diagramLines = rootPackage.getSubpackages()
                .stream()
                .flatMap(origin -> origin.getPackageDependenciesFromSelf()
                        .stream()
                        .filter(target -> packageMatcher.matches(target.getName()))
                        .map(target -> packageMatcher.match(target.getName()).get().getGroup(1))
                        .map(targetPackage -> List.of(origin.getRelativeName(), targetPackage)))
                .filter(dependency -> !dependency.get(0).equals(dependency.get(1)))
                .map(dependency -> String.format(dependencyTemplate, dependency.get(0), dependency.get(1)))
                .collect(toSet());

        String diagram = String.format(diagramTemplate, diagramLines.stream().collect(joining(lineSeparator())));
        System.out.println(diagram);
    }

}
