workflow "project-build" {
  on = "push"
  resolves = ["GitHub Action for Maven Package"]
}

action "GitHub Action for Maven" {
  uses = "LucaFeger/action-maven-cli@aed8a1fd96b459b9a0be4b42a5863843cc70724e"
  secrets = ["GITHUB_TOKEN"]
  runs = "mvn clean install"
  args = "-X -DskipTests"
}

action "GitHub Action for Maven Package" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  needs = ["GitHub Action for Maven"]
  runs = "mvn package"
  args = "-X -DskipTests"
  secrets = ["GITHUB_TOKEN"]
}
