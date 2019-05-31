workflow "maven install and package" {
  on = "push"
  resolves = ["GitHub Action for Docker"]
}

action "GitHub Action for Maven" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  secrets = ["GITHUB_TOKEN"]
  args = "clean install packages -X -DskipTests"
}

action "GitHub Action for Docker" {
  uses = "actions/docker/cli@8cdf801b322af5f369e00d85e9cf3a7122f49108"
  needs = ["GitHub Action for Maven"]
}
