const viewTableGenerator = require('./plop-templates/crud/prompt')

module.exports = function(plop) {
  plop.setGenerator('view-table', viewTableGenerator)
}
