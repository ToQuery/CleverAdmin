const viewTableGenerator = require('./plop-templates/curd/prompt')

module.exports = function(plop) {
  plop.setGenerator('view-table', viewTableGenerator)
}
