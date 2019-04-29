workflow "project-build" {
  on = "push"
  resolves = ["GitHub Action for Maven"]
}

action "GitHub Action for Maven" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  needs = ["GitHub Action for Maven"]
  runs = "mvn clean install package"
  args = "-X -DskipTests"
  secrets = ["GITHUB_TOKEN"]
}
