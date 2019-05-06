const { notEmpty } = require('../utils.js')

// const WEBAPP_PATH = 'src/main/webapp'
const WEBAPP_PATH = 'docs/temp/'

function fillField(fieldName = '', fieldType = 'String') {
  return {
    fieldName: fieldName,
    fieldType: fieldType
  }
}

function fillFields(fieldsStr) {
  const fields = []
  if (fieldsStr.indexOf(',') >= 0) {
    const fieldTypeStr = fieldsStr.split(',')
    for (let i = 0; i < fieldTypeStr.length; i++) {
      const fieldStrs = fieldTypeStr[i].split('#')
      fields.push(fillField(fieldStrs[0], fieldStrs[1]))
    }
  } else {
    const fieldStrs2 = fieldsStr.split('#')
    fields.push(fillField(fieldStrs2[0], fieldStrs2[1]))
  }
  return fields
}

module.exports = {
  description: '快速生成CURD功能。',
  prompts: [
    {
      type: 'input',
      name: 'name',
      message: '请输入模块名称：',
      validate: notEmpty('name')
    },
    {
      type: 'input',
      name: 'fields',
      message: '请输入字段,多个字段以逗号分隔，一个字段内以#区分类型(默认String)',
      validate: notEmpty('fields')
    }
  ],
  actions: data => {
    const name = '{{name}}'
    const fieldList = fillFields(data.fields)
    console.info('成功获取到字段信息:\n', JSON.stringify(fieldList))

    const actions = [
      {
        type: 'add',
        path: WEBAPP_PATH + `src/views/${name}/index.vue`,
        templateFile: 'plop-templates/curd/index.hbs',
        data: {
          name: name,
          fieldList: fieldList
        }
      }
    ]
    return actions
  }
}
/*
,
      {
        type: 'add',
        path: WEBAPP_PATH + `src/views/${name}/edit.vue`,
        templateFile: 'plop-templates/curd/edit.hbs',
        data: {
          name: name,
          fields: fields
        }
      }
 */
