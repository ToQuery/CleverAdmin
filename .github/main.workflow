workflow "npm install and build" {
  on = "push"
  resolves = ["GitHub Action for npm build"]
}

action "GitHub Action for npm install" {
  uses = "actions/npm@59b64a598378f31e49cb76f27d6f3312b582f680"
  runs = "npm install"
}

action "GitHub Action for npm build" {
  uses = "actions/npm@59b64a598378f31e49cb76f27d6f3312b582f680"
  needs = ["GitHub Action for npm install"]
  runs = "npm run build"
}
