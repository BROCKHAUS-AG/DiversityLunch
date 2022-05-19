const fs = require('fs');

const customersFile = 'customers.txt';
const projectTypeTsFileFE = 'diversity-lunch-fe/src/types/enums/project.type.ts';
const projectDropdownTypeTsFileFE = 'diversity-lunch-fe/src/types/dropdownOptions/project-dropdown-type.ts';
const projectEnumFileBE = 'diversity-lunch-be/src/main/java/de/brockhausag/diversitylunchspringboot/profile/model/Project.java';

function addCustomersToProject()
{
    const customers = getCustomerData();

    if(customers != null && customers.length > 0)
    {
        build_ProjectTypeTsFileFE(customers);
        build_ProjectDropdownTypeTsFileFE(customers);
        build_projectEnumBE(customers);

        console.log("Succeed");
    }
    else
    {
        console.error("Please Add customers.txt in Diversity-Lunch-App");
    }

}

function getCustomerData()
{
    if (fs.existsSync(customersFile)) {
        var customersData = fs.readFileSync(customersFile, 'utf8');

        return customers = customersData.split('\n');

    } else {
        console.error('File does not exist: ',customersFile);
        //exit(1);
    }
}

function build_ProjectTypeTsFileFE(customers)
{
    var projectTypeTs = 'export type Project =\n';


    customers.forEach((customer) => {

        var filteredCustomerKey = customer.replace(" ","").normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/[^a-zA-Z ]/g, "");
        if(customer.length > 1)
            projectTypeTs += "    | \'"+filteredCustomerKey+"\'\n"
    });

    console.log(projectTypeTs);

    writeFile(projectTypeTs,projectTypeTsFileFE);
}

function build_ProjectDropdownTypeTsFileFE(customers)
{
    var projectDropdownTypeTs = "import { DropdownOptions } from './dropdown-options.type';\n" +
        "import { Project } from '../enums/project.type';\n" +
        "\n" +
        "export const PROJECT_DROPDOWN_OPTIONS: DropdownOptions<Project> = [\n";

    customers.forEach((customer) => {
        if(customer.length > 1) {
            customer = customer.trim();
            var filteredCustomerKey = customer.replace(" ","").normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/[^a-zA-Z ]/g, "");
            var customerStr = "{\n\tvalue: \'"+filteredCustomerKey+"\',\n"+
                "\tlabel: \'"+customer+"\',\n},\n";
            projectDropdownTypeTs += customerStr;
        }

    });
    projectDropdownTypeTs += "];";
    console.log(projectDropdownTypeTs);
    writeFile(projectDropdownTypeTs,projectDropdownTypeTsFileFE);
}

function build_projectEnumBE(customers)
{

    var projectTypeTs = 'package de.brockhausag.diversitylunchspringboot.profile.model;\n' +
        '\n' +
        'public enum Project {\nExampleCompany1, ExampleCompany2, ';

    customers.forEach((customer) => {

        var filteredCustomerKey = customer.replace(" ","").normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/[^a-zA-Z ]/g, "");
        if(customer.length > 1)
            projectTypeTs += filteredCustomerKey+", ";
    });
    //Default Key
    projectTypeTs += "\n}"

    console.log(projectTypeTs);

    writeFile(projectTypeTs,projectEnumFileBE);
}

function writeFile(content,path)
{
    if (fs.existsSync(path)) {
        try{
            fs.writeFileSync(path, content);
        }
        catch(e) {
            console.error("Datei konnte nicht bearbeitet werden: "+path)
            console.error(e);
        }
    } else {
        console.error('File does not exist: ',customersFile);
        //exit(1);
    }

    console.log("build_ProjectTypeTsFileFE successfully written!: \n");
    console.log(content)
}

addCustomersToProject();

