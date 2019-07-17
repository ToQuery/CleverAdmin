workflow "maven install and package" {
  on = "push"
  resolves = [
    "GitHub Action for Maven",
    "GitHub Action for npm build",
  ]
}

action "GitHub Action for Maven" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  secrets = ["GITHUB_TOKEN"]
  runs = "mvn clean install package"
  args = "-X -DskipTests"
}

action "GitHub Action for npm" {
  uses = "actions/npm@59b64a598378f31e49cb76f27d6f3312b582f680"
  runs = "npm install"
}

action "GitHub Action for npm build" {
  uses = "actions/npm@59b64a598378f31e49cb76f27d6f3312b582f680"
  needs = ["GitHub Action for npm"]
  runs = "npm run build"
}
