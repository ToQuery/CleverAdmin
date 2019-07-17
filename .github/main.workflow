workflow "maven install and package" {
  on = "push"
  resolves = ["GitHub Action for Maven"]
}

action "GitHub Action for Maven" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  runs = "mvn clean install package"
  args = "-DskipTests -f pom.xml"
  secrets = ["GITHUB_TOKEN"]
}
