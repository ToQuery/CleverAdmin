const { notEmpty } = require('../utils.js')

// const WEBAPP_PATH = 'src/main/webapp'
const WEBAPP_PATH = 'docs/temp/'

const JAVA_SOURCE_PATH = 'docs/temp/'

const PACKAGE_PATH = 'io/github/toquery/cleverweb/'

/**
 * 根据包文件路径转化为代码包路径
 * @returns {string}
 */
function getPackageSources() {
	return PACKAGE_PATH.replace(new RegExp('/',"gm"),'.').substring(0, PACKAGE_PATH.length - 1)
}

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
		const nameProperCase = '{{properCase name}}'
    const fieldList = fillFields(data.fields)
		const packageSources = getPackageSources()
    console.info('成功获取到字段信息:\n', JSON.stringify(fieldList))

    const actions = [
      {
        type: 'add',
        path: WEBAPP_PATH + `src/views/biz/${name}/index.vue`,
        templateFile: 'plop-templates/curd/view/index.hbs',
        data: {
          name: name,
          fieldList: fieldList
        }
      },
      {
        type: 'add',
        path: WEBAPP_PATH + `src/views/biz/${name}/edit.vue`,
        templateFile: 'plop-templates/curd/view/edit.hbs',
        data: {
          name: name,
          fieldList: fieldList
        }
      },
      {
        type: 'add',
        path: WEBAPP_PATH + `src/api/biz/${name}.js`,
        templateFile: 'plop-templates/curd/view/api.hbs',
        data: {
          name: name
        }
      },
      {
        type: 'add',
        path: JAVA_SOURCE_PATH + PACKAGE_PATH + `entity/${nameProperCase}.java`,
        templateFile: 'plop-templates/curd/java/entity.hbs',
        data: {
          name: name,
          fieldList: fieldList,
					packageSources: packageSources
        }
      },
			{
				type: 'add',
				path: JAVA_SOURCE_PATH + PACKAGE_PATH + `controller/${nameProperCase}Controller.java`,
				templateFile: 'plop-templates/curd/java/controller.hbs',
				data: {
					name: name,
					fieldList: fieldList,
					packageSources: packageSources
				}
			},
			{
				type: 'add',
				path: JAVA_SOURCE_PATH + PACKAGE_PATH + `dao/${nameProperCase}Dao.java`,
				templateFile: 'plop-templates/curd/java/dao.hbs',
				data: {
					name: name,
					fieldList: fieldList,
					packageSources: packageSources
				}
			},
			{
				type: 'add',
				path: JAVA_SOURCE_PATH + PACKAGE_PATH + `service/I${nameProperCase}Service.java`,
				templateFile: 'plop-templates/curd/java/iservice.hbs',
				data: {
					name: name,
					fieldList: fieldList,
					packageSources: packageSources
				}
			},
			{
				type: 'add',
				path: JAVA_SOURCE_PATH + PACKAGE_PATH + `service/impl/${nameProperCase}Service.java`,
				templateFile: 'plop-templates/curd/java/service.hbs',
				data: {
					name: name,
					fieldList: fieldList,
					packageSources: packageSources
				}
			}
    ]
    return actions
  }
}
