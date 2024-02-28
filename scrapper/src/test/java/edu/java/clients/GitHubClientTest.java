package edu.java.clients;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import edu.java.ScrapperApplication;
import edu.java.configuration.ClientConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.logging.Logger;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

@SpringBootTest(classes = ScrapperApplication.class)
@TestPropertySource(locations = "classpath:test")
@WireMockTest
public class GitHubClientTest {
    @Autowired
    ClientConfig clientConfig;
    String url = "/repos/Molok0/java-course-2024-backend";
    private static final Logger LOGGER = Logger.getLogger(GitHubClientTest.class.getName());
    private static final String BODY = "{\n" +
        "    \"id\": 752771021,\n" +
        "    \"node_id\": \"R_kgDOLN5fzQ\",\n" +
        "    \"name\": \"java-course-2024-backend\",\n" +
        "    \"full_name\": \"Molok0/java-course-2024-backend\",\n" +
        "    \"private\": false,\n" +
        "    \"owner\": {\n" +
        "        \"login\": \"Molok0\",\n" +
        "        \"id\": 61472202,\n" +
        "        \"node_id\": \"MDQ6VXNlcjYxNDcyMjAy\",\n" +
        "        \"avatar_url\": \"https://avatars.githubusercontent.com/u/61472202?v=4\",\n" +
        "        \"gravatar_id\": \"\",\n" +
        "        \"url\": \"https://api.github.com/users/Molok0\",\n" +
        "        \"html_url\": \"https://github.com/Molok0\",\n" +
        "        \"followers_url\": \"https://api.github.com/users/Molok0/followers\",\n" +
        "        \"following_url\": \"https://api.github.com/users/Molok0/following{/other_user}\",\n" +
        "        \"gists_url\": \"https://api.github.com/users/Molok0/gists{/gist_id}\",\n" +
        "        \"starred_url\": \"https://api.github.com/users/Molok0/starred{/owner}{/repo}\",\n" +
        "        \"subscriptions_url\": \"https://api.github.com/users/Molok0/subscriptions\",\n" +
        "        \"organizations_url\": \"https://api.github.com/users/Molok0/orgs\",\n" +
        "        \"repos_url\": \"https://api.github.com/users/Molok0/repos\",\n" +
        "        \"events_url\": \"https://api.github.com/users/Molok0/events{/privacy}\",\n" +
        "        \"received_events_url\": \"https://api.github.com/users/Molok0/received_events\",\n" +
        "        \"type\": \"User\",\n" +
        "        \"site_admin\": false\n" +
        "    },\n" +
        "    \"html_url\": \"https://github.com/Molok0/java-course-2024-backend\",\n" +
        "    \"description\": null,\n" +
        "    \"fork\": false,\n" +
        "    \"url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend\",\n" +
        "    \"forks_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/forks\",\n" +
        "    \"keys_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/keys{/key_id}\",\n" +
        "    \"collaborators_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/collaborators{/collaborator}\",\n" +
        "    \"teams_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/teams\",\n" +
        "    \"hooks_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/hooks\",\n" +
        "    \"issue_events_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/issues/events{/number}\",\n" +
        "    \"events_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/events\",\n" +
        "    \"assignees_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/assignees{/user}\",\n" +
        "    \"branches_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/branches{/branch}\",\n" +
        "    \"tags_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/tags\",\n" +
        "    \"blobs_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/git/blobs{/sha}\",\n" +
        "    \"git_tags_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/git/tags{/sha}\",\n" +
        "    \"git_refs_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/git/refs{/sha}\",\n" +
        "    \"trees_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/git/trees{/sha}\",\n" +
        "    \"statuses_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/statuses/{sha}\",\n" +
        "    \"languages_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/languages\",\n" +
        "    \"stargazers_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/stargazers\",\n" +
        "    \"contributors_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/contributors\",\n" +
        "    \"subscribers_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/subscribers\",\n" +
        "    \"subscription_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/subscription\",\n" +
        "    \"commits_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/commits{/sha}\",\n" +
        "    \"git_commits_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/git/commits{/sha}\",\n" +
        "    \"comments_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/comments{/number}\",\n" +
        "    \"issue_comment_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/issues/comments{/number}\",\n" +
        "    \"contents_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/contents/{+path}\",\n" +
        "    \"compare_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/compare/{base}...{head}\",\n" +
        "    \"merges_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/merges\",\n" +
        "    \"archive_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/{archive_format}{/ref}\",\n" +
        "    \"downloads_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/downloads\",\n" +
        "    \"issues_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/issues{/number}\",\n" +
        "    \"pulls_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/pulls{/number}\",\n" +
        "    \"milestones_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/milestones{/number}\",\n" +
        "    \"notifications_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/notifications{?since,all,participating}\",\n" +
        "    \"labels_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/labels{/name}\",\n" +
        "    \"releases_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/releases{/id}\",\n" +
        "    \"deployments_url\": \"https://api.github.com/repos/Molok0/java-course-2024-backend/deployments\",\n" +
        "    \"created_at\": \"2024-02-04T18:57:39Z\",\n" +
        "    \"updated_at\": \"2024-02-04T19:06:26Z\",\n" +
        "    \"pushed_at\": \"2024-02-25T17:40:36Z\",\n" +
        "    \"git_url\": \"git://github.com/Molok0/java-course-2024-backend.git\",\n" +
        "    \"ssh_url\": \"git@github.com:Molok0/java-course-2024-backend.git\",\n" +
        "    \"clone_url\": \"https://github.com/Molok0/java-course-2024-backend.git\",\n" +
        "    \"svn_url\": \"https://github.com/Molok0/java-course-2024-backend\",\n" +
        "    \"homepage\": null,\n" +
        "    \"size\": 133,\n" +
        "    \"stargazers_count\": 0,\n" +
        "    \"watchers_count\": 0,\n" +
        "    \"language\": \"Java\",\n" +
        "    \"has_issues\": true,\n" +
        "    \"has_projects\": true,\n" +
        "    \"has_downloads\": true,\n" +
        "    \"has_wiki\": true,\n" +
        "    \"has_pages\": false,\n" +
        "    \"has_discussions\": false,\n" +
        "    \"forks_count\": 0,\n" +
        "    \"mirror_url\": null,\n" +
        "    \"archived\": false,\n" +
        "    \"disabled\": false,\n" +
        "    \"open_issues_count\": 2,\n" +
        "    \"license\": null,\n" +
        "    \"allow_forking\": true,\n" +
        "    \"is_template\": false,\n" +
        "    \"web_commit_signoff_required\": false,\n" +
        "    \"topics\": [],\n" +
        "    \"visibility\": \"public\",\n" +
        "    \"forks\": 0,\n" +
        "    \"open_issues\": 2,\n" +
        "    \"watchers\": 0,\n" +
        "    \"default_branch\": \"main\",\n" +
        "    \"temp_clone_token\": null,\n" +
        "    \"network_count\": 0,\n" +
        "    \"subscribers_count\": 1\n" +
        "}";

    @Test
    void testClient() {

        stubFor(get(urlPathMatching(url)).willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json   ")
            .withBody("<response>SUCCESS</response>")));

        clientConfig.gitHubClient().getInfo("Molok0", "java-course-2024-backend").subscribe(userRepositoryResponse -> {
            LOGGER.info(userRepositoryResponse.id.toString());
            System.out.println(userRepositoryResponse.id.toString());
            Assertions.assertEquals(userRepositoryResponse.id, 752771021);
        });
    }
}
