package org.azd.main;

import org.azd.core.types.Project;
import org.azd.exceptions.AzDException;
import org.azd.utils.AzDClientApi;

public class AzureMainClass {

	public static void main(String[] args) {
		String organisation = "rajabsc89";
        String personalAccessToken = "lnrufpykm7kpq7sko7nf5abmsxdrp6wy4jfwp2ncilat3bajnd7q";

        // Connect Azure DevOps API with organisation name and personal access token.
        var webApi = new AzDClientApi(organisation, personalAccessToken);

        // call the respective API with created webApi client connection object;
        var core = webApi.getCoreApi();
        try {
            // get the list of projects
            core.getProjects();
            
            // Get the list of projects asynchronously
            // This returns a CompletableFuture<List<Project>>
            var future = core.createAsync(core.getProjects().getProjects());

            System.out.println("Do something here...");

            var projects = future.join(); // Get the results
            
            projects.stream()
                    .map(Project::getName)
                    .forEach(System.out::println);
            System.out.println("Do something Teams...");
           core.getTeams().getTeams().stream().map(i -> i.getName()).forEach(System.out::println);
            
            // create a new project
           // core.createProject("my-new-project", "Finance management");
            
            // create a team in the project
            //core.createTeam("my-new-project", "my-new-team");
        
            // list all the teams
            core.getTeams();
        } catch (AzDException e1) {
            e1.printStackTrace();
        }
	}

}
